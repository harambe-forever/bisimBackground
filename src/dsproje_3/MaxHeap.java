package dsproje_3;

import java.util.ArrayList;

/**
 *
 * @author gokbe
 */
public class MaxHeap {

    /*
    heap veri yapisi icin gereken degiskenler ve yapici metod
    */
    private Durak[] Heap;
    private int size;
    private int maxSize;

    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        Heap = new Durak[this.maxSize + 1];
        Heap[0] = new Durak("", 0, 0, 0);
    }

    private int parent(int poz) {
        return poz / 2;
    }

    private int leftChild(int poz) {
        return 2 * poz;
    }

    private int rightChild(int poz) {
        return 2 * poz + 1;
    }

    /*
    node'un dogru yerde oldugunu check eden metod
    */
    private boolean daldaMi(int poz) {
        if (poz >= (size / 2) && poz <= size) {
            return true;
        }
        return false;
    }

    /*
    silme - ekleme islemlerinden sonra gerek varsa swap edilmelerini saglayan 
    metod
    */
    private void swap(int ilkDrm, int ikinciDrm) {
        Durak temp;
        temp = Heap[ilkDrm];
        Heap[ilkDrm] = Heap[ikinciDrm];
        Heap[ikinciDrm] = temp;
    }

    /*
    silme isleminden sonra ilgili elemanlarin yukari alinmasindan sorumlu metod
    */
    private void yukariAl(int poz) {
        if (daldaMi (poz)) {
            return;
        }

        if (Heap[poz].getNb() < Heap[leftChild(poz)].getNb()
                || Heap[poz].getNb() < Heap[rightChild(poz)].getNb()) {
            if (Heap[leftChild(poz)].getNb() > Heap[rightChild(poz)].getNb()) {
                swap(poz, leftChild(poz));
                yukariAl(leftChild(poz));
            } else {
                swap(poz, rightChild(poz));
                yukariAl(rightChild(poz));
            }
        }
    }

    public void ekle(Durak element) {
        Heap[++size] = element;
        int current = size;
        while (Heap[current].getNb() > Heap[parent(current)].getNb()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void yazdir() {
        for (int i = 1; i <= size / 2; i++) {
            System.out.println(" Tepesi: " + Heap[i].getNb() + " Sol: "
                    + Heap[2 * i].getNb() + " Sag: " + Heap[2 * i + 1].getNb());
            System.out.println();
        }
    }

    public Durak maxAl() {
        Durak pop = Heap[1];
        Heap[1] = Heap[size--];
        yukariAl(1);
        return pop;
    }

    public static void main(String[] args) {
        String[] duraklar = {"Inciralti, 28, 2, 10",
            "Sahilevleri, 8, 1, 11",
            "Dogal Yasam Parki, 17, 1, 6",
            "Bostanli Iskele, 7, 0, 5",
            "Uckuyular Iskele, 10, 1, 9",
            "Pasaport Iskele, 9, 1, 12",
            "Fahrettin Altay, 7, 1, 4",
            "Goztepe Kopru, 14, 6, 16",
            "Guzelbahce, 10, 5, 5"};

        ArrayList<Durak> duraklARR = new ArrayList<>();

        /*
        For dongusu icinde yukaridaki diziden alinan elemanlari sahalarina ayiran
        ve maxHeap'a atayan islem
        */
        for (String duraklar1 : duraklar) {
            String[] duraklarray = duraklar1.split(", ");
            duraklARR.add(new Durak(duraklarray[0], Integer.parseInt(duraklarray[1]),
                    Integer.parseInt(duraklarray[2]), Integer.parseInt(duraklarray[3])));
        }

        MaxHeap maxHeap = new MaxHeap(duraklARR.size());

        for (int i = 0; i < duraklARR.size(); i++) {
            maxHeap.ekle(duraklARR.get(i));
        }

        maxHeap.yazdir();

        System.out.println("Max 3lü: ");
        for (int j = 0; j < 3; j++) {
            maxHeap.maxAl().yazdir();
        }

        System.out.println();
        maxHeap.yazdir();

    }
}
