package M1.Listener.flotMax;

import M1.Frame.AccueilFrame;
import M1.Frame.AddFlotMaxFrame;
import M1.Models.Noeud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class CreateFlotMaxListener implements ActionListener {

    private AccueilFrame accueilFrame;

    public CreateFlotMaxListener(AccueilFrame accueilFrame) {
        this.accueilFrame = accueilFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddFlotMaxFrame flotFrame = new AddFlotMaxFrame();
        List<Noeud<Integer>> noeuds = accueilFrame.getGraphePanel().getNoeuds();
        for(Noeud noeud : noeuds){
            flotFrame.getSourceCombo().addItem(""+noeud.getX());
            flotFrame.getPuitCombo().addItem(""+noeud.getX());
        }
        flotFrame.getValidationButton().addActionListener(new AddFlotMaxListener(accueilFrame, flotFrame));
    }

}
