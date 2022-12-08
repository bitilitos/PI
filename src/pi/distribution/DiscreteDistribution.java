package pi.distribution;

import pi.gui.HistogramWindow;

import java.awt.image.BufferedImage;

public final class DiscreteDistribution {

    public static double getTriangleDiscrete(){
        double result = Math.random();
        if (result < 0.02) return 2;
        if (result < 0.06) return 3;
        if (result < 0.14) return 5;
        if (result < 0.30) return 7;
        if (result < 0.62) return 11;
        return 13;
    }


    public static double[] createTriangleDiscreteHistogramData(){
        double[] dataHistogram = new double[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataHistogram[i]=DiscreteDistribution.getTriangleDiscrete();
        }

        return dataHistogram;
    }




    public static double getUniformDiscrete(){
        return Math.floor((Math.random()*10)+1);
    }

    public static double getUniformDiscrete0to5(){
        return Math.floor((Math.random()*5));
    }

    public static double[] createUniformDiscreteHistogramData(){
        double[] dataHistogram = new double[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataHistogram[i]=DiscreteDistribution.getUniformDiscrete();
        }

        return dataHistogram;
    }


    public static void main(String[] args) {
        BufferedImage histogramImg = Histogram.createHistogram("Histograma Triangular Discreto", DiscreteDistribution.createTriangleDiscreteHistogramData(), 900, 400, 100        );
        HistogramWindow.createHistogramJFrame("Histograma Triangular Discreto", histogramImg).setVisible(true);
        BufferedImage histogramImg2 = Histogram.createHistogram("Histograma Uniforme Discreto", DiscreteDistribution.createUniformDiscreteHistogramData(), 900, 400, 100        );
        HistogramWindow.createHistogramJFrame("Histograma Uniforme Discreto", histogramImg2).setVisible(true);

    }
}




