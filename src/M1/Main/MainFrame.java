package M1.Main;

import M1.Frame.AccueilFrame;
import M1.Frame.GraphePanel;
import com.alee.laf.WebLookAndFeel;

import javax.swing.*;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class MainFrame {
    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel ( new WebLookAndFeel() );
        UIManager.setLookAndFeel ( "com.alee.laf.WebLookAndFeel" );
        UIManager.setLookAndFeel ( WebLookAndFeel.class.getCanonicalName() );
        GraphePanel graphePanel = new GraphePanel();
        AccueilFrame accueilFrame = new AccueilFrame(graphePanel,"Graphe");
    }
}
