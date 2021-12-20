package dsproje_3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author gokbe
 */
public class Durak {

    /*
    Durak degiskenlerinin ve get-set metodlarının olusturulması
    */
    String durakAdi;
    private int bp;
    private int tb;
    private int nb;
    ArrayList<Musteri> musteriList = new ArrayList<Musteri>();

    public Durak(String durakAdi, int bp, int tb, int nb) {
        this.durakAdi = durakAdi;
        this.bp = bp;
        this.tb = tb;
        this.nb = nb;
    }

    public int getBp() {
        return bp;
    }

    public int getTb() {
        return tb;
    }

    public int getNb() {
        return nb;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }

    public void setTb(int tb) {
        this.tb = tb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    /*
    Durak icine eklenecek olan genericList'in musterilerini rastgele sayıda ve
    rastgele id'de olusturacak metod. Bu metodda ayrıca normal bisiklet veya 
    tandem bisikletin bitirilmesini rastgele bir duruma bagladım ki biraz daha
    gercekci bir durum gorelim.
    */
    public void musteriler() {
        Random rand = new Random();
        int musteri = rand.nextInt(10);
        for (int i = 0; i < musteri; i++) {
            musteriList.add(new Musteri((rand.nextInt(19) + 1), rand.nextInt(24), rand.nextInt(60)));
        }
        if (musteri > getBp()) {
            setBp(getBp() + (getNb() + getTb()));
            setNb(0);
            setTb(0);
        } else if (musteri < getBp()) {
            setBp(getBp() + musteri);
        }

        int bisiklet = rand.nextInt(2);
        if (musteri < (getNb() + getTb())) {
            if (bisiklet == 0) { // tandemli bitsin
                for (int t = 0; t < musteri; t++) {
                    if (getTb() == 0) {
                        nb--;
                    } else {
                        tb--;
                    }
                    setNb(getNb());
                    setTb(getTb());
                }
            } else { // normal bitsin
                for (int n = 0; n < musteri; n++) {
                    if (getNb() == 0) {
                        tb--;
                    } else {
                        nb--;
                    }
                }
            }
        } else if (musteri > (getNb() + getTb()) || musteri == (getNb() + getTb())) {
            setNb(0);
            setTb(0);
            System.out.println("Bisiklet Bitti. Sonradan gelenler kuyruga girdi");
        }

    }
    
    /*
    Formatli yazdirma metodu 
    */

    public void yazdir() {
        for (int j = 0; j < 50; j++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("%-10s %-20s\n %-10s %-3s\n %-10s %-3s\n %-10s %-3s\n", "Durak Adi:", durakAdi, "Bos Park:",
                getBp(), "Tandem B.:", getTb(), "Normal B.:", getNb());
        System.out.println("Musterilerden Sonra Durak Bilgileri");
        musteriler();
        System.out.println("Musteri sayisi: " + musteriList.size());
        System.out.printf("%-10s %-3s\n %-10s %-3s\n %-10s %-3s\n", "Bos Park:", getBp(), "Tandem B.:", getTb(),
                "Normal B.:", getNb());
        System.out.println("\nMusteri Listesi:");
        for (int i = 0; i < musteriList.size(); i++) {
            System.out.println("Musteri ID: " + musteriList.get(i).getId() + "\tSaat: " + musteriList.get(i).getSaat()
                    + ":" + musteriList.get(i).getDakika());
        }
    }

    public static void main(String[] args) {
        String[] duraklar = { "Inciralti, 28, 2, 10", "Sahilevleri, 8, 1, 11", "Dogal Yasam Parki, 17, 1, 6",
                "Bostanli Iskele, 7, 0, 5", "Uckuyular Iskele, 10, 1, 9", "Pasaport Iskele, 9, 1, 12",
                "Fahrettin Altay, 7, 1, 4", "Goztepe Kopru, 14, 6, 16", "Guzelbahce, 10, 5, 5" };

        Tree tree = new Tree();

        /*
        For dongusu icinde yukaridaki diziden alinan elemanlari sahalarina ayiran
        ve tree'ye atayan islem
        */
        for (int i = 0; i < duraklar.length; i++) {
            String[] duraklARR = duraklar[i].split(", ");
            tree.insert(new Durak(duraklARR[0], Integer.parseInt(duraklARR[1]), Integer.parseInt(duraklARR[2]),
                    Integer.parseInt(duraklARR[3])));
        }

        /*
        tree'yi inOrder sekilde yazdirdim.
        */
        tree.inOrder(tree.getRoot());
        /*
        agacin derinligi
        */
        System.out.println("\nAGACIN DERINLIGI: " + tree.derinlik() + "\n");

        Scanner sc = new Scanner(System.in);

        System.out.println("Musteri Bulmak icin ID numarasi giriniz:");
        int id = sc.nextInt();
        tree.musteriBul(id, tree.getRoot());

        System.out.println("Durak adi giriniz: ");
        String durakAdi = getDurak();

        System.out.println("ID numarasi giriniz: ");
        int idNum = getIdNum();

        tree.kiralamaIslemi(durakAdi, idNum).print();

    }

    static String getDurak() {
        Scanner sc = new Scanner(System.in);
        String durakAdi = sc.nextLine();
        return durakAdi;
    }

    static int getIdNum() {
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        return id;
    }
}
