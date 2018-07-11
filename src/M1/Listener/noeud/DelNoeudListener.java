package M1.Listener.noeud;

import M1.Frame.AccueilFrame;
import M1.Frame.SuppNoeudFrame;
import M1.Models.Graphe;
import M1.Service.GrapheService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class DelNoeudListener implements ActionListener {

    private AccueilFrame accueilFrame;
    private SuppNoeudFrame noeudFrame;

    public DelNoeudListener(AccueilFrame accueilFrame, SuppNoeudFrame noeudFrame) {
        this.accueilFrame = accueilFrame;
        this.noeudFrame = noeudFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String noeudSelected = noeudFrame.getNoeudsCombo().getSelectedItem().toString();
            GrapheService graphe = new GrapheService();
            graphe.removeNoeud(noeudSelected, new Graphe(accueilFrame.getGraphePanel().getNoeuds(), accueilFrame.getGraphePanel().getArcs()));

            accueilFrame.getGraphePanel().repaint();
            accueilFrame.getGraphePanel().revalidate();
            noeudFrame.setVisible(false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
