package M1.Service.Ordonnancement;

import M1.Models.Arc;
import M1.Models.Graphe;
import M1.Models.Noeud;
import M1.Models.Ordonnancement;
import M1.Service.ArcService;
import M1.Service.GrapheService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class OrdonnancementService<T> {

    private final GrapheService<T> grapheService = new GrapheService();
    private final ArcService<T> arcService = new ArcService();

    public void ordonnancementTache(Graphe g) throws Exception {
        List<Arc<T>> arcs = g.getArcs();
        this.addNoeudFin(g);
        List<Noeud<T>> niveau = grapheService.decompEnNiveau(g);
        int niveauMax = niveau.get(niveau.size() - 1).getNiveau() + 1;
        this.initializePosition(niveauMax, g);
        for (int i = 1; i < niveauMax; i++) {
            List<Noeud<T>> noeudNiveau = grapheService.getNiveau(i, niveau);
            for (Noeud<T> noeud : noeudNiveau) {
                Arc<T> arcMaximum = arcService.arcExtremum(grapheService.predecesseur(arcs, noeud), "maximum");
                arcMaximum.setColor("214;15;15");
                Noeud<T> n = arcMaximum.getFin();
                n.setPoids((int) (arcMaximum.getDebut().getPoids() + arcMaximum.getPoids()));
                n.setDureePlusTot((int) n.getPoids());
                n.setDureePlusTard((int) n.getPoids());
            }
        }
        for (int i = 0; i < niveauMax - 1; i++) {
            List<Noeud<T>> noeudNiveau = grapheService.getNiveau(i, niveau);
            /*System.out.println("Noeud Correspondant au niveau");
            for (Noeud n : noeudNiveau) {
                System.out.println(n.getX());
                grapheService.successeurs(arcs, n);
            }*/
            for (Noeud<T> noeud : noeudNiveau) {
                Arc<T> arcMinimum = arcService.arcExtremum(grapheService.successeurs(arcs, noeud), "minimum");
                if (arcMinimum != null) {
                    Noeud<T> n = arcMinimum.getDebut();
                    n.setDureePlusTard((int) (arcMinimum.getFin().getPoids() - arcMinimum.getPoids()));
                }
            }
        }
    }

    public Graphe<T> makeGraphe(List<Ordonnancement> ordonnancements) throws Exception {
        List<Arc<T>> arcs = new ArrayList();
        List<Noeud<T>> noeuds = new ArrayList();

        for (Ordonnancement ord : ordonnancements) {
            noeuds.add(new Noeud(ord.getTache(), 0, ord.getDuree(), "ordonnancement"));
        }

        this.verifyTask(ordonnancements, noeuds);

        for (Noeud noeud : noeuds) {
            List<Arc<T>> listeArc = this.makeArc(noeud, noeuds, ordonnancements);
            if (!listeArc.isEmpty()) {
                arcs.addAll(listeArc);
            }
        }

        return new Graphe(noeuds, arcs);
    }

    private List<Arc<T>> makeArc(Noeud<T> noeud, List<Noeud<T>> noeuds, List<Ordonnancement> ord) {
        List<Arc<T>> arcs = new ArrayList();

        for (Ordonnancement o : ord) {
            if (noeud.getX().equals(o.getTache()) && !o.getTacheAnterieur().isEmpty()) {
                String[] tacheAnterieurs = o.getTacheAnterieurs();
                for (String t : tacheAnterieurs) {
                    for (Noeud n : noeuds) {
                        if (n.getX().equals(t)) {
                            arcs.add(new Arc(n, noeud, n.getDuree()));
                        }
                    }
                }
                break;
            }
        }

        return arcs;
    }

    private boolean verifyTask(List<Ordonnancement> ordonnancements, List<Noeud<T>> noeuds) throws Exception {
        boolean result = true;
        for (Ordonnancement ord : ordonnancements) {
            if (!ord.getTacheAnterieur().isEmpty()) {
                String[] task = ord.getTacheAnterieurs();
                for (String t : task) {
                    if (!ifTaskExist(t, noeuds)) {
                        throw new Exception("Inexistance d'une tâche antérieure");
                    }

                }
            }
        }
        return result;
    }

    private boolean ifTaskExist(String task, List<Noeud<T>> noeuds) {
        boolean result = false;
        for (Noeud n : noeuds) {
            if (n.getX().equals(task)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void addNoeudFin(Graphe g) {
        List<Noeud<T>> noeuds = g.getNoeuds();
        List<Arc<T>> arcs = g.getArcs();

        List<Noeud<T>> noeudDecompose = grapheService.decompEnNiveau(g);
        int lastIndice = noeudDecompose.size() - 1;
        int niveauMax = noeudDecompose.get(lastIndice).getNiveau();

        Noeud<T> noeudFin = new Noeud("FIN", 0, 0, "");
        noeudFin.setNiveau(niveauMax + 1);
        noeudFin.setPosX(50 + (noeudFin.getNiveau() * 200));
        noeudFin.setPosY(150);
        noeuds.add(noeudFin);

        for (Noeud noeud : noeuds) {
            if (noeud.getNiveau() == niveauMax) {
                arcs.add(new Arc(noeud, noeudFin, noeud.getDuree()));
            }
        }
    }

    public void initializePosition(int niveauMax, Graphe g) {
        List<Noeud<T>> noeuds = g.getNoeuds();
        for (int i = 0; i < niveauMax + 1; i++) {
            int k = 50;
            for (Noeud n : noeuds) {
                if (n.getNiveau() == i) {
                    n.setPosX(50 + (i * 200));
                    n.setPosY(k);
                    k += 100;
                }
            }
        }
    }

}