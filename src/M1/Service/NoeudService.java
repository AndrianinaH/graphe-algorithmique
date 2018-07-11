package M1.Service;

import M1.Models.Noeud;

import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class NoeudService<T> {

    public Noeud<T> getNoeud(String x, List<Noeud<T>> noeuds) {
        Noeud<T> noeud = null;
        for (Noeud n : noeuds) {
            if (x.equals(n.getX().toString())) {
                noeud = n;
                break;
            }
        }
        return noeud;
    }

    public Noeud<T> noeudExtremum(List<Noeud<T>> listeNoeud, String typeExtremum) {
        Noeud<T> res = listeNoeud.get(0);
        if (typeExtremum.equals("minimum")) {
            for (Noeud<T> noeud : listeNoeud) {
                if (noeud.getPoids() < res.getPoids()) {
                    res = noeud;
                }
            }
        } else {
            for (Noeud<T> noeud : listeNoeud) {
                if (noeud.getPoids() > res.getPoids()) {
                    res = noeud;
                }
            }
        }
        return res;
    }
}
