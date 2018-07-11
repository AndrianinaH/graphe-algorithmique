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
public class GraphePanel extends JPanel implements MouseMotionListener, MouseListener {

    private int posX;
    private int posY;
    private int dragX;
    private int dragY;
    private boolean afficheFlot = false;
    private List<Noeud<Integer>> noeuds = new ArrayList();
    private List<Arc<Integer>> arcs = new ArrayList();

    public GraphePanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
        for (Arc arc : arcs) {
            arc.dessiner(g2, afficheFlot);
            /*if (noeudDebut.getX().equals(noeudFin.getX())) {
                g2.drawOval(noeudDebut.getPosX() + 30, noeudDebut.getPosY() + 10, 40, 40);
            } else {
                if (i % 2 == 0) {
                    g2.drawLine(arc.getxDebut(), arc.getyDebut(), arc.getxFin(), arc.getyFin());
                } else {
                    g2.drawLine(arc.getxDebut() + 10, arc.getyDebut() + 10, arc.getxFin() + 10, arc.getyFin() + 10);
                }
                i++;
            }*/
        }
        for (Noeud noeud : noeuds) {
            noeud.dessiner(g2);
        }
//        for (Noeud noeud : noeuds) {
//            if (noeud.getDragX() != 0) {
//                g2.setColor(Color.RED);
//                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f));
//                g2.fillOval(noeud.getPosX() - 5, noeud.getPosY() - 5, 60, 60);
//            }
//        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ArcService<Integer> arcService = new ArcService();
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
            if ((x > point.getPosX() && (x < point.getPosX() + 50)) && (y > point.getPosY() && (y < point.getPosY() + 50))) {
                point.setDragX(x - point.getPosX());
                point.setDragY(y - point.getPosY());
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ArcService<Integer> arcService = new ArcService();
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

    public List<Noeud<Integer>> getNoeuds() {
        return noeuds;
    }

    public void setNoeuds(List<Noeud<Integer>> noeuds) {
        this.noeuds = noeuds;
    }

    public List<Arc<Integer>> getArcs() {
        return arcs;
    }

    public void setArcs(List<Arc<Integer>> arcs) {
        this.arcs = arcs;
    }

    public boolean isAfficheFlot() {
        return afficheFlot;
    }

    public void setAfficheFlot(boolean afficheFlot) {
        this.afficheFlot = afficheFlot;
    }


}
