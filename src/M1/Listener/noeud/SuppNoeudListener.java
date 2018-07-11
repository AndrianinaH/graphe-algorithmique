package M1.Listener.noeud;

import M1.Frame.AccueilFrame;
import M1.Frame.SuppNoeudFrame;
import M1.Models.Noeud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class SuppNoeudListener implements ActionListener {

    private AccueilFrame accueilFrame;

    public SuppNoeudListener(AccueilFrame accueilFrame) {
        this.accueilFrame = accueilFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SuppNoeudFrame noeudFrame = new SuppNoeudFrame();
        List<Noeud<Integer>> noeuds = accueilFrame.getGraphePanel().getNoeuds();
        for (Noeud noeud : noeuds) {
            noeudFrame.getNoeudsCombo().addItem(noeud.getX().toString());
        }
        noeudFrame.getSuppressionButton().addActionListener(new DelNoeudListener(accueilFrame, noeudFrame));
    }

}
