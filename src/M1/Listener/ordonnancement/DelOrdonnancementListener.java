package M1.Listener.ordonnancement;

import M1.Frame.AddOrdonnancementFrame;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class DelOrdonnancementListener implements ActionListener {

    private AddOrdonnancementFrame frame;

    public DelOrdonnancementListener(AddOrdonnancementFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int numberCount = frame.getjTable1().getModel().getRowCount();
        ((DefaultTableModel)frame.getjTable1().getModel()).removeRow(numberCount);
    }

}

