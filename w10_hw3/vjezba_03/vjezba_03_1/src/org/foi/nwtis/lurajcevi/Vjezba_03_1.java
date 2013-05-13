package org.foi.nwtis.lurajcevi;

import org.foi.nwtis.lurajcevi.kvadrati.*;

public class Vjezba_03_1 {

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Trebam 3 argumenta.");
            System.exit(0);
        }
        int odBroja = 0;
        int doBroja = 0;
        try {
            odBroja = Integer.parseInt(args[0]);
            doBroja = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Molim unesite brojeve.");
            System.exit(0);
        }
        switch (args[2]) {
            case "0":
                Kvadrati kvad = new Kvadrati(odBroja, doBroja);
                kvad.ispis();
                break;
            case "1":
                NeparniKvadrati_1 nkvad1 = new NeparniKvadrati_1(odBroja, doBroja);
                nkvad1.ispis();
                break;
            case "2":
                Kvadrati nkvad2 = new NeparniKvadrati_1(odBroja, doBroja);
                nkvad2.ispis();
                break;
            case "3":
                Kvadrati nkvad3 = new NeparniKvadrati_2(odBroja, doBroja);
                nkvad3.ispis();
                break;
            case "4":
                NeparniKvadrati_3 nkvad4 = new NeparniKvadrati_3(odBroja, doBroja);
                nkvad4.ispisiPodatke();
                break;
            case "5":
                Ispisivac_1 nkvad5 = new NeparniKvadrati_3(odBroja, doBroja);
                nkvad5.ispisiPodatke();
                System.out.println(nkvad5.getClass().getName());
                break;
            case "6":
                Ispisivac_1 nkvad6 = new KolikoJeSati();
                nkvad6.ispisiPodatke();
                System.out.println(nkvad6.getClass().getName());
                break;
            case "7":
                Ispisivac_1 nkvad7 = NeparniKvadrati_5.kreirajIspisivac(odBroja, doBroja);
                nkvad7.ispisiPodatke();
                System.out.println(nkvad7.getClass().getName());
                break;
            default:
                System.out.println("Nepoznati unos");
                break;
        }
    }
}