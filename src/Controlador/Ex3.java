package Controlador;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Ex3 {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Quants feligresos hi han?: ");
        int nFeligresos = scanner.nextInt();

        System.out.print("Quants turistes hi han?: ");
        int nTuristes = scanner.nextInt();


        for (int i = 1; i <= nTuristes; i++) {
            int waitTime = random.nextInt(15) + 1;
            TuristaEx3 turista = new TuristaEx3(semaphore, i, waitTime * 1000);
            Thread threadsTurista = new Thread(turista);
            threadsTurista.start();
        }

        for (int i = 1; i <= nFeligresos; i++) {
            int waitTime = random.nextInt(15) + 1;
            FeligresEx3 feligres = new FeligresEx3(semaphore, i, waitTime * 1000);
            Thread threadsFeligres = new Thread(feligres);
            threadsFeligres.start();
        }
    }
}

class TuristaEx3 implements Runnable {
    private Semaphore semaphore;
    private int id;
    private int waitTime;

    public TuristaEx3(Semaphore semaphore, int id, int waitTime) {
        this.semaphore = semaphore;
        this.id = id;
        this.waitTime = waitTime;
    }

    public void run() {
        try {
            Thread.sleep(waitTime);
            if (waitTime>=6000) {
                System.out.println("Turista " + id + " s'ha mort");
            } else {
                semaphore.acquire();
                System.out.println("Turista " + id + " anant al Basilica...Temps d'arribada: " + (waitTime / 1000) + " segons.");
                System.out.println("Turista " + id + " marxant a comprar un gelat...");
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


    class FeligresEx3 implements Runnable {
        private Semaphore semaphore;
        private int id;
        private int waitTime;

        public FeligresEx3(Semaphore semaphore, int id, int waitTime) {
            this.semaphore = semaphore;
            this.id = id;
            this.waitTime = waitTime;
        }

        public void run() {
            try {
                Thread.sleep(waitTime);
                if (waitTime>=6000) {
                    System.out.println("Feligres " + id + " s'ha mort");
                } else {
                    semaphore.acquire();
                    System.out.println("Feligres " + id + " anant a la Basílica...Temps d'arribada: " + (waitTime / 1000) + " segons.");
                    System.out.println("Feligres " + id + " agafant torn per entrar a la Basílica.");
                    semaphore.release();

                    System.out.println("Feligres " + id + " sortint de la Basílica.");

                    semaphore.acquire();

                    System.out.println("Feligres " + id + " agafant torn per entrar a la Basílica");

                    System.out.println("Feligres " + id + " sortint de la Basílica");
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
