package M1.Models;

import java.awt.*;

import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class Noeud<T> {

    //Detail
    private T x;
    private String couleur = "BLANC";
    private String color = "0;0;0";
    private float poids = Float.POSITIVE_INFINITY;
    private int degre;
    private int niveau = 0;
    //Ordonnancement
    private int duree;
    private int dureePlusTot;
    private int dureePlusTard;
    //Position
    private int posX;
    private int posY;
    private int dragX = 0;
    private int dragY = 0;

    public Noeud() {}

    public Noeud(T x) {
        this.x = x;
    }

    public Noeud(T x, int degre) {
        this.x = x;
        this.setDegre(degre);
    }

    public Noeud(T x, int poids, int duree, String s) { //Constructeur pour l'ordonnancement
        this.x = x;
        this.poids = poids;
        this.duree = duree;
        this.poids = poids;
        this.duree = duree;
    }

    public Noeud(int posX, int posY, T x, int degre) {
        this.x = x;
        this.degre = degre;
        this.posX = posX;
        this.posY = posY;
    }

    public Noeud(int posX, int posY, T x) {
        this.x = x;
        this.posX = posX;
        this.posY = posY;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getDegre() {
        return degre;
    }

    public void setDegre(int degre) {
        this.degre += degre;
    }

    public String getColor() {
        return color;
    }

    public int[] getColorRGB() {
        String[] colors = this.color.split(";");
        return new int[]{Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2])};
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDureePlusTot() {
        return dureePlusTot;
    }

    public void setDureePlusTot(int dureePlusTot) {
        this.dureePlusTot = dureePlusTot;
    }

    public int getDureePlusTard() {
        return dureePlusTard;
    }

    public void setDureePlusTard(int dureePlusTard) {
        this.dureePlusTard = dureePlusTard;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getDragX() {
        return dragX;
    }

    public void setDragX(int dragX) {
        this.dragX = dragX;
    }

    public int getDragY() {
        return dragY;
    }

    public void setDragY(int dragY) {
        this.dragY = dragY;
    }

    public void dessiner(Graphics g) {
        int[] colors = this.getColorRGB();
        g.setColor(new Color(colors[0], colors[1], colors[2]));
        g.fillOval(this.getPosX(), this.getPosY(), 80, 80);
        Font font = new Font("Courier", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("" + this.getX(), this.getPosX() + 35, this.getPosY() + 45);
    }

    public void initialize(List<Noeud<T>> noeuds) {
        for(Noeud n : noeuds){
            n.setCouleur("BLANC");
        }
    }

    public void dessinerOrdonnancement(Graphics g) {
        int x = this.getPosX(), y = this.getPosY();
        String nomTache = this.getX().toString();
        g.drawRect(x, y, 100, 50);
        g.drawLine(x + 50, y + 25, x + 50, y + 50);
        g.drawLine(x, y + 25, x + 100, y + 25);

        Font font = new Font("Courier", Font.BOLD, 17);
        g.setFont(font);
        g.drawString(nomTache, (x + 50) - (nomTache.length() * nomTache.length()), y + 17);
        g.drawString("" + this.getDureePlusTot(), x + 2, y + 45);
        g.drawString("" + this.getDureePlusTard(), x + 52, y + 45);
    }

}