package Inventory;

import java.util.*;

public class Node {
    private boolean terminal;
    private ArrayList<Objet> objets;
    private HashMap<Character, Node> child;

    /*Uniquement les node terminaux on un type et un identificateur. Sinon ces attributs sont set à NULL*/

    public Node() {
        this.terminal = false;
        this.objets = new ArrayList<Objet>();
        this.child = new HashMap<Character, Node>();
    }

    public HashMap<Character, Node> getChild() {
        return this.child;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public boolean isTerminal() {
        return terminal;
    }


    public ArrayList<Objet> getObjets() {
        return objets;
    }

    public void findTerminals(ArrayList<Objet> list, HashMap<Character, Node> map){      //pour trouver terminals dans enfants
        Iterator mapIterator = map.entrySet().iterator();
        while(mapIterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)mapIterator.next();
            HashMap<Character, Node> childs = ((Node)mapElement.getValue()).getChild();
            if(childs != null) {
                findTerminals(list, childs);
            }
            if(((Node)mapElement.getValue()).isTerminal()){
                for (Objet o: ((Node)mapElement.getValue()).getObjets()) {
                    list.add(o);
                }
            }
        }
    }

}
