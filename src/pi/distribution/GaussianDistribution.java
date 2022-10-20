package pi.distribution;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

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

    public GaussianDistribution(double mean, double std, double minLimit, double maxLimit) {
        if (std < 0) throw new IllegalArgumentException("STD must be bigger or equal to 0");
        if (mean < minLimit || mean > maxLimit) throw new IllegalArgumentException("minLimit < Mean < maxLimit");
        this.std = std;
        this.mean = mean;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        truncated = true;
    }

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

    private double getTableFDC(double x) {
        GaussianDistributionTable gdt = new GaussianDistributionTable();
        return gdt.getTableValue((x-mean)/std);
    }


    public double valorDeX(double x) {
        if (!truncated) return (1/denominator())*Math.exp(exponencialPower(x));
        return (1/denominator())*Math.exp(exponencialPower(x))/getNormalizationFactor();
    }


    private double denominator() {
        return Math.sqrt(2 * Math.PI)*std;
    }

    private double exponencialPower(double x) {
        return -(Math.pow(x-mean,2)/2*Math.pow(std,2));
    }

    // FDP mean = 0, std = 1
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

    // Transformação de Z
    private double myFDP(double x, double mean, double std) {
        return myFDP((x-mean)/std);
    }



    public double getGaussian(){
        return mean + Math.sqrt(2)*std*erfInv(2*Math.random()-1);
    }



    private double erfInv(double x) {
        double result = Math.sqrt(Math.sqrt(Math.pow(erfInvPart1PlusPart2(x),2)-(Math.log(1-Math.pow(x,2))/getA()))-erfInvPart1PlusPart2(x));
        result = getSgn(x,getTau(x))*result;
        return result;
    }

    private double getSgn(double x, double tau) {
        if (x >= 0) return 1-tau;
        else return tau-1;
    }

    private double getTau(double x) {
        double t = getT(x);
        return t*Math.exp(-Math.pow(x,2)-1.26551223+1.00002368*t+0.37409196*Math.pow(t,2)+
                0.09678418*Math.pow(t,3)-0.18628806*Math.pow(t,4)+0.27886807*Math.pow(t,5)-
                1.13520398*Math.pow(t,6)+1.48851587*Math.pow(t,7)-0.82215223*Math.pow(t,8)+
                0.17087277*Math.pow(t,9));
    }

    private double getT(double x) {
        return 1/(1+(Math.abs(x)*0.5));
    }

    private double erfInvPart1PlusPart2(double x) {
        return erfInvPart1() + erfInvPart2(x);
    }

    private double erfInvPart1(){
        return 2/(Math.PI*getA());
    }

    private double erfInvPart2(double x){
        return Math.log(1-Math.pow(x,2))/2;

    }

    private double getA (){
        return (8*(Math.PI-3))/(3*Math.PI*(4-Math.PI));
    }



    public static void main(String[] args) {
        GaussianDistribution gd = new GaussianDistribution(0, 200);
        double[] dataHistogram = new double[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataHistogram[i]=gd.getGaussian();
        }

        HistogramDataset hd = new HistogramDataset();
        hd.setType(HistogramType.RELATIVE_FREQUENCY);
        hd.addSeries("n", dataHistogram, 100);
        PlotOrientation orientation = PlotOrientation.VERTICAL;
        JFreeChart histograma = ChartFactory.createHistogram("Histograma Gaussiano",
                "Dados", "Frequência", hd);

        int width = 500;
        int height = 300;
        try {
            ChartUtils.saveChartAsPNG(new File("Gaussian_histogram.PNG"),histograma, width, height);
        } catch (IOException e) {}
    }











}
