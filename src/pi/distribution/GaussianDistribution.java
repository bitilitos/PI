package pi.distribution;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import pi.gui.HistogramWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public final class GaussianDistribution {

    private double std;
    private double mean;
    private double minLimit;
    private double maxLimit;
    private boolean truncated;

    public GaussianDistribution(double mean, double std) {
        if (std < 0) throw new IllegalArgumentException("STD must be bigger or equal to 0");
        this.std = std;
        this.mean = mean;
        truncated = false;
    }

    //NÃO ESTÁ A SER USADO
    // Construtor para Gaussiana truncada
    public GaussianDistribution(double mean, double std, double minLimit, double maxLimit) {
        if (std < 0) throw new IllegalArgumentException("STD must be bigger or equal to 0");
        if (mean < minLimit || mean > maxLimit) throw new IllegalArgumentException("minLimit < Mean < maxLimit");
        this.std = std;
        this.mean = mean;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        truncated = true;
    }

    //NÃO ESTÁ A SER USADO
    // Calcula o factor de normalização da FDP
    private double getNormalizationFactor() {

        //double probMinLimit = getTableFDC(minLimit);
        //double probMaxLimit = getTableFDC(maxLimit);

        double probMinLimit = getTableFDC(minLimit);
        double probMaxLimit = getTableFDC(maxLimit);

        double normalizationFactor = probMaxLimit - probMinLimit;
        System.out.println(probMinLimit);
        System.out.println(probMaxLimit);
        return normalizationFactor;
    }

    //NÃO ESTÁ A SER USADO
    //Usa a tabela da distribuição normal para devolver a probabilidade acumulada de [-INF,x]
    private double getTableFDC(double x) {
        GaussianDistributionTable gdt = new GaussianDistributionTable();
        return gdt.getTableValue((x-mean)/std);
    }

    //NÃO ESTÁ A SER USADO
    //Devolve o valor relactivo a x na FDP, com a Gaussiana truncada ou não.
    public double valorDeX(double x) {
        if (!truncated) return (1/denominator())*Math.exp(exponencialPower(x));
        return (1/denominator())*Math.exp(exponencialPower(x))/getNormalizationFactor();
    }

    //Cálculo parcial do denominador da FDP
    private double denominator() {
        return Math.sqrt(2 * Math.PI)*std;
    }

    //Cálculo parcial da Exponencial da FDP
    private double exponencialPower(double x) {
        return -(Math.pow(x-mean,2)/2*Math.pow(std,2));
    }

    //NÃO ESTÁ A SER USADO TRUNCADA DÁ PROBLEMAS
    // devolve a probabilidade acumulada de [-INF,x] para a FDP mean = 0, std = 1
    private double myFDP(double x) {
        double normalizationFactor=1.0;
        // Se a função é truncada tem que ser normalizada
        if (truncated) normalizationFactor = getNormalizationFactor();
        // Se a Gaussiana for de média 0 e desvio 1
        if (mean == 0 && std == 1) return (Math.exp(-(x*x/2))/Math.sqrt(2*Math.PI))/normalizationFactor;

        // Caso contrário fazemos uma transformação ao z
        double z = (x-mean)/std;
        return (Math.exp(-(z*z/2))/Math.sqrt(2*Math.PI))/normalizationFactor;

    }

    //NÃO ESTÁ A SER USADO
    // Devolve a transformação de Z
    private double myFDP(double x, double mean, double std) {
        return myFDP((x-mean)/std);
    }



    public double getGaussian(){
        return mean + Math.sqrt(2)*std*erfInv(2*Math.random()-1);
    }


    // devolve o valor da inversa da função de erro
    private double erfInv(double x) {
        double result = Math.sqrt(Math.sqrt(Math.pow(erfInvPart1PlusPart2(x),2)-(Math.log(1-Math.pow(x,2))/getA()))-erfInvPart1PlusPart2(x));
        result = getSgn(x,getTau(x))*result;
        return result;
    }

    //Devolve o valor para o sinal de x
    private double getSgn(double x, double tau) {
        if (x >= 0) return 1-tau;
        else return tau-1;
    }

    //devolve o tau para cálculo do sinal de x para a inversa da função de erro
    private double getTau(double x) {
        double t = getT(x);
        return t*Math.exp(-Math.pow(x,2)-1.26551223+1.00002368*t+0.37409196*Math.pow(t,2)+
                0.09678418*Math.pow(t,3)-0.18628806*Math.pow(t,4)+0.27886807*Math.pow(t,5)-
                1.13520398*Math.pow(t,6)+1.48851587*Math.pow(t,7)-0.82215223*Math.pow(t,8)+
                0.17087277*Math.pow(t,9));
    }

    //devolve o t para cálculo do tau para cálculo do sinal de x para a inversa da função de erro
    private double getT(double x) {
        return 1/(1+(Math.abs(x)*0.5));
    }

    //devolve a soma da primeira com a segunda parcela da inversa da função de erro
    private double erfInvPart1PlusPart2(double x) {
        return erfInvPart1() + erfInvPart2(x);
    }

    //devolve a primeira parcela da inversa da função de erro
    private double erfInvPart1(){
        return 2/(Math.PI*getA());
    }

    //devolve a segunda parcela da inversa da função de erro
    private double erfInvPart2(double x){
        return Math.log(1-Math.pow(x,2))/2;

    }

    //devolve o a da inversa da função de erro
    private double getA (){
        return (8*(Math.PI-3))/(3*Math.PI*(4-Math.PI));
    }


    //cria um array de double com distribuição gaussiana
    public double[] createHistogramData(){
        double[] dataHistogram = new double[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataHistogram[i]=this.getGaussian();
        }

        return dataHistogram;
    }


    public static void main(String[] args) {
        GaussianDistribution gd = new GaussianDistribution(50, 20);
        BufferedImage histogramImg = Histogram.createHistogram("Histograma Gaussiano", gd.createHistogramData(), 900, 400, 20
        );
        HistogramWindow.createHistogramJFrame("Histograma Gaussiano", histogramImg).setVisible(true);
    }




}
