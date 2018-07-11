package M1.Main;

import M1.Models.Arc;
import M1.Models.Graphe;
import M1.Models.Noeud;
import M1.Service.Coloriage.ColoriageService;
import M1.Service.FlotMax.FlotMaxService;
import M1.Service.Ordonnancement.OrdonnancementService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        //ORDONNANCEMENT
        List<Noeud<String>> noeuds = new ArrayList();
        noeuds.add(new Noeud("A", 0, 2, ""));//0
        noeuds.add(new Noeud("B", 0, 4, ""));//1
        noeuds.add(new Noeud("C", 0, 4, ""));//2
        noeuds.add(new Noeud("D", 0, 5, ""));//3
        noeuds.add(new Noeud("E", 0, 6, ""));//4

        List<Arc<String>> arcs = new ArrayList();
        arcs.add(new Arc(noeuds.get(0), noeuds.get(2), 2));
        arcs.add(new Arc(noeuds.get(0), noeuds.get(3), 2));
        arcs.add(new Arc(noeuds.get(1), noeuds.get(3), 4));
        arcs.add(new Arc(noeuds.get(3), noeuds.get(4), 5));
        arcs.add(new Arc(noeuds.get(2), noeuds.get(4), 4));

        Graphe<String> g = new Graphe(noeuds, arcs);

        OrdonnancementService service = new OrdonnancementService();
        service.ordonnancementTache(g);

        for (Noeud n : g.getNoeuds()) {
            System.out.println("Noeud " + n.getX() + " - Niveau " + n.getNiveau() + " - Durée Plus Tôt " + n.getDureePlusTot() + " - Durée Plus Tard " + n.getDureePlusTard());
        }

        //COLORATION
        ColoriageService coloriageService = new ColoriageService();
        coloriageService.coloriage(g);
        for (Noeud n : g.getNoeuds()) {
            System.out.println("Noeud : " + n.getX() + " - Couleur : " + n.getColor());
        }

        //FLOT MAX
        noeuds = new ArrayList();
        noeuds.add(new Noeud("A"));//0
        noeuds.add(new Noeud("B"));//1
        noeuds.add(new Noeud("C"));//2
        noeuds.add(new Noeud("D"));//3

        arcs = new ArrayList();
        arcs.add(new Arc(noeuds.get(0), noeuds.get(1), 3));
        arcs.add(new Arc(noeuds.get(0), noeuds.get(2), 3));
        arcs.add(new Arc(noeuds.get(1), noeuds.get(2), 2));
        arcs.add(new Arc(noeuds.get(1), noeuds.get(3), 2));
        arcs.add(new Arc(noeuds.get(2), noeuds.get(3), 3));

        g = new Graphe(noeuds, arcs);
        FlotMaxService flotService = new FlotMaxService();
        flotService.searchFlotMax(g, noeuds.get(0), noeuds.get(3));

        //LES FLOTS SORTANT DU SOURCE DOIVENT ETRE EGALE AU FLOT ENTRANT DU PUIT
        //LES FLOTS SORTANT DU SOURCE, ICI LA SOURCE EST A
        System.out.println("---SOURCE---");
        for (Arc a : g.getArcs()) {
            if (a.getDebut().getX().equals("A")) {
                System.out.println(a.getFlot() + "/" + a.getPoids());
            }
        }

        //LES FLOTS ENTRANT DU PUIT, ICI LA PUIT EST D
        System.out.println("---PUIT---");
        for (Arc a : g.getArcs()) {
            if (a.getFin().getX().equals("D")) {
                System.out.println(a.getFlot() + "/" + a.getPoids());
            }
        }

    }
}
