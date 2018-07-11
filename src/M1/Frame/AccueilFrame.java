package M1.Frame;

import M1.Frame.Menu.MenuTotal;
import M1.Listener.CreateGrapheListener;
import M1.Listener.arc.AddArcListener;
import M1.Listener.arc.CreateArcListener;
import M1.Listener.arc.SuppArcListener;
import M1.Listener.coloration.AddColorListener;
import M1.Listener.flotMax.CreateFlotMaxListener;
import M1.Listener.noeud.CreateNoeudListener;
import M1.Listener.noeud.SuppNoeudListener;
import M1.Listener.ordonnancement.CreateOrdonnancementListener;
import M1.Listener.plusCourtChemin.CreateCourtCheminListener;

import javax.swing.*;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class AccueilFrame extends JFrame {
    private MenuTotal menuTotal = new MenuTotal();
    private GraphePanel graphePanel;
    private OrdonnancementPanel ordonnancementPanel = new OrdonnancementPanel();

    public AccueilFrame(GraphePanel pannel, String title){
        super(title);
        this.setGraphePanel(pannel);
        this.getContentPane().add(getGraphePanel());
        this.setJMenuBar(menuTotal);
        this.setSize(800, 400);

        //----------------- Listener
        //------- graph
        menuTotal.nouveauGraphe.addActionListener(new CreateGrapheListener(this));
        menuTotal.addNoeud.addActionListener(new CreateNoeudListener(this));
        menuTotal.deleteNoeud.addActionListener(new SuppNoeudListener(this));
        menuTotal.addArc.addActionListener(new CreateArcListener(this));
        menuTotal.deleteArc.addActionListener(new SuppArcListener(this));

        //------- plus court chemin
        menuTotal.newPlusCourtChemin.addActionListener(new CreateCourtCheminListener(this));

        //------- coloriage
        menuTotal.newColoration.addActionListener(new AddColorListener(this));


        //------- ordonnancement
        menuTotal.newOrdonnancement.addActionListener(new CreateOrdonnancementListener(this));

        //------- flot Max
        menuTotal.newFlotMax.addActionListener(new CreateFlotMaxListener(this));


        //----------------- fin Listener

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public MenuTotal getMenuTotal() {
        return menuTotal;
    }

    public void setMenuTotal(MenuTotal menuTotal) {
        this.menuTotal = menuTotal;
    }

    public GraphePanel getGraphePanel() {
        return graphePanel;
    }

    public void setGraphePanel(GraphePanel graphePanel) {
        this.graphePanel = graphePanel;
    }

    public OrdonnancementPanel getOrdonnancementPanel() {
        return ordonnancementPanel;
    }

    public void setOrdonnancementPanel(OrdonnancementPanel ordonnancementPanel) {
        this.ordonnancementPanel = ordonnancementPanel;
    }
}
