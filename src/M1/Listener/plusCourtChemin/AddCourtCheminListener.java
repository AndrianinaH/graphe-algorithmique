package M1.Listener.plusCourtChemin;

import M1.Frame.AccueilFrame;
import M1.Frame.PlusCourtCheminFrame;
import M1.Models.Arc;
import M1.Models.Graphe;
import M1.Models.Noeud;
import M1.Service.PlusCourtCheminService.PlusCourtCheminService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class AddCourtCheminListener implements ActionListener {

    private AccueilFrame accueilFrame;
    private PlusCourtCheminFrame courtCheminFrame;

    public AddCourtCheminListener(AccueilFrame accueilFrame, PlusCourtCheminFrame courtCheminFrame) {
        this.accueilFrame = accueilFrame;
        this.courtCheminFrame = courtCheminFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            PlusCourtCheminService service = new PlusCourtCheminService();
            int depart = Integer.parseInt(this.courtCheminFrame.getDepartCombo().getSelectedItem().toString());
            int arrive = Integer.parseInt(this.courtCheminFrame.getArriveCombo().getSelectedItem().toString());
            List<Noeud<Integer>> noeuds = accueilFrame.getGraphePanel().getNoeuds();
            List<Arc<Integer>> arcs = accueilFrame.getGraphePanel().getArcs();
            service.plusCourtChemin(new Graphe(noeuds, arcs), new Noeud<>(depart), new Noeud<>(arrive));
            accueilFrame.getGraphePanel().repaint();
            accueilFrame.getGraphePanel().revalidate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

