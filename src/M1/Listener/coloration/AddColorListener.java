package M1.Listener.coloration;

import M1.Frame.AccueilFrame;
import M1.Models.Arc;
import M1.Models.Graphe;
import M1.Models.Noeud;
import M1.Service.Coloriage.ColoriageService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class AddColorListener implements ActionListener {

    private AccueilFrame accueilFrame;

    public AddColorListener(AccueilFrame accueilFrame) {
        this.accueilFrame = accueilFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ColoriageService service = new ColoriageService();
            List<Noeud<Integer>> noeuds =  accueilFrame.getGraphePanel().getNoeuds();
            List<Arc<Integer>> arcs = accueilFrame.getGraphePanel().getArcs();
            service.coloriage(new Graphe(noeuds, arcs));
            accueilFrame.getGraphePanel().repaint();
            accueilFrame.getGraphePanel().revalidate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

