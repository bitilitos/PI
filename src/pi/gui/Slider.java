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
    private final int RANGE = 3;
    private int VA1;
    private int VA2;
    private int VA3;
    private int VA4;
    private int VA5;

    private JSlider jSlider;
    private JLabel jLabel;
    private JPanel center;
    private JPanel west;
    private JPanel east;
    private JButton btnReset;
    private JButton btnGenerateContinuousVA;
    private JTextField value;
    private JTextField gaussian;
    private KeyGeneratorWindow kgw;


    public Slider (KeyGeneratorWindow kgw){
        this.kgw = kgw;
            }


    public Container SliderON(){
        Container container = new Container();
        // frame
        // set the size of frame

        container.setLayout(new BorderLayout());

        // slider
        jSlider = new JSlider(1,3);

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
        btnGenerateContinuousVA = new JButton("Gerar VA Contínuas");
        btnGenerateContinuousVA.setFont(new Font("SansSerif", Font.BOLD, 20));

        // text fiel VA4
        value = new JTextField(10);
        value.setFont(new Font("SansSerif", Font.PLAIN, 16));

        // text fiel VA5
        gaussian = new JTextField(10);
        gaussian.setFont(new Font("SansSerif", Font.PLAIN, 20));


        // set spacing

        jSlider.setMajorTickSpacing(33);
        jSlider.setMinorTickSpacing(33);

        // set Change Listener
        jSlider.addChangeListener(this);

        // set margins
        jSlider.setFont(new Font("SansSerif", Font.BOLD, 20));

        // set color
        jSlider.setBackground(Color.DARK_GRAY);

        // setBorder
        Border emptyBorder = new EmptyBorder(30, 40, 30, 40);
        jSlider.setBorder(emptyBorder);

        container.add("West", jSlider);

        // add slider to panel
        Label label1 = new Label("Encripação");
        label1.setFont(new Font("SansSerif", Font.BOLD, 20));
        center.add(label1);
        value.setText("Média (512B)");
        center.add(value);
        //value.setText(String.valueOf(this.getVA2()));
       // Label label2 = new Label("Gaussian");
        //label2.setFont(new Font("SansSerif", Font.BOLD, 20));
       // center.add(label2);
       // center.add(gaussian);
        //gaussian.setText(String.valueOf(slider.getVA4()));
        container.add("Center", center);

       // btnReset.addActionListener(this);
        btnGenerateContinuousVA.addActionListener(this);
      //  west.add("West", btnReset);
       // container.add("West", west);
       // east.add("East", btnGenerateContinuousVA);
        //container.add("East", east);

        return container;
    }

    public int getSliderValue() {
        return jSlider.getValue();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
       // normalDistribution(getVA1()+RANGE/2, getVA2()-(getVA1()+RANGE/2));
        String encryption = "";
        switch (jSlider.getValue()) {
            case 1:
                encryption = "Mínima (256B)";
                break;
            case 2:
                encryption = "Média (512B)";
                break;
            case 3:
                encryption = "Máxima (1024B)";
                break;
        }

        value.setText("" + encryption);
        kgw.setCalculateKeysButtonEnable();


        //gaussian.setText("" + VA4);
    }

    public int getEncryptionValue() {
        switch (jSlider.getValue()) {
            case 1:
                return 256;

            case 2:
                return 512;

        }
        return 1024;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnReset)
            reset();
        if(e.getSource() == btnGenerateContinuousVA)
            generateContinuousVA();
    }

    public void reset() {
       // value.setText(String.valueOf(getVA2()));
        //gaussian.setText(String.valueOf(getVA4()));
       // jSlider.setValue(getVA2());
    }

    public void generateContinuousVA(){

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


}
