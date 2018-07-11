package M1.Models;

import java.awt.*;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class Arc<T> {

    private Noeud<T> debut;
    private Noeud<T> fin;
    private int flot;
    private int poids;
    private int delta;
    private int xDebut;
    private int yDebut;
    private int xFin;
    private int yFin;
    private String color = "0;0;0";
    private String couleur = "BLANC";

    public Arc(Noeud<T> debut, Noeud<T> fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public Arc(Noeud<T> debut, Noeud<T> fin, int poids) {
        this.debut = debut;
        this.fin = fin;
        this.poids = poids;
    }

    public Arc() {}

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public Noeud<T> getDebut() {
        return debut;
    }

    public void setDebut(Noeud<T> debut) {
        this.debut = debut;
    }

    public Noeud<T> getFin() {
        return fin;
    }

    public void setFin(Noeud<T> fin) {
        this.fin = fin;
    }

    public int getxDebut() {
        return debut.getPosX() + 25;
    }

    public void setxDebut(int xDebut) {
        this.xDebut = xDebut;
    }

    public int getyDebut() {
        return debut.getPosY() + 25;
    }

    public void setyDebut(int yDebut) {
        this.yDebut = yDebut;
    }

    public int getxFin() {
        return fin.getPosX() + 25;
    }

    public void setxFin(int xFin) {
        this.xFin = xFin;
    }

    public int getyFin() {
        return fin.getPosY() + 25;
    }

    public void setyFin(int yFin) {
        this.yFin = yFin;
    }

    public int[] getColorRGB() {
        String[] colors = this.color.split(";");
        return new int[]{Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2])};
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getFlot() {
        return flot;
    }

    public void setFlot(int flot) {
        this.flot = flot;
    }


    public void dessiner(Graphics g, boolean afficheFlot) {
        int x1 = this.getxDebut(), y1 = this.getyDebut();
        int x2 = this.getxFin(), y2 = this.getyFin();
        int xInter = (x1 + +4 * x2) / 5, yInter = (y1 + 4 * y2) / 5;
        int epsilon = 5;
        g.drawLine(x1, y1, xInter, yInter);
        g.setColor(Color.RED);
        g.drawLine(xInter, yInter, x2, y2);
        if ((x2 - x1) * (y2 - y1) < 0) {
            epsilon = 10;
        }
        g.setColor(Color.BLACK);

        if(afficheFlot){
            g.drawString(this.getFlot() + "/" + this.getPoids(), (x1 + x2) / 2 + epsilon, (y1 + y2) / 2 - 2);
        } else {
            g.drawString("" + this.getPoids(), (x1 + x2) / 2 + epsilon, (y1 + y2) / 2 - 2);
        }

    }

    public void dessinerOrdonnancement(Graphics g) {
        int[] colors = this.getColorRGB();
        g.setColor(new Color(colors[0], colors[1], colors[2]));
        int x1 = this.getxDebut() - 25, y1 = this.getyDebut() - 25;
        int x2 = this.getxFin() - 25, y2 = this.getyFin() - 25;
        g.drawLine(x1 + 100, y1 + 25, x2, y2 + 25);
        g.drawString("" + this.getPoids(), (x1 + x2) / 2 + 30, (y1 + y2) / 2 + 20);
    }


}

