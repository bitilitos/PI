package pi.distribution;

import pi.gui.HistogramWindow;

import java.awt.image.BufferedImage;

public class TriangleDistribution {
    int rangeStart = 0;


    public TriangleDistribution(int rangeStart) {

        this.rangeStart= rangeStart;
    }


    public double getTriangular(){
        return Math.sqrt(4*Math.random())+rangeStart;
    }

    public double FDP(double x) {return 0.5*x;
    }

    //cria um array de double com distribuição triangular
    public  double[] createHistogramData(){
        double[] dataHistogram = new double[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataHistogram[i]=this.getTriangular();
        }

        return dataHistogram;
    }

    public static void main(String[] args) {
        TriangleDistribution td = new TriangleDistribution(5);
        BufferedImage histogramImg = Histogram.createHistogram("Histograma Triangular", td.createHistogramData(), 900, 400, 100      );
        HistogramWindow.createHistogramJFrame("Histograma Triangular", histogramImg).setVisible(true);
    }
}
