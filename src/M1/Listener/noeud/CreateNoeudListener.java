package M1.Listener.noeud;

import M1.Frame.AccueilFrame;
import M1.Frame.AddNoeudFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class CreateNoeudListener implements ActionListener {
    private AccueilFrame frame;

    public CreateNoeudListener(AccueilFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddNoeudFrame ajoutFrame = new AddNoeudFrame();
        ajoutFrame.getAjout().addActionListener(new AddNoeudListener(ajoutFrame, frame));
    }

}
