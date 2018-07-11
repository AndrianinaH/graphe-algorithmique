package M1.Service;

import M1.Models.Arc;
import M1.Models.Noeud;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class ArcService<T> {
    public void removeArc(Noeud<T> debut, Noeud<T> fin, List<Arc<T>> arcs) {
        for (int i = 0; i < arcs.size(); i++) {
            if (arcs.get(i).getDebut().getX() == debut.getX() && arcs.get(i).getFin().getX() == fin.getX()) {
                arcs.remove(i);
                break;
            }
        }
    }

    public void updateCoordinate(List<Arc<T>> arcs, Noeud<T> noeud) {
        for (Arc arc : arcs) {
            Noeud noeudDebut = arc.getDebut();
            Noeud noeudFin = arc.getFin();
            if (noeudDebut.getX().equals(noeud.getX())) {
                noeudDebut.setPosX(noeud.getPosX());
                noeudDebut.setPosY(noeud.getPosY());
                break;
            } else if (noeudFin.getX().equals(noeud.getX())) {
                noeudFin.setPosX(noeud.getPosX());
                noeudFin.setPosY(noeud.getPosY());
                break;
            }
        }
    }

    public List<Noeud<T>> noeudDisponible(T noeud, List<Noeud<T>> noeuds, List<Arc<T>> arcs) {
        List<Noeud<T>> noeudDispo = new ArrayList(noeuds);
        for (Arc arc : arcs) {
            if (arc.getDebut().getX().equals(noeud)) {
                noeudDispo.remove(arc.getFin());
            }
        }
        return noeudDispo;
    }

    public Arc<T> arcExtremum(List<Arc<T>> arcs, String typeExtremum) {
        Arc<T> res = null;
        if (!arcs.isEmpty()) {
            res = arcs.get(0);
            if (typeExtremum.equals("maximum")) {
                for (Arc arc : arcs) {
                    int distanceRes = (int) (res.getDebut().getPoids() + res.getPoids());
                    int distanceArc = (int) (arc.getDebut().getPoids() + arc.getPoids());
                    if (distanceArc > distanceRes) {
                        res = arc;
                    }
                }
            } else {
                for (Arc arc : arcs) {
                    int distanceRes = (int) (res.getDebut().getPoids() - res.getPoids());
                    int distanceArc = (int) (arc.getDebut().getPoids() - arc.getPoids());
                    if (distanceArc < distanceRes) {
                        res = arc;
                    }
                }
            }
        }
        return res;
    }
}
