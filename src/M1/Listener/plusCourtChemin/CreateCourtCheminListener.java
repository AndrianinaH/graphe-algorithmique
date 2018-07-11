package M1.Listener.plusCourtChemin;

import M1.Frame.AccueilFrame;
import M1.Frame.PlusCourtCheminFrame;
import M1.Models.Noeud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class CreateCourtCheminListener implements ActionListener {

    AccueilFrame accueilFrame;

    public CreateCourtCheminListener(AccueilFrame accueilFrame) {
        this.accueilFrame = accueilFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlusCourtCheminFrame frame = new PlusCourtCheminFrame();
        List<Noeud<Integer>> noeuds = accueilFrame.getGraphePanel().getNoeuds();
        for (Noeud n : noeuds) {
            frame.getDepartCombo().addItem("" + n.getX());
            frame.getArriveCombo().addItem("" + n.getX());
        }
        frame.getSearchButton().addActionListener(new AddCourtCheminListener(accueilFrame, frame));
    }

}
