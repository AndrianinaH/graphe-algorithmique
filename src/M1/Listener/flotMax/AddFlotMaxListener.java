package M1.Listener.flotMax;

import M1.Frame.AccueilFrame;
import M1.Frame.AddFlotMaxFrame;
import M1.Models.Arc;
import M1.Models.Graphe;
import M1.Models.Noeud;
import M1.Service.FlotMax.FlotMaxService;
import M1.Service.NoeudService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class AddFlotMaxListener implements ActionListener {

    private AccueilFrame accueilFrame;
    private AddFlotMaxFrame flotFrame;

    public AddFlotMaxListener(AccueilFrame accueilFrame, AddFlotMaxFrame flotFrame) {
        this.accueilFrame = accueilFrame;
        this.flotFrame = flotFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            NoeudService<Integer> noeudService = new NoeudService();
            FlotMaxService<Integer> service = new FlotMaxService();

            List<Arc<Integer>> arcs = accueilFrame.getGraphePanel().getArcs();
            List<Noeud<Integer>> noeuds = accueilFrame.getGraphePanel().getNoeuds();

            String sourceSelected = flotFrame.getSourceCombo().getSelectedItem().toString();
            String puitSelected = flotFrame.getPuitCombo().getSelectedItem().toString();

            Noeud<Integer> noeudSource = noeudService.getNoeud(sourceSelected, noeuds);
            Noeud<Integer> noeudPuit = noeudService.getNoeud(puitSelected, noeuds);
            service.searchFlotMax(new Graphe(noeuds, arcs), noeudSource, noeudPuit);
            accueilFrame.getGraphePanel().setAfficheFlot(true);
            accueilFrame.getGraphePanel().repaint();
            accueilFrame.getGraphePanel().revalidate();
            flotFrame.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
