package M1.Frame;

import M1.Models.Arc;
import M1.Models.Noeud;
import M1.Service.ArcService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class OrdonnancementPanel extends JPanel implements MouseMotionListener, MouseListener {

    private int posX;
    private int posY;
    private int dragX;
    private int dragY;
    private List<Noeud<String>> noeuds = new ArrayList();
    private List<Arc<String>> arcs = new ArrayList();

    public OrdonnancementPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.BLACK);

        for (Noeud noeud : noeuds) {
            noeud.dessinerOrdonnancement(g2);
        }

        for (Arc arc : arcs) {
            arc.dessinerOrdonnancement(g2);
        }
        //System.out.println(noeuds.get(0).getClass().getName());

    }

    public List<Noeud<String>> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(List<Noeud<String>> noeuds) {
        this.noeuds = noeuds;
    }

    public List<Arc<String>> getArcs() {
        return arcs;
    }

    public void setArcs(List<Arc<String>> arcs) {
        this.arcs = arcs;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ArcService<String> arcService = new ArcService();
        for (Noeud point : noeuds) {
            if (point.getDragX() != 0) {
                point.setPosX(e.getX() - point.getDragX());
                point.setPosY(e.getY() - point.getDragY());
                arcService.updateCoordinate(arcs, point);
                break;
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        for (Noeud point : noeuds) {
            if ((x > point.getPosX() && (x < point.getPosX() + 100)) && (y > point.getPosY() && (y < point.getPosY() + 50))) {
                point.setDragX(x - point.getPosX());
                point.setDragY(y - point.getPosY());
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ArcService<String> arcService = new ArcService();
        for (Noeud point : noeuds) {
            if (point.getDragX() != 0) {
                point.setPosX(e.getX() - point.getDragX());
                point.setPosY(e.getY() - point.getDragY());
                arcService.updateCoordinate(arcs, point);
                point.setDragX(0);
                point.setDragY(0);
                break;
            }
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}