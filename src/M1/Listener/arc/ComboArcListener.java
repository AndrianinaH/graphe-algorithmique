package M1.Listener.arc;

import M1.Frame.AddArcFrame;
import M1.Frame.GraphePanel;
import M1.Models.Arc;
import M1.Models.Noeud;
import M1.Service.ArcService;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class ComboArcListener implements ItemListener {

    private GraphePanel graphePanel;
    private AddArcFrame arcFrame;

    public ComboArcListener(GraphePanel graphePanel, AddArcFrame arcFrame) {
        this.graphePanel = graphePanel;
        this.arcFrame = arcFrame;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        //Remove all items
        arcFrame.getNoeudFinCombo().removeAllItems();
        arcFrame.getNoeudFinCombo().setEnabled(false);

        ArcService<Integer> service = new ArcService();
        List<Arc<Integer>> arcs = graphePanel.getArcs();
        List<Noeud<Integer>> noeuds = graphePanel.getNoeuds();
        int noeudSelected = Integer.parseInt(arcFrame.getNoeudDebutCombo().getSelectedItem().toString());
        List<Noeud<Integer>> noeudDispo = service.noeudDisponible(noeudSelected, noeuds, arcs);

        //Add Item in comboBox
        for (Noeud noeud : noeudDispo) {
            arcFrame.getNoeudFinCombo().addItem(noeud.getX().toString());
        }

        if (!noeudDispo.isEmpty()) {
            arcFrame.getNoeudFinCombo().setEnabled(true);
        }

    }

}