package pi.distribution;

import pi.gui.HistogramWindow;

import java.awt.image.BufferedImage;

public class UniformDistribution {




    public double getUniform(){
        return 1+(99*Math.random());
    }

    public double FDP(double x) {return 1/99*x;
    }

    //cria um array de double com distribuição triangular
    public  double[] createHistogramData(){
        double[] dataHistogram = new double[1000000];
        for (int i = 0; i < 1000000; i++) {
            dataHistogram[i]=this.getUniform();
        }

        return dataHistogram;
    }

    public static void main(String[] args) {
        UniformDistribution ud = new UniformDistribution();
        BufferedImage histogramImg = Histogram.createHistogram("Histograma Uniforme", ud.createHistogramData(), 900, 400, 100      );
        HistogramWindow.createHistogramJFrame("Histograma Uniforme", histogramImg).setVisible(true);
    }
}
