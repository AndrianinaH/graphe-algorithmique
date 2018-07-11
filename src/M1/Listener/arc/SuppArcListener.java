package M1.Listener.arc;

import M1.Frame.AccueilFrame;
import M1.Frame.SuppArcFrame;
import M1.Models.Arc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class SuppArcListener implements ActionListener {
    private AccueilFrame accueilFrame;

    public SuppArcListener(AccueilFrame accueilFrame) {
        this.accueilFrame = accueilFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SuppArcFrame frame = new SuppArcFrame();
        List<Arc<Integer>> arcs = accueilFrame.getGraphePanel().getArcs();

        for(Arc arc : arcs) {
            frame.getArcsCombo().addItem(arc.getDebut().getX()+" - "+arc.getFin().getX());
        }

        frame.getSuppressionButton().addActionListener(new DelArcListener(accueilFrame, frame));
    }

}