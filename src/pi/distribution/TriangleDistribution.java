package pi.distribution;

import pi.gui.HistogramWindow;

import java.awt.image.BufferedImage;

public class TriangleDistribution {


    public double getTrangular(){
        return Math.sqrt(Math.random());
    }

    public double FDP(double x) {
        return Math.pow(x,2);
    }

    //cria um array de double com distribuição triangular
    public  double[] createHistogramData(){
        double[] dataHistogram = new double[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataHistogram[i]=this.getTrangular();
        }

        return dataHistogram;
    }

    public static void main(String[] args) {
        TriangleDistribution td = new TriangleDistribution();
        BufferedImage histogramImg = Histogram.createHistogram("Histograma Triangular", td.createHistogramData(), 900, 400, 100        );
        HistogramWindow.createHistogramJFrame("Histograma Triangular", histogramImg).setVisible(true);
    }
}
