package pi.distribution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class GaussianDistributionTable {

    private double[][] gaussianTable = new double[40][10];

    public GaussianDistributionTable() {
        parseTable();
    }

    public double getTableValue(double z){
        double result = 0.0;
        if (z < -3.99) return 0.0;
        if (z > 3.99) return 1.0;
        if (z >= 0) {
            int number = (int)(z*10);
            int y = Math.abs((int)(((int)z-z)*10));
            result = gaussianTable[number][Math.abs((int)(((int)(z*10)-(z*10))*10))];
        } else {
            int number = (int)(Math.abs(z)*10);
            result = 1 - gaussianTable[number][Math.abs((int)(((int)(z*10)-(z*10))*10))];
        }
        return result;
    }


    private void parseTable() {
        try{
            Scanner scanner = new Scanner(new File("tabela_distribuicao_normal.txt"));
            int x = 0;
            int y = 0;
            while(scanner.hasNextLine()){
                String[] line = scanner.nextLine().split(" ");
                for (int i = 0; i < line.length; i++){
                    gaussianTable[y][x] = Double.parseDouble(line[x]);
                    x++;
                }
                x = 0;
                y++;
            }

        }catch (FileNotFoundException e){
            System.out.println(e);
        }



    }

    public String gaussianTableToString() {
        String result = "";
        for (int y = 0; y < gaussianTable.length; y++) {
            for (int x = 0; x < gaussianTable[0].length; x++) {
                result += ("["+gaussianTable[y][x]+"]");
            }
            result += ("\n");
        }
        return result;
    }



    public static void main(String[] args) {

        GaussianDistributionTable gdt = new GaussianDistributionTable();

            gdt.parseTable();

        System.out.println(gdt.gaussianTableToString());
        System.out.println(gdt.getTableValue(2.568965));

    }


}
