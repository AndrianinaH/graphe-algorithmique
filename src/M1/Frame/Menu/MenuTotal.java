package M1.Frame.Menu;

import javax.swing.*;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class MenuTotal extends JMenuBar {
    public JMenu creationGraphe=new JMenu("Graphe");
    public JMenuItem nouveauGraphe=new JMenuItem("Nouveau graphe");
    public JMenuItem addNoeud=new JMenuItem("Ajouter un noeud");
    public JMenuItem deleteNoeud=new JMenuItem("Supprimer un noeud");
    public JMenuItem addArc=new JMenuItem("Ajouter un arc");
    public JMenuItem deleteArc=new JMenuItem("Supprimer un arc");

    public JMenu coloration=new JMenu("Coloration");
    public JMenuItem newColoration=new JMenuItem("Colorier");

    public JMenu plusCourtChemin=new JMenu("Plus court chemin");
    public JMenuItem newPlusCourtChemin=new JMenuItem("Plus Court Chemin");

    public JMenu ordonnancement = new JMenu("Ordonnancement de tâche");
    public JMenuItem newOrdonnancement=new JMenuItem("Créer les tâches");

    public JMenu flotMax = new JMenu("Flot max");
    public JMenuItem newFlotMax=new JMenuItem("Flot Max");

    public MenuTotal()
    {
        creationGraphe.add(nouveauGraphe);
        creationGraphe.add(addNoeud);
        creationGraphe.add(deleteNoeud);
        creationGraphe.add(addArc);
        creationGraphe.add(deleteArc);
        this.add(creationGraphe);

        ordonnancement.add(newOrdonnancement);
        this.add(ordonnancement);

        plusCourtChemin.add(newPlusCourtChemin);
        this.add(plusCourtChemin);

        coloration.add(newColoration);
        this.add(coloration);

        flotMax.add(newFlotMax);
        this.add(flotMax);





    }

}
