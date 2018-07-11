package M1.Listener.noeud;

import M1.Frame.AccueilFrame;
import M1.Frame.AddNoeudFrame;
import M1.Models.Noeud;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class AddNoeudListener implements ActionListener {

    private AddNoeudFrame frame;
    private AccueilFrame accueilFrame;

    public AddNoeudListener(AddNoeudFrame frame, AccueilFrame accueilFrame) {
        this.frame = frame;
        this.accueilFrame = accueilFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int nombre = Integer.parseInt(frame.getNombreSommet().getText());
            List<Noeud<Integer>> noeuds = accueilFrame.getGraphePanel().getNoeuds();
            int nombreNoeuds = noeuds.size();
            for (int i = 0; i < nombre; i++) {
                noeuds.add(new Noeud((int) (Math.random() * 150), (int) (Math.random() * 150), nombreNoeuds + 1));
                nombreNoeuds++;
            }
            accueilFrame.getGraphePanel().repaint();
            accueilFrame.getGraphePanel().revalidate();

            frame.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            frame.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}
