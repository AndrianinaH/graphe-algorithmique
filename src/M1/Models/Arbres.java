package M1.Models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class Arbres<T> extends HashMap<T, ArrayList<T>> {

    private T racine;
    private ArrayList<T> value = new ArrayList<T>();

    public Arbres(T racine) {
        this.racine = racine;
        this.put(this.racine, null);
    }

    public Arbres() {}

    public boolean ifExist(T noeud) {
        if (this.containsKey(noeud) || this.containsValue(noeud) || noeud == null) {
            return true;
        }
        return false;
    }

    public void add(T fils, T pere) throws Exception {
        if (ifExist(pere)) {
            if (this.get(fils) != null) {
                this.get(fils).add(pere);
                this.put(fils, this.get(fils));
            } else {
                ArrayList<T> father = new ArrayList<T>();
                father.add(pere);
                this.put(fils, father);
            }
        } else {
            throw new Exception("père inexistant");
        }
    }

    public void afficher() {
        System.out.println(this.keySet());
        System.out.println(this.values());
    }
}
