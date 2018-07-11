package M1.Listener;

import M1.Frame.AccueilFrame;
import M1.Frame.AddNoeudFrame;
import M1.Listener.noeud.AddNoeudListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class CreateGrapheListener implements ActionListener {
    private AccueilFrame accueil;

    public CreateGrapheListener(AccueilFrame accueil) {
        this.accueil = accueil;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Remove
        this.accueil.getContentPane().removeAll();
        this.accueil.getGraphePanel().getNoeuds().clear();
        this.accueil.getGraphePanel().getArcs().clear();

        //Update Panel
        this.accueil.setContentPane(accueil.getGraphePanel());
        this.accueil.getContentPane().repaint();
        this.accueil.getContentPane().revalidate();

        //Show PopUp
        AddNoeudFrame frame = new AddNoeudFrame();
        frame.getAjout().addActionListener(new AddNoeudListener(frame, accueil));
    }

}

