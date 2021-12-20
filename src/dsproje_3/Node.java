package dsproje_3;

/**
 *
 * @author gokbe
 */
public class Node {

    public Durak durak;
    public Node leftChild;
    public Node rightChild;

    public void displayNode() {
        durak.yazdir();
    }
    public void print(){
        System.out.printf("%-10s %-20s\n %-10s %-3s\n %-10s %-3s\n %-10s %-3s\n", "Durak Adi:", durak.durakAdi,
                "Bos Park:", durak.getBp(), "Tandem B.:", durak.getTb(), "Normal B.:", durak.getNb());
        System.out.println("\nMusteri Listesi:");
        System.out.println("Musteri sayisi: " + durak.musteriList.size());
        for (int i = 0; i < durak.musteriList.size(); i++) {
            System.out.println("Musteri ID: " + durak.musteriList.get(i).getId() + "\tSaat: "
                    + durak.musteriList.get(i).getSaat() + ":" + durak.musteriList.get(i).getDakika());
        }
    }
}
