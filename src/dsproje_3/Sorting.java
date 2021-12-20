package dsproje_3;

import java.util.Random;

/**
 *
 * @author gokbe
 */
public class Sorting {

    /*
    random sayi generator
    */
    public int[] randomSayi(int kacTane) {
        Random rand = new Random();
        int[] liste = new int[kacTane];
        for (int i = 0; i < liste.length; i++) {
            liste[i] = rand.nextInt(100);
        }
        return liste;
    }

    public int[] bubbleSort(int[] liste) {
        for (int i = 0; i < liste.length; i++) {
            for (int j = 0; j < liste.length - 1; j++) {
                if (liste[j] > liste[j + 1]) {
                    int temp = liste[j];
                    liste[j] = liste[j + 1];
                    liste[j + 1] = temp;
                }
            }
        }
        return liste;
    }

    public int bolum(int[] liste, int min, int max) {
        int ayrac = liste[max];
        int index = (min - 1);
        for (int i = min; i < max; i++) {
            if (liste[i] < ayrac) {
                index++;
                int temp = liste[index];
                liste[index] = liste[i];
                liste[i] = temp;
            }
        }

        int temp = liste[index + 1];
        liste[index + 1] = liste[max];
        liste[max] = temp;

        return index + 1;
    }

    public int[] sort(int[] liste, int min, int max) {
        if (min < max) {
            int bolumIndex = bolum(liste, min, max);
            sort(liste, min, bolumIndex - 1);
            sort(liste, bolumIndex + 1, max);
        }
        return liste;
    }

    public void print(int[] randomListe, int[] siraliListe) {
        System.out.println("Normal Sirali");
        if (randomListe.length > 101) {
            return;
        } else {
            for (int i = 0; i < randomListe.length; i++) {
                System.out.printf("%-6s %-6s\n", randomListe[i], siraliListe[i]);
            }
        }

    }

    public static void main(String[] args) {
        Sorting s1 = new Sorting();

        System.out.println("BUBBLE SORT");
        int[] randomListe = s1.randomSayi(10);
        int[] randomListePrint = new int[randomListe.length];
        randomListePrint = randomListe.clone();
        long baslangic = System.nanoTime();
        int[] siraliListe = s1.bubbleSort(randomListe);
        long bitis = System.nanoTime();

        s1.print(randomListePrint, siraliListe);
        System.out.println("Calisma suresi: " + (bitis - baslangic) + "\n");

        System.out.println("QUICK SORT");
        Sorting s2 = new Sorting();
        randomListe = s2.randomSayi(10);
        randomListePrint = randomListe.clone();

        baslangic = System.nanoTime();
        siraliListe = s2.sort(randomListe, 0, randomListe.length - 1);
        bitis = System.nanoTime();

        s2.print(randomListePrint, siraliListe);
        System.out.println("Calisma suresi: " + (bitis - baslangic));
    }
}
