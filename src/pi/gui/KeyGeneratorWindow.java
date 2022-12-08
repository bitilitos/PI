package pi.gui;

import pi.crypto.RSA;
import pi.distribution.*;
import pi.userNode.ClientNode;
import pi.userNode.Node;
import pi.userNode.ServerNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.BigInteger;

public class KeyGeneratorWindow {
    private JFrame frame;
    private GaussianDistribution gaussianDistribution;
    private TriangleDistribution triangleDistribution;
    private UniformDistribution uniformDistribution = new UniformDistribution();
    private Insets insets = new Insets(10,10,10,10);
    private Button calculateKeysButton = new Button("Calcular Chaves");
    private Button gaussianHistogramButton;
    private Button triangularHistogramButton;
    private Button uniformHistogramButton;
    private Button okButton;
    private JTextField stdGaussianJText;
    private JTextField resultTriangularJText;
    private JTextField resultGaussianJText;
    private JTextField medianGaussianJText;
    private JTextField publicKeyJText;
    private JTextField privateKeyJText;
    private JTextField rangeStartJText = new JTextField();
    private int uniformDiscrete = -1;
    private int triangleDiscrete = -1;
    private double resultUniform = -1;
    private BigInteger resultGaussian = new BigInteger("-1");
    private BigInteger resultTriangular = new BigInteger("-1");
    private BigInteger publicKey;
    private BigInteger privateKey;
    private int multiplyFactor = -1;
    private Slider slider = new Slider(this);
    private Node node;

    public KeyGeneratorWindow (Node node) {
        this.node = node;
        createWindow();
        frame.setVisible(true);

    }



