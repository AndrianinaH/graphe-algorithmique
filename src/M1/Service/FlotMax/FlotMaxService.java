package M1.Service.FlotMax;

import M1.Models.Arbre;
import M1.Models.Arc;
import M1.Models.Graphe;
import M1.Models.Noeud;
import M1.Service.GrapheService;
import M1.Service.UtilService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class FlotMaxService<T> {

    private final GrapheService<T> grapheService = new GrapheService();

    public void searchFlotMax(Graphe g, Noeud<T> source, Noeud<T> puit) throws Exception {
        Arbre<T> arbre;
        Noeud<T> n = new Noeud();
        List<Noeud<T>> file = new ArrayList();
        file.add(source);
        while (true) {
            arbre = new Arbre(source.getX());
            file.clear();
            file.add(source);
            n.initialize(g.getNoeuds());

            while (!file.isEmpty()) {
                List<Arc<T>> successeurs = grapheService.successeurs(g.getArcs(), file.get(0));
                if (isSuccesseurValid(successeurs)) {
                    for (Arc successeur : successeurs) {
                        if (successeur.getFin().getCouleur().equals("BLANC") && !successeur.getCouleur().equals("ROUGE")) {
                            successeur.getFin().setCouleur("NOIR");
                            successeur.setDelta(successeur.getPoids() - successeur.getFlot());
                            file.add(successeur.getFin());
                            arbre.add((T) successeur.getFin().getX(), file.get(0).getX());

                            if (successeur.getFin().getX().equals(puit.getX())) {
                                break;
                            }
                        }
                    }
                } else {
                    List<Arc<T>> predecesseurs = grapheService.predecesseur(g.getArcs(), file.get(0));
                    for (Arc predecesseur : predecesseurs) {
                        Noeud<T> debutArc = predecesseur.getDebut();
                        Noeud<T> finArc = predecesseur.getFin();
                        if (debutArc.getCouleur().equals("BLANC") && 0 < predecesseur.getFlot() && !debutArc.getX().equals(source.getX()) && !finArc.getX().equals(puit.getX())) {
                            predecesseur.getFin().setCouleur("NOIR");
                            predecesseur.setDelta(-((predecesseur.getPoids() - predecesseur.getFlot()) + predecesseur.getPoids()));

                            Arc<T> arc = new Arc(predecesseur.getFin(), predecesseur.getDebut());
                            arc.setPoids(predecesseur.getPoids());
                            arc.setDelta(predecesseur.getDelta());

                            g.getArcs().add(arc);

                            file.add(arc.getFin());
                            arbre.add((T) arc.getFin().getX(), file.get(0).getX());
                        }
                    }
                }
                file.remove(file.get(0));
            }

            if (!arbre.containsKey(puit.getX())) {
                break;
            }

            int flotMinimal = this.flotMinimum(arbre, g, puit.getX());
            this.updateFlot(arbre, g, puit.getX(), flotMinimal);

        }
    }

    public int flotMinimum(Arbre<T> arbre, Graphe<T> g, T puit) {
        T visite = puit;
        List<Integer> poids = new ArrayList();
        while (true) {
            if (arbre.get(visite) == null) {
                break;
            }
            Arc<T> arc = this.getArc(g.getArcs(), arbre.get(visite), visite);
            poids.add(Math.abs(arc.getDelta()));
            visite = arbre.get(visite);
        }
        return UtilService.extremumNumber(poids, "minimum");
    }

    public void updateFlot(Arbre<T> arbre, Graphe<T> g, T puit, int flotMinimal) {
        T visite = puit;
        while (true) {
            if (arbre.get(visite) == null) {
                break;
            }
            Arc<T> arc = this.getArc(g.getArcs(), arbre.get(visite), visite);
            if (arc.getDelta() < 0) {
                int flot = arc.getFlot() - flotMinimal;
                arc = this.getArc(g.getArcs(), arbre.get(visite), visite);
                arc.setFlot(flot);
            } else {
                arc.setFlot(flotMinimal + arc.getFlot());
            }

            if (arc.getFlot() == arc.getPoids()) {
                arc.setCouleur("ROUGE");
            }
            visite = arbre.get(visite);
        }

    }

    public boolean isSuccesseurValid(List<Arc<T>> arcs) {
        boolean result = false;
        for (Arc arc : arcs) {
            if (arc.getFin().getCouleur().equals("BLANC") && !arc.getCouleur().equals("ROUGE")) {
                result = true;
                break;
            }

        }
        return result;
    }

    private Arc<T> getArc(List<Arc<T>> arcs, T debut, T fin) {
        Arc<T> result = null;
        for (Arc arc : arcs) {
            if (debut.equals(arc.getDebut().getX()) && fin.equals(arc.getFin().getX())) {
                result = arc;
                break;
            }
        }
        return result;
    }
}
