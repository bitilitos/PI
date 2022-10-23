package pi.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HistogramWindow {


    public static JFrame createHistogramJFrame(String title, BufferedImage histogramImg) {
        JFrame frame = new JFrame(title);
        frame.setSize(histogramImg.getWidth(),histogramImg.getHeight());
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = new Container();
        container.setLayout(new FlowLayout());
        JLabel histogramLabel = new JLabel(new ImageIcon(histogramImg));
        container.add(histogramLabel);

        frame.add(container);

        return frame;
    }
}
