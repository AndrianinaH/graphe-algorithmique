package M1.Listener.arc;

import M1.Frame.AccueilFrame;
import M1.Frame.AddArcFrame;
import M1.Models.Arc;
import M1.Models.Noeud;
import M1.Service.NoeudService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class AddArcListener implements ActionListener {

    private AccueilFrame accueilFrame;
    private AddArcFrame addArcFrame;

    public AddArcListener(AccueilFrame accueilFrame, AddArcFrame frame) {
        this.accueilFrame = accueilFrame;
        this.addArcFrame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Arc<Integer>> arcs = accueilFrame.getGraphePanel().getArcs();
        List<Noeud<Integer>> noeuds = accueilFrame.getGraphePanel().getNoeuds();
        NoeudService<Integer> noeudService = new NoeudService();

        int poids = Integer.parseInt(addArcFrame.getPoidsArc().getText().trim());
        String noeudDebutSelected = addArcFrame.getNoeudDebutCombo().getSelectedItem().toString();
        String noeudFinSelected = addArcFrame.getNoeudFinCombo().getSelectedItem().toString();

        Noeud<Integer> debut = noeudService.getNoeud(noeudDebutSelected, noeuds);
        debut.setDegre(1);
        Noeud<Integer> fin = noeudService.getNoeud(noeudFinSelected, noeuds);
        fin.setDegre(1);

        arcs.add(new Arc(debut, fin, poids));

        accueilFrame.getGraphePanel().repaint();
        accueilFrame.getGraphePanel().revalidate();

        addArcFrame.setVisible(false);

    }

}
