package M1.Service;

import M1.Models.Arbres;
import M1.Models.Arc;
import M1.Models.Graphe;
import M1.Models.Noeud;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class GrapheService<T> {

    //decomposition en niveau
    public List<Noeud<T>> decompEnNiveau(Graphe graphe) {
        List<Noeud<T>> noeudNiveau = new ArrayList();
        List<Noeud<T>> sommets = new ArrayList(graphe.getNoeuds());
        List<Arc<T>> arcs = new ArrayList(graphe.getArcs());
        ArcService<T> arcService = new ArcService();
        while (!sommets.isEmpty()) {
            List<Noeud<T>> degreEntrantNonNull = this.degreEntrantNonNull(sommets, arcs);
            for (Noeud<T> noeud : degreEntrantNonNull) {
                List<Noeud<T>> successeurs = this.successeur(graphe, noeud);
                for (Noeud<T> successeur : successeurs) {
                    if (successeur.getNiveau() < noeud.getNiveau() + 1) {
                        successeur.setNiveau(noeud.getNiveau() + 1);
                    }
                    arcService.removeArc(noeud, successeur, arcs);
                }
                sommets.remove(noeud);
                noeudNiveau.add(noeud);
            }
        }
        return noeudNiveau;
    }

    //transfert
    public List<Integer> transfert(Graphe g) {
        List<Integer> res = new ArrayList();
        List<Arc<T>> arcs = g.getArcs();
        List<Noeud<T>> niveau = this.decompEnNiveau(g);
        for (int i = 1; i < niveau.get(niveau.size() - 1).getNiveau() + 1; i++) {
            res.clear();
            List<Noeud<T>> noeudNiveau = this.getNiveau(i, niveau);
            for (Noeud<T> noeud : noeudNiveau) {
                List<Arc<T>> predecesseur = this.predecesseur(arcs, noeud);
                for (Arc<T> pred : predecesseur) {
                    pred.getFin().setPoids((int) (pred.getFin().getPoids() + (pred.getDebut().getPoids() * pred.getPoids())));
                }
                //System.out.println("noeud : "+ predecesseur.get(0).getFin().getX() +" poids sortie : "+predecesseur.get(0).getFin().getPoids());
                predecesseur.get(0).getFin().setPoids(this.fonctionTransfert((int) predecesseur.get(0).getFin().getPoids()));
                res.add((int) predecesseur.get(0).getFin().getPoids());
            }
        }
        return res;
    }

    //transfert avec matrice
    public int[] transfert(int[] nbreNoeud, int[][][] poids, int[] entree) {
        int[] res = entree;
        for (int i = 0; i < poids.length; i++) {
            int[] resultat = this.multiplicationMatrice(poids[i], res);
            res = new int[resultat.length];
            res = resultat;
        }
        return res;
    }

    //simple parcours en largeur
    public List<Noeud<T>> simpleParcLarg(Graphe g, Noeud<T> sommet) {
        List<Noeud<T>> resultat = new ArrayList<>();
        sommet.setCouleur("NOIR");
        resultat.add(sommet);
        for (int i = 0; i < resultat.size(); i++) {
            Noeud<T> s = resultat.get(i);
            List<Noeud<T>> successeurs = this.successeur(g, s);
            for (Noeud<T> successeur : successeurs) {
                if (successeur.getCouleur().equals("BLANC")) {
                    successeur.setCouleur("NOIR");
                    resultat.add(successeur);
                }
            }
        }
        return resultat;
    }

    //parcours en largeur avec Arbre comme type de retour
    public Arbres<T> parcoursLargeur(Graphe g, Noeud<T> sommet) throws Exception {
        Arbres<T> arbre = new Arbres(sommet.getX());
        List<Noeud<T>> listeSommet = new ArrayList<Noeud<T>>();
        sommet.setCouleur("GRIS");
        listeSommet.add(sommet);

        while (listeSommet.size() > 0) {
            Noeud<T> teteListe = listeSommet.get(0);
            List<Noeud<T>> successeur = this.successeur(g, teteListe);
            for (Noeud<T> noeud : successeur) {
                if (noeud.getCouleur().equals("BLANC")) {
                    noeud.setCouleur("GRIS");
                    arbre.add(noeud.getX(), teteListe.getX());
                    listeSommet.add(noeud);
                }
            }
            teteListe.setCouleur("NOIR");
            listeSommet.remove(0);
        }

        return arbre;
    }

    //parcours en profondeur
    public Arbres<T> parcoursProfondeur(Graphe g) throws Exception {
        Arbres<T> arbre = new Arbres();
        List<Noeud<T>> listeNoeud = g.getNoeuds();

        for (Noeud<T> noeud : listeNoeud) {
            arbre.add(noeud.getX(), null);
        }

        for (int i = 0; i < listeNoeud.size(); i++) {
            if (listeNoeud.get(i).getCouleur().equals("BLANC")) {
                this.visiterPP(g, listeNoeud.get(i), arbre);
            }
        }
        return arbre;
    }

    public void removeArc(String arcToDelete, Graphe g) {
        String[] arcSplit = arcToDelete.split("-");
        List<Arc<T>> arcs = g.getArcs();
        for (Arc<T> arc : arcs) {
            String nomNoeudDebut = arc.getDebut().getX().toString();
            String nomNoeudFin = arc.getFin().getX().toString();
            if (nomNoeudDebut.equals(arcSplit[0].trim()) && nomNoeudFin.equals(arcSplit[1].trim())) {
                arcs.remove(arc);
                break;
            }
        }
    }

    public void removeNoeud(String noeudToDelete, Graphe g) {
        List<Arc<T>> arcs = g.getArcs();
        List<Noeud<T>> noeuds = g.getNoeuds();
        for (Noeud<T> noeud : noeuds) {
            if (noeud.getX().toString().equals(noeudToDelete.trim())) {
                noeuds.remove(noeud);
                break;
            }
        }
        for (int i = 0; i < arcs.size(); i++) {
            String nomNoeudDebut = arcs.get(i).getDebut().getX().toString();
            String nomNoeudFin = arcs.get(i).getFin().getX().toString();
            if (nomNoeudDebut.equals(noeudToDelete.trim()) || nomNoeudFin.equals(noeudToDelete.trim())) {
                arcs.remove(arcs.get(i));
                i--;
            }
        }
    }

    public void visiterPP(Graphe g, Noeud<T> sommet, Arbres<T> arbre) throws Exception {
        sommet.setCouleur("GRIS");
        List<Noeud<T>> successeur = this.successeur(g, sommet);
        for (Noeud<T> fils : successeur) {
            if (fils.getCouleur().equals("BLANC")) {
                arbre.add(fils.getX(), sommet.getX());
                this.visiterPP(g, fils, arbre);
            }
        }
        sommet.setCouleur("NOIR");
    }

    public int fonctionTransfert(int x) {
        int res = 0;
        if (x >= 0) {
            res = 1;
        }
        return res;
    }

    public List<Noeud<T>> degreEntrantNonNull(List<Noeud<T>> noeuds, List<Arc<T>> arcs) {
        List<Noeud<T>> res = new ArrayList<>();
        List<T> listeNoeudFin = this.noeudFin(arcs);
        for (Noeud<T> noeud : noeuds) {
            if (!listeNoeudFin.contains(noeud.getX())) {
                res.add(noeud);
            }
        }
        return res;
    }

    public List<Noeud<T>> successeur(Graphe g, Noeud<T> sommet) {
        List<Noeud<T>> res = new ArrayList<>();
        List<Arc<T>> listeArcs = g.getArcs();
        for (int i = 0; i < listeArcs.size(); i++) {
            if (listeArcs.get(i).getDebut().getX().equals(sommet.getX())) {
                res.add(listeArcs.get(i).getFin());
            }
        }
        return res;
    }

    public List<Arc<T>> predecesseur(List<Arc<T>> arcs, Noeud<T> sommet) {
        List<Arc<T>> res = new ArrayList();
        for (Arc arc : arcs) {
            if (arc.getFin().getX().equals(sommet.getX())) {
                res.add(arc);
            }
        }
        return res;
    }

    public List<Arc<T>> successeurs(List<Arc<T>> arcs, Noeud<T> sommet) {
        List<Arc<T>> res = new ArrayList();
        for (Arc arc : arcs) {
            if (arc.getDebut().getX().equals(sommet.getX())) {
                res.add(arc);
            }
        }
        return res;
    }

    public int distanceArc(Graphe g, Noeud<T> debut, Noeud<T> fin) {
        List<Arc<T>> arcs = g.getArcs();
        int res = 0;
        for (Arc<T> arc : arcs) {
            if (arc.getDebut().getX() == debut.getX() && arc.getFin().getX() == fin.getX()) {
                res = arc.getPoids();
                break;
            }
        }
        return res;
    }

    public List<T> noeudFin(List<Arc<T>> arcs) {
        List<T> res = new ArrayList();
        for (Arc<T> arc : arcs) {
            res.add(arc.getFin().getX());
        }
        return res;
    }

    public List<Noeud<T>> getNiveau(int niveau, List<Noeud<T>> listeNoeud) {
        List<Noeud<T>> res = new ArrayList();
        for (Noeud noeud : listeNoeud) {
            if (noeud.getNiveau() == niveau) {
                res.add(noeud);
            }
        }
        return res;
    }

    public int[] multiplicationMatrice(int[][] matrice, int[] multiplier) {
        int[] res = new int[matrice.length];
        for (int i = 0; i < matrice.length; i++) {
            int somme = 0;
            for (int j = 0; j < matrice[0].length; j++) {
                somme += matrice[i][j] * multiplier[j];
            }
            res[i] = this.fonctionTransfert(somme);
        }
        return res;
    }

}
