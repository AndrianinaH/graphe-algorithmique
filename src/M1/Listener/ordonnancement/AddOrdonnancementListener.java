package M1.Listener.ordonnancement;

import M1.Frame.AccueilFrame;
import M1.Frame.AddOrdonnancementFrame;
import M1.Models.Graphe;
import M1.Models.Ordonnancement;
import M1.Service.Ordonnancement.OrdonnancementService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class AddOrdonnancementListener implements ActionListener {

    private AccueilFrame accueilFrame;
    private AddOrdonnancementFrame addFrame;

    public AddOrdonnancementListener(AccueilFrame accueilFrame, AddOrdonnancementFrame addFrame) {
        this.accueilFrame = accueilFrame;
        this.addFrame = addFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //Remove
            this.accueilFrame.getContentPane().removeAll();
            this.accueilFrame.getOrdonnancementPanel().getNoeuds().clear();
            this.accueilFrame.getOrdonnancementPanel().getArcs().clear();

            //Update Panel
            this.accueilFrame.setContentPane(this.accueilFrame.getOrdonnancementPanel());
            this.accueilFrame.getContentPane().repaint();
            this.accueilFrame.getContentPane().revalidate();

            OrdonnancementService<String> service = new OrdonnancementService();
            DefaultTableModel model = (DefaultTableModel) this.addFrame.getjTable1().getModel();
            List<Ordonnancement> ordonnancements = new ArrayList();
            for (int i = 0; i < model.getRowCount(); i++) {
                ordonnancements.add(new Ordonnancement(model.getValueAt(i, 0).toString(), model.getValueAt(i, 1).toString(), model.getValueAt(i, 2).toString(), model.getValueAt(i, 3).toString()));
            }
            Graphe<String> g = service.makeGraphe(ordonnancements);
            service.ordonnancementTache(g);

            this.accueilFrame.getOrdonnancementPanel().setArcs(g.getArcs());
            this.accueilFrame.getOrdonnancementPanel().setNoeuds(g.getNoeuds());
            this.accueilFrame.getOrdonnancementPanel().repaint();
            this.accueilFrame.getOrdonnancementPanel().revalidate();

            this.addFrame.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            addFrame.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

}