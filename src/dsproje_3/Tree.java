package dsproje_3;

import java.util.Random;

/**
 *
 * @author gokbe
 */
public class Tree {
    /*
    Tree sinifi icin gereken degiskenler ve yapici metod
    */

    private Node root;
    private int solNode;
    private int sagNode;

    public Tree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    /*
    durak isimlerinin ilk harfini karsilastiran ve ona gore tree'ye ekleyen 
    metod
    */
    public void insert(Durak newData) {
        Node newNode = new Node();
        newNode.durak = newData;
        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (newData.durakAdi.compareTo(current.durak.durakAdi) < 0) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        solNode++;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        sagNode++;
                        return;
                    }
                }
            }
        }
    }

    /*
    inOrder sirada yazdirmayi saglayan metod
    */
    public void inOrder(Node currentRoot) {
        if (currentRoot != null) {
            inOrder(currentRoot.leftChild);
            currentRoot.displayNode();
            inOrder(currentRoot.rightChild);
        }
    }

    /*
    derinligi donduren metod
    */
    public int derinlik() {
        if (sagNode > solNode) {
            return sagNode;
        } else {
            return solNode;
        }

    }

    /*
    agac icinde durak adlarini arayarak, istenen durak bulundugunda o duraga
    kullanicinin verdigi id numarali musteriyi rastgele saatte ekleyen metod
    */
    public Node kiralamaIslemi(String durakAdi, int id) {
        Random rand = new Random();
        Node current = root;
        while (!current.durak.durakAdi.equals(durakAdi)) {
            if (durakAdi.compareTo(current.durak.durakAdi) < 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }

            if (current == null) {
                return null;
            }
        }
        if (current.durak.getNb() == 0) {
            current.durak.setNb(0);
        } else {
            current.durak.setNb(current.durak.getNb() - 1);
        }

        current.durak.setBp(current.durak.getBp() + 1);
        current.durak.musteriList.add(new Musteri(id, rand.nextInt(24), rand.nextInt(60)));

        return current;
    }

    /*
    agac icindeki tum node'lar, gezerek gezinen node icindeki genericList icinde
    kullanicinin girdigi musteri id varsa o musteriye ait bilgileri ekrana 
    yazdiran metod
    */
    public void musteriBul(int id, Node node) {

        if (node == null) {
            return;
        }
        musteriBul(id, node.leftChild);
        for (int i = 0; i < node.durak.musteriList.size(); i++) {
            if (node.durak.musteriList.get(i).getId() == id) {
                System.out.println("Durak Adi: " + node.durak.durakAdi + " Saat "
                        + node.durak.musteriList.get(i).getSaat() + ":" + node.durak.musteriList.get(i).getDakika());
            }
        }
        musteriBul(id, node.rightChild);
    }

}
