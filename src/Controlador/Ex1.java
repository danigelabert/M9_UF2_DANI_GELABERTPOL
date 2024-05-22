package Controlador;

import java.util.Arrays;
import java.util.Random;

public class Ex1 implements Runnable{
    private int idComunitat;
    private Random aleatorio;
    private int dadesDau;
    private static int[] totalTiradesDau = new int[3];

    public Ex1(int idComunitat) {
        this.idComunitat = idComunitat;
        this.aleatorio = new Random();
    }

    public void run() {
        dadesDau = aleatorio.nextInt(6) + 1;
        totalTiradesDau[idComunitat] = dadesDau;
    }


    public static void main(String[] args) throws InterruptedException {

        Thread fil1 = new Thread(new Ex1(0));
        Thread fil2 = new Thread(new Ex1(1));
        Thread fil3 = new Thread(new Ex1(2));

        fil1.setName("Catolics");
        fil2.setName("Ortodoxes");
        fil3.setName("Coptes");

        fil1.start();
        fil2.start();
        fil3.start();

        fil1.join();
        fil2.join();
        fil3.join();

        System.out.println("Tirada dau " + fil1.getName() +": " +totalTiradesDau[0]);
        System.out.println("Tirada dau " + fil2.getName() +": " +totalTiradesDau[1]);
        System.out.println("Tirada dau " + fil3.getName() +": " +totalTiradesDau[2]);

        double mitja = (totalTiradesDau[0] + totalTiradesDau[1] + totalTiradesDau[2]) / 3.0;
        System.out.println("Mitjana de les tirades: "+mitja);

        Arrays.sort(totalTiradesDau);
        int mediana = (totalTiradesDau[0] + totalTiradesDau[1] + totalTiradesDau[2]) / 3;

        System.out.println("Número d'hores totals: " + mediana);
    }
}
