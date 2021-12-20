package dsproje_3;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author gokbe
 */
public class HashTable {

    public static void main(String[] args) {
        String[] duraklar = { "Inciralti, 28, 2, 10", "Sahilevleri, 8, 1, 11", "Dogal Yasam Parki, 17, 1, 6",
                "Bostanli Iskele, 7, 0, 5", "Uckuyular Iskele, 10, 1, 9", "Pasaport Iskele, 9, 1, 12",
                "Fahrettin Altay, 7, 1, 4", "Goztepe Kopru, 14, 6, 16", "Guzelbahce, 10, 5, 5" };

        ArrayList<Durak> duraklARR = new ArrayList<>();

                /*
        For dongusu icinde yukaridaki diziden alinan elemanlari sahalarina ayiran
        ve ArrayList'e atayan islem
        */
        for (String duraklar1 : duraklar) {
            String[] duraklarray = duraklar1.split(", ");
            duraklARR.add(new Durak(duraklarray[0], Integer.parseInt(duraklarray[1]), Integer.parseInt(duraklarray[2]),
                    Integer.parseInt(duraklarray[3])));
        }

        /*
        hashtable'in olusturulmasi
        */
        Hashtable<String, Integer> ht = new Hashtable();

        /*
        arraylist icinden hashTable'a yerlestirme islemi
        */
        for (int i = 0; i < duraklARR.size(); i++) {
            ht.put(duraklARR.get(i).durakAdi, duraklARR.get(i).getNb());
        }

        System.out.printf("%-20s %-10s\n", "DURAK ADI", "NORMAL BISIKLET");

        for (Map.Entry m : ht.entrySet()) {
            System.out.printf("%-20s %-10s", m.getKey(), m.getValue());
            System.out.println();
        }

        for (int j = 0; j < duraklARR.size(); j++) {
            if (duraklARR.get(j).getBp() > 5) {
                duraklARR.get(j).setBp(duraklARR.get(j).getBp() - 5);
                duraklARR.get(j).setNb(duraklARR.get(j).getNb() + 5);
            }
        }

        /*
         * Hashtable key-value olarak tasarlandigi icin duz bir sekilde eklemek tabloyu
         * guncelleyecektir
         */
        for (int k = 0; k < duraklARR.size(); k++) {
            ht.put(duraklARR.get(k).durakAdi, duraklARR.get(k).getNb());
        }

        System.out.println("---------------Duzenlenmis Hali---------------");
        System.out.printf("%-20s %-10s\n", "DURAK ADI", "NORMAL BISIKLET");
        for (Map.Entry m : ht.entrySet()) {
            System.out.printf("%-20s %-10s", m.getKey(), m.getValue());
            System.out.println();
        }
    }

}
