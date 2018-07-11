package M1.Listener.arc;

import M1.Frame.AccueilFrame;
import M1.Frame.AddArcFrame;
import M1.Models.Noeud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class CreateArcListener implements ActionListener {

    private AccueilFrame accueilFrame;

    public CreateArcListener(AccueilFrame accueilFrame) {
        this.accueilFrame = accueilFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddArcFrame addArcFrame = new AddArcFrame();

        //Add list noeud
        List<Noeud<Integer>> noeuds = accueilFrame.getGraphePanel().getNoeuds();
        for (Noeud noeud : noeuds) {
            addArcFrame.getNoeudDebutCombo().addItem("" + noeud.getX());
        }

        //Add listener
        addArcFrame.getNoeudDebutCombo().addItemListener(new ComboArcListener(accueilFrame.getGraphePanel(), addArcFrame));
        addArcFrame.getCreateArcButton().addActionListener(new AddArcListener(accueilFrame, addArcFrame));
    }

}
