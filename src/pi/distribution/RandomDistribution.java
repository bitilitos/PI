package pi.distribution;

import java.util.Random;

public class RandomDistribution {
    private static final int ProbMax = 1;
    private final int range;
    private int[] domain;
    private double[] domainProbability;


    public RandomDistribution(int range, int rangeStartsAt) {
        this.range = range;
        domain = new int[range];
        domainProbability = new double[range];
        createDomain(rangeStartsAt);
        createDistribution();
    }

    private void createDistribution() {
        int probSum = 0;
        double average = ProbMax / range;

        // SE A PROBABILIDADE AINDA NÃO FOR 1

        for (int i = 0; i < range; i++) {
            if (probSum != ProbMax) {
                //Se não for o último elemento
                if (i != range - 1) {
                    Random random = new Random();
                    // nextGaussian()*desvioPadrão+media
                    double prob = random.nextGaussian();

                    // NÃO PODE ULTRAPASSAR 1
                    if (!(probSum + prob > ProbMax)) {
                        domainProbability[i] = prob;
                    } else {
                        domainProbability[i] = ProbMax - probSum;
                    }
                    probSum += domainProbability[i];

                } else {
                    // Último elemento
                    domainProbability[i] = ProbMax - probSum;
                }

            }else System.out.println("Probabilidade máxima atingida antes do fim do Domínio");
        }

    }

    private void createDomain(int rangeStartsAt) {
        for (int i = 0, n = rangeStartsAt; i < range; i++, n++) {
            domain[i] = n;
        }
    }

    private void printResult(){
        for (int i = 0; i < range; i++){
            System.out.println(domain[i] + " : " + domainProbability[i]);
        }

    }

    public static void main(String[] args) {
        RandomDistribution rd = new RandomDistribution(5, 95);
        rd.printResult();
    }


}
