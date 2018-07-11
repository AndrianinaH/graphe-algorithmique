package M1.Models;

import java.util.HashMap;

/**
 * Created by Andrianina_pc on 10/07/2018.
 */
public class Arbre<T> extends HashMap<T, T> {

    private T racine;
    private T value;

    public Arbre(T racine) {
        this.racine = racine;
        this.put(this.racine, null);
    }

    public Arbre() {}

    public boolean ifExist(T noeud) {
        return this.containsKey(noeud) || this.containsValue(noeud) || noeud == null;
    }

    public void add(T fils, T pere) throws Exception {
        if (ifExist(pere)) {
            this.put(fils, pere);
        } else {
            throw new Exception("père inexistant");
        }
    }

    public void afficher() {
        System.out.println(this.keySet());
        System.out.println(this.values());
    }

}
