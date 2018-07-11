package M1.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class Graphe<T> {
    private List<Noeud<T>> noeuds;
    private List<Arc<T>> arcs;

    public Graphe() {}

    public Graphe(List<Noeud<T>> noeuds, List<Arc<T>> arcs) throws Exception {
        this.setNoeuds(noeuds);
        this.arcs = arcs;
    }

    public List<Noeud<T>> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(List<Noeud<T>> noeuds) throws Exception{
        HashSet<T> set = new HashSet<T>();
        for (Noeud<T> noeud : noeuds) {
            if(set.add(noeud.getX()) == false) {
                throw new Exception("existance de doublant");
            }
        }
        this.noeuds = noeuds;
    }

    public List<Arc<T>> getArcs() {
        return arcs;
    }

    public void setArcs(ArrayList<Arc<T>> arcs) {
        this.arcs = arcs;
    }
}