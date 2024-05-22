package Controlador;

import java.util.Random;
import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Quants feligresos hi han?: ");
        int nFeligresos = scanner.nextInt();

        System.out.print("Quants turistes hi han?: ");
        int nTuristes = scanner.nextInt();

        for (int i = 1; i <= nTuristes; i++) {
            int waitTime = random.nextInt(15) + 1;
            Turista turista = new Turista(monitor, i, waitTime * 1000);
            Thread threadsTurista = new Thread(turista);
            threadsTurista.start();
        }

        for (int i = 1; i <= nFeligresos; i++) {
            int waitTime = random.nextInt(15) + 1;
            Feligres feligres = new Feligres(monitor, i, waitTime * 1000);
            Thread threadsFeligres = new Thread(feligres);
            threadsFeligres.start();
        }
    }
}

class Turista implements Runnable {
    private Monitor monitor;
    private int id;
    private int waitTime;

    public Turista(Monitor monitor, int id, int waitTime) {
        this.monitor = monitor;
        this.id = id;
        this.waitTime = waitTime;
    }

    public void run() {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Turista " + id + " anant al Basilica...Temps d'arribada: " + (waitTime / 1000) + " segons.");
        monitor.openReading();
        System.out.println("Turista " + id + " agafant torn per entrar a la Basilica");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Turista " + id + " marxant a comprar un gelat...");
        monitor.closeReading();
    }
}


class Feligres implements Runnable {
    private Monitor monitor;
    private int id;
    private int waitTime;

    public Feligres(Monitor monitor, int id, int waitTime) {
        this.monitor = monitor;
        this.id = id;
        this.waitTime = waitTime;
    }

    public void run() {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Feligres " + id + " anant a la Basílica...Temps d'arribada: " + (waitTime / 1000) + " segons.");
        monitor.openWriting();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Feligres " + id + " agafant torn per entrar a la Basílica.");
        monitor.closeWriting();

        System.out.println("Feligres " + id + " sortint de la Basílica.");
        monitor.openWriting();
        System.out.println("Feligres " + id + " agafant torn per entrar a la Basílica");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Feligres " + id + " sortint de la Basílica");
        monitor.closeWriting();
    }
}
