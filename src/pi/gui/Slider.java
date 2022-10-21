package pi.gui;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Slider extends JFrame implements ActionListener, ChangeListener{
    private final int RANGE = 100;
    private int VA1;
    private int VA2;
    private int VA3;
    private int VA4;
    private int VA5;

    private JFrame jFrame;
    private JSlider jSlider;
    private JLabel jLabel;
    private JPanel center;
    private JPanel west;
    private JPanel east;
    private JButton btnReset;
    private JButton btnSave;
    private JTextField value;
    private JTextField gaussian;

    public Slider (int VA1, int range, int VA2){
        this.VA1 = VA1;
        this.VA2 = VA2;
        range = VA1 + RANGE;
    }
    public int getVA1() {
        return VA1;
    }

    public void setVA1(int VA1) {
        this.VA1 = VA1;
    }

    public int getVA2() {
        return VA2;
    }

    public void setVA2(int VA2) {
        this.VA2 = VA2;
    }

    public int getVA3() {
        return VA3;
    }

    public void setVA3(int VA3) {
        this.VA3 = VA3;
    }

    public int getVA4() { return VA4; }

    public void setVA4(int VA4) { this.VA4 = VA4; }

    public int getVA5() { return VA5; }

    public void setVA5(int VA5) { this.VA5 = VA5; }

    public void SliderON(Slider slider){
        // frame
        jFrame = new JFrame("Select a Value");

        // set the size of frame
        jFrame.setSize(800, 200);
        jFrame.setLayout(new BorderLayout());
        centerFrame(jFrame);

        // slider
        jSlider = new JSlider(slider.getVA1(), slider.getVA1()+RANGE, slider.getVA2());;

        // label
        jLabel = new JLabel();

        // set the font size
        jLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        // create panels
        center = new JPanel();
        west = new JPanel();
        east = new JPanel();

        // buttons
        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnSave = new JButton("Save");
        btnSave.setFont(new Font("SansSerif", Font.BOLD, 20));

        // text fiel VA4
        value = new JTextField(10);
        value.setFont(new Font("SansSerif", Font.PLAIN, 20));

        // text fiel VA5
        gaussian = new JTextField(10);
        gaussian.setFont(new Font("SansSerif", Font.PLAIN, 20));

/*        // paint the ticks and tracks
        jSlider.setPaintTrack(true);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);*/

        // set spacing
        jSlider.setMajorTickSpacing(50);
        jSlider.setMinorTickSpacing(5);

        // set Change Listener
        jSlider.addChangeListener(slider);

        // set margins
        jSlider.setFont(new Font("SansSerif", Font.BOLD, 20));

        // set color
        jSlider.setBackground(Color.DARK_GRAY);

        // setBorder
        Border emptyBorder = new EmptyBorder(30, 40, 30, 40);
        jSlider.setBorder(emptyBorder);

        jFrame.add("North", jSlider);

        // add slider to panel
        Label label1 = new Label("Value");
        label1.setFont(new Font("SansSerif", Font.BOLD, 20));
        center.add(label1);
        center.add(value);
        value.setText(String.valueOf(slider.getVA2()));
        Label label2 = new Label("Gaussian");
        label2.setFont(new Font("SansSerif", Font.BOLD, 20));
        center.add(label2);
        center.add(gaussian);
        gaussian.setText(String.valueOf(slider.getVA4()));
        jFrame.add("Center", center);

        btnReset.addActionListener(slider);
        btnSave.addActionListener(slider);
        west.add("West", btnReset);
        jFrame.add("West", west);
        east.add("East", btnSave);
        jFrame.add("East", east);

        // set close operation
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        normalDistribution(getVA1()+RANGE/2, getVA2()-(getVA1()+RANGE/2));

        value.setText("" + jSlider.getValue());
        gaussian.setText("" + VA4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnReset)
            reset();
        if(e.getSource() == btnSave)
            save();
    }

    public void reset() {
        value.setText(String.valueOf(getVA2()));
        gaussian.setText(String.valueOf(getVA4()));
        jSlider.setValue(getVA2());
    }

    public void save(){
        setVA3(jSlider.getValue());
        System.out.println(VA3);
        System.out.println(VA4);
        System.out.println(VA5);
        System.exit(0);
    }

    // This method centers a frame on the screen
    private static void centerFrame (JFrame frame){

        // Get the size of the screen
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dimension.width - w) / 2;
        int y = (dimension.height - h)/ 2;

        // Move the window
        frame.setLocation(x, y);
    }

    public void normalDistribution(int mean, int std){

        // create a random object
        Random r = new Random();
        // set VA4 with a normal distribution receiving the mean and the standard deviation
        setVA4((int) (r.nextGaussian()*std+mean));

        // verify if V4<V1
        if (getVA4()<getVA1())
            setVA4(getVA1());
        // verify if VA>V1+range
        else if (getVA4()>getVA1()+RANGE)
            setVA4(getVA1()+RANGE);
    }
}