    private JFrame createWindow(){

        frame = new JFrame("Key Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;

        frame.add(resultsAndPrimes(),gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        frame.add(createGaussianDistributionContainer(), gbc);

        gbc.gridx = 1;
        frame.add(createDiscreteDistributionsContainer(), gbc);

        gbc.gridx = 2;
        frame.add(createTriangleDistributionContainer(), gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;

        frame.add(createUniformDistributionContainer(), gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        frame.add(slider.SliderON(),gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        calculateKeysButton.setEnabled(false);
        createCalculateKeysButton();
        frame.add(calculateKeysButton,gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        frame.add(createOKButton(), gbc);


        frame.pack();
        return  frame;
    }

    private Container resultsAndPrimes() {
        multiplyFactor = (int) DiscreteDistribution.getUniformDiscrete0to5();

        Container container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10,10,80);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        JLabel titleResultGaussianLable = new JLabel("Resultado");
        container.add(titleResultGaussianLable,gbc);


        gbc.gridy = 1;
        gbc.gridwidth = 1;

        resultGaussianJText = new JTextField();
        resultGaussianJText.setText("xxxxxxxxxxxxxxxxxxxxxx");
        container.add(resultGaussianJText,gbc);


//        gbc.gridx = 1;
//        JLabel multiplyGaussianLable = new JLabel("x10^"+multiplyFactor);
//        container.add(multiplyGaussianLable,gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;

        JLabel titlePublicKeyLable = new JLabel("Chave Pública");
        container.add(titlePublicKeyLable,gbc);

        gbc.gridx = 3;
        JLabel titlePrivateKeyLable = new JLabel("Chave Privada");
        container.add(titlePrivateKeyLable,gbc);


        gbc.gridx = 2;
        gbc.gridy = 1;
        publicKeyJText = new JTextField();
        publicKeyJText.setText("xxxxxxxxxxxxxxxxxxxxxx");
        container.add(publicKeyJText,gbc);

        gbc.gridx = 3;
        privateKeyJText = new JTextField();
        privateKeyJText.setText("xxxxxxxxxxxxxxxxxxxxxx");
        container.add(privateKeyJText,gbc);


        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleResultTriangularLable = new JLabel("Resultado");
        container.add(titleResultTriangularLable,gbc);


        gbc.gridy = 1;
        gbc.gridwidth = 2;

        resultTriangularJText = new JTextField();
        resultTriangularJText.setText("xxxxxxxxxxxxxxxxxxxxxx");
        container.add(resultTriangularJText,gbc);


//        gbc.gridx = 6;
//        JLabel multiplyTriangularLable = new JLabel("x10^"+multiplyFactor);
//        container.add(multiplyTriangularLable,gbc);

        return  container;
    }

    private Container createGaussianDistributionContainer() {

        Container container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleGaussianLable = new JLabel("Distribuição Gaussiana");
        container.add(titleGaussianLable,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        JLabel medianGaussianLable = new JLabel("Média");
        container.add(medianGaussianLable,gbc);

        gbc.gridy = 2;
        medianGaussianJText = new JTextField();
        medianGaussianJText.setText("000000");
        container.add(medianGaussianJText,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel stdGaussianLable = new JLabel("Desvio Padrão");
        container.add(stdGaussianLable,gbc);

        gbc.gridy= 2;
        stdGaussianJText = new JTextField();
        stdGaussianJText.setText("000000");
        container.add(stdGaussianJText,gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gaussianHistogramButton = createGaussianHistogramButton();
        gaussianHistogramButton.setEnabled(false);
        container.add(gaussianHistogramButton,gbc);

        return container;
    }

    private Button createGaussianHistogramButton() {
        Button gaussianHistogramButton = new Button("Histograma Gaussiano");
        gaussianHistogramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage histogramImg = Histogram.createHistogram(
                        "Histograma Gaussiano", gaussianDistribution.createHistogramData(), 900, 400, 81);

                HistogramWindow.createHistogramJFrame("Histograma Gaussiano", histogramImg).setVisible(true);
            }
        });
        return  gaussianHistogramButton;
    }

    private Container createUniformDistributionContainer() {

        Container container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel uniformDistributionTitleLable = new JLabel("Distribuição Uniforme Contínua");
        container.add(uniformDistributionTitleLable, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;

        JTextField uniformJText = new JTextField();
        resultUniform = uniformDistribution.getUniform();
        uniformJText.setText(String.format("%,.2f",resultUniform));
        container.add(uniformJText, gbc);
        medianGaussianJText.setText(String.format("%,.2f",resultUniform));

        gbc.gridy = 2;
        gbc.gridx = 0;
        uniformHistogramButton = createUniformHistogramButton();
        container.add(uniformHistogramButton,gbc);

        return container;
    }


    private Button createUniformHistogramButton() {
        Button uniformHistogramButton = new Button("Histograma Uniforme");
        uniformHistogramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage histogramImg = Histogram.createHistogram(
                        "Histograma Uniforme", uniformDistribution.createHistogramData(), 900, 400, 81);

                HistogramWindow.createHistogramJFrame("Histograma Uniforme", histogramImg).setVisible(true);
            }
        });
        return  uniformHistogramButton;
    }

    private Container createTriangleDistributionContainer() {

        Container container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel triangleDistributionTitleLable = new JLabel("Distribuição Triangular Contínua");
        container.add(triangleDistributionTitleLable, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;

        JLabel rangeStartLable = new JLabel("Origem");
        container.add(rangeStartLable, gbc);

        gbc.gridy = 2;
        container.add(rangeStartJText, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        triangularHistogramButton = createTriangularHistogramButton();
        triangularHistogramButton.setEnabled(false);
        container.add(triangularHistogramButton,gbc);




        return container;
    }


    private Button createTriangularHistogramButton() {
        Button triangularHistogramButton = new Button("Histograma Triangular Contínuo");
        triangularHistogramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage histogramImg = Histogram.createHistogram(
                        "Histograma Triangular Contínuo", triangleDistribution.createHistogramData(), 900, 400, 100        );
                HistogramWindow.createHistogramJFrame("Histograma Triangular Contínuo", histogramImg).setVisible(true);
            }
        });
        return  triangularHistogramButton;
    }

    private Container createDiscreteDistributionsContainer() {

        Container container = new Container();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = insets;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel discreteDistributionLable = new JLabel("Distribuições Discretas");
        container.add(discreteDistributionLable, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        JLabel uniformDiscreteLable = new JLabel("Distribuição Uniforme");
        container.add(uniformDiscreteLable, gbc);

        gbc.gridy = 2;

        JTextField uniformDiscreteJText = new JTextField();
        uniformDiscrete = (int)DiscreteDistribution.getUniformDiscrete();
        uniformDiscreteJText.setText(Integer.toString(uniformDiscrete));
        stdGaussianJText.setText(Integer.toString(uniformDiscrete));
        container.add(uniformDiscreteJText, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel triangleDiscreteLable = new JLabel("Distribuição Triangular");
        container.add(triangleDiscreteLable, gbc);

        gbc.gridy= 2;
        JTextField triangleDiscreteJText = new JTextField();
        triangleDiscrete = (int)DiscreteDistribution.getTriangleDiscrete();
        triangleDiscreteJText.setText(Integer.toString(triangleDiscrete));
        rangeStartJText.setText(Integer.toString(triangleDiscrete));
        container.add(triangleDiscreteJText, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        container.add(createUniformDiscreteHistogramButton(), gbc);

        gbc.gridx = 1;
        container.add(createTriangularDiscreteHistogramButton(), gbc);

        return container;
    }

    private Button createTriangularDiscreteHistogramButton() {
        Button triangularDiscreteHistogramButton = new Button("Histograma Triangular Discreto");
        triangularDiscreteHistogramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage histogramImg = Histogram.createHistogram("Histograma Triangular Discreto", DiscreteDistribution.createTriangleDiscreteHistogramData(), 900, 400, 100        );
                HistogramWindow.createHistogramJFrame("Histograma Triangular Discreto", histogramImg).setVisible(true);
            }
        });
        return  triangularDiscreteHistogramButton;
    }

    private Button createUniformDiscreteHistogramButton() {
        Button uniformDiscreteHistogramButton = new Button("Histograma Uniform Discreto");
        uniformDiscreteHistogramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage histogramImg2 = Histogram.createHistogram("Histograma Uniforme Discreto", DiscreteDistribution.createUniformDiscreteHistogramData(), 900, 400, 100        );
                HistogramWindow.createHistogramJFrame("Histograma Uniforme Discreto", histogramImg2).setVisible(true);
            }
        });
        return  uniformDiscreteHistogramButton;
    }

    private void createCalculateKeysButton() {

        calculateKeysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sliderEncryptValue = slider.getEncryptionValue();
                getContinuousDistributionValues();
                //medianGaussianJText.setText(Integer.toString(sliderValue));
                resultGaussianJText.setText(resultGaussian.toString());
                resultTriangularJText.setText(resultTriangular.toString());
                RSA.setRSAKeys(resultGaussian, resultTriangular, node, sliderEncryptValue);
                privateKeyJText.setText(node.getP().toString());
                publicKeyJText.setText(node.getQ().toString());
                gaussianHistogramButton.setEnabled(true);
                triangularHistogramButton.setEnabled(true);
                okButton.setEnabled(true);
            }
        });
    }

    private Button createOKButton() {
        okButton = new Button("Chat");
        okButton.setEnabled(false);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (node instanceof ServerNode) {
                    Thread thread = new Thread((ServerNode) node);
                    thread.start();
                }
                if (node instanceof ClientNode) {
                    Thread thread = new Thread((ClientNode) node);
                    thread.start();
                }

                frame.setVisible(false);

            }
        });
        return  okButton;
    }

    private void getContinuousDistributionValues(){

        // GAUSSIAN VALUE
        gaussianDistribution = new GaussianDistribution(resultUniform,uniformDiscrete);
        resultGaussian = BigDecimal.valueOf(gaussianDistribution.getGaussian() * Math.pow(2,slider.getEncryptionValue())).toBigInteger();
        triangleDistribution = new TriangleDistribution(triangleDiscrete);
        resultTriangular =  BigDecimal.valueOf(triangleDistribution.getTriangular() * Math.pow(2,slider.getEncryptionValue())).toBigInteger();
    }

    public void setCalculateKeysButtonEnable(){ calculateKeysButton.setEnabled(true);}



    public static void main(String[] args) {
//        KeyGeneratorWindow kgw = new KeyGeneratorWindow();
//        Frame frame = kgw.createWindow();
//        frame.setVisible(true);
    }


}
