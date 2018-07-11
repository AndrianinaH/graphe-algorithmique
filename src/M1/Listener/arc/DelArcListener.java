package M1.Listener.arc;

import M1.Frame.AccueilFrame;
import M1.Frame.SuppArcFrame;
import M1.Models.Graphe;
import M1.Service.GrapheService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class DelArcListener implements ActionListener {

    private AccueilFrame accueilFrame;
    private SuppArcFrame frame;

    public DelArcListener(AccueilFrame accueilFrame, SuppArcFrame frame) {
        this.accueilFrame = accueilFrame;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            GrapheService<Integer> grapheService = new GrapheService();
            String arcSelected = frame.getArcsCombo().getSelectedItem().toString();
            grapheService.removeArc(arcSelected, new Graphe(accueilFrame.getGraphePanel().getNoeuds(), accueilFrame.getGraphePanel().getArcs()));

            accueilFrame.getGraphePanel().repaint();
            accueilFrame.getGraphePanel().revalidate();
            frame.setVisible(false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}