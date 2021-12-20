package dsproje_3;

/**
 *
 * @author gokbe
 */
public class Musteri {

    /*
    Musteri sinifi icin gerekenler
    */
    private int id;
    private int saat;
    private int dakika;

    public Musteri(int id, int saat, int dakika) {
        this.id = id;
        setSaat(saat);
        setDakika(dakika);
    }

    /*
    Olmaz ama, olursa diye kontrollu sekilde tasarlanan get ve set metodlari
    */
    public void setSaat(int saat) {
        if (saat < 0 || saat > 24) {
            this.saat = 0;
        } else {
            this.saat = saat;
        }
    }

    public void setDakika(int dakika) {
        if (dakika < 0 || dakika > 60) {
            this.dakika = 0;
        }else if(dakika < 10){
            this.dakika = dakika + 10;
        }else{
            this.dakika = dakika;
        }
    }

    public int getSaat() {
        return saat;
    }

    public int getDakika() {
        return dakika;
    }
    public int getId() {
        return id;
    }
}
