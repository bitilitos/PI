package pi.distribution;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import pi.gui.HistogramWindow;

import java.awt.image.BufferedImage;

public final class Histogram {

    public static BufferedImage createHistogram(String title, double[] dataHistogram, int width, int height,int nrBarras) {
        HistogramDataset hd = new HistogramDataset();
        hd.setType(HistogramType.RELATIVE_FREQUENCY);
        hd.addSeries("n", dataHistogram, nrBarras);
        PlotOrientation orientation = PlotOrientation.VERTICAL;
        JFreeChart histograma = ChartFactory.createHistogram(title,
                "Dados", "Frequência", hd);

        return histograma.createBufferedImage(width,height);
    }


}
