import Controlador.Ex1;
import Controlador.Ex2;
import Controlador.Ex3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------- MENU DANI GELABERT POL ---------------");
        System.out.println("1. - Num. Guardies Residents");
        System.out.println("2. - Urgències amb Monitor");
        System.out.println("3. - Urgències amb Semàfor");
        System.out.println("4. - Sortir de l'aplicació");
        System.out.print("Elige una opció: ");

        int menu = scanner.nextInt();

        switch (menu) {
            case 1:
                Ex1.main(new String[0]);
                break;
            case 2:
                Ex2.main(new String[0]);
                break;
            case 3:
                Ex3.main(new String[0]);
                break;
            case 4:
                System.out.println("Acabat");
                break;
            default:
                System.out.println("Fi");
        }

        scanner.close();
    }
}
