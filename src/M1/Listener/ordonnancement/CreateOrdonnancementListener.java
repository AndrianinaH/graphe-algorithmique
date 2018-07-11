package M1.Listener.ordonnancement;

import M1.Frame.AccueilFrame;
import M1.Frame.AddOrdonnancementFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class CreateOrdonnancementListener implements ActionListener {
    private AccueilFrame accueilFrame;

    public CreateOrdonnancementListener(AccueilFrame accueilFrame) {
        this.accueilFrame = accueilFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddOrdonnancementFrame frame = new AddOrdonnancementFrame();
        frame.getOrdonnerButton().addActionListener(new AddOrdonnancementListener(accueilFrame, frame));
    }

}