package M1.Service.Coloriage;

import M1.Models.Arc;
import M1.Models.Graphe;
import M1.Models.Noeud;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class ColoriageService<T> {

    //COLORIAGE GRAPHE
    public void coloriage(Graphe g) throws Exception {
        List<Noeud<T>> noeuds = new ArrayList(this.classementDegre(g).getNoeuds());
        String[] couleur = new String[]{"214;15;15", "29;158;27", "27;116;158"};
        int indiceColor = 0;
        while (!noeuds.isEmpty()) {
            noeuds.get(0).setColor(couleur[indiceColor]);
            List<Noeud<T>> voisins = this.voisinNoeud(noeuds.get(0), g);
            noeuds.remove(noeuds.get(0));
            for (Noeud noeud : noeuds) {
                if (!voisins.contains(noeud)) {
                    noeud.setColor(couleur[indiceColor]);
                    voisins.addAll(this.voisinNoeud(noeud, g));
                }
            }
            this.removeNoeudColore(noeuds);
            indiceColor++;
        }
    }

    //CLASSEMENT SOMMET PAR ORDRE DECROISSANT DE DEGRE
    public Graphe classementDegre(Graphe g) {
        List<Noeud<T>> noeuds = g.getNoeuds();
        Noeud<T> temp;
        for (int i = 0; i < noeuds.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (noeuds.get(j).getDegre() > noeuds.get(j - 1).getDegre()) {
                    temp = noeuds.get(j);
                    noeuds.set(j, noeuds.get(j - 1));
                    noeuds.set(j - 1, temp);
                }
            }
        }
        return g;
    }

    //VOISIN D'UN NOEUD
    public List<Noeud<T>> voisinNoeud(Noeud noeud, Graphe g) {
        List<Noeud<T>> res = new ArrayList();
        List<Arc<T>> arcs = g.getArcs();
        for (Arc<T> arc : arcs) {
            if (!(noeud.getX().equals(arc.getDebut().getX()) && noeud.getX().equals(arc.getFin().getX()))) {
                if (noeud.getX().equals(arc.getDebut().getX())) {
                    res.add(arc.getFin());
                }
                if (noeud.getX().equals(arc.getFin().getX())) {
                    res.add(arc.getDebut());
                }
            }
        }
        return res;
    }

    //REMOVE NOEUD COLOR
    public void removeNoeudColore(List<Noeud<T>> noeuds) {
        List<Noeud<T>> noeudDelete = new ArrayList();
        for (Noeud noeud : noeuds) {
            if (!noeud.getColor().equals("0;0;0")) {
                noeudDelete.add(noeud);
            }
        }
        noeuds.removeAll(noeudDelete);
    }

}
