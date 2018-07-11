package M1.Service.PlusCourtCheminService;

import M1.Models.Arbre;
import M1.Models.Graphe;
import M1.Models.Noeud;
import M1.Service.GrapheService;
import M1.Service.NoeudService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class PlusCourtCheminService<T> {

    private final NoeudService<T> noeudService = new NoeudService();
    private final GrapheService<T> grapheService = new GrapheService();

    public void plusCourtChemin(Graphe g, Noeud<T> depart, Noeud<T> arrive) throws Exception {
        Arbre<T> arbres = this.dijkstra(g, depart);
        if (arbres.ifExist(arrive.getX())) {
            T noeudVisite = arrive.getX();
            for (int j = 0; j < arbres.size(); j++) {
                List<Noeud<T>> noeuds = g.getNoeuds();
                noeuds.get(this.indiceNoeud(noeuds, noeudVisite)).setColor("214;15;15");

                if (noeudVisite.equals(depart.getX())) {
                    break;
                }

                noeudVisite = arbres.get(noeudVisite);
            }
        } else {
            throw new Exception("Chemin Impossible");
        }
    }

    //algorithme de Dijkstra
    public Arbre<T> dijkstra(Graphe g, Noeud<T> sommet) throws Exception {
        Arbre<T> arbre = new Arbre(sommet.getX());
        List<Noeud<T>> file_priorite = new ArrayList();
        sommet.setPoids(0);
        file_priorite.add(sommet);
        while (!file_priorite.isEmpty()) {
            Noeud<T> pivot = noeudService.noeudExtremum(file_priorite, "minimum");
            List<Noeud<T>> successeurs = grapheService.successeur(g, pivot);
            for (Noeud<T> successeur : successeurs) {
                if (successeur.getCouleur().equals("BLANC")) {
                    if (successeur.getPoids() == Float.POSITIVE_INFINITY) {
                        file_priorite.add(successeur);
                    }
                    int distanceArc = grapheService.distanceArc(g, pivot, successeur);
                    if (successeur.getPoids() > pivot.getPoids() + distanceArc) {
                        successeur.setPoids((int) (pivot.getPoids() + distanceArc));
                        arbre.add(successeur.getX(), pivot.getX());
                    }

                }
            }
            pivot.setCouleur("NOIR");
            file_priorite.remove(pivot);
        }
        return arbre;
    }

    public int indiceNoeud(List<Noeud<T>> noeuds, T noeud) {
        int indice = 0;
        for (int i = 0; i < noeuds.size(); i++) {
            if (noeuds.get(i).getX().equals(noeud)) {
                indice = i;
                break;
            }
        }
        return indice;
    }
}

