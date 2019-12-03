package Inventory;

import java.util.*;

public class Tree {

    private Node root;

    public Tree(Node root){
        this.root = root;
    }

/* s'applique pour automate nom et automate code identificateur. Chaque automate contient le type s'il s'agit un terminal*/
    public void insertInAutomate(String word, Objet newObjet){
        Node current = this.root;
        for (int i = 0; i < word.length(); i++){
            current = current.getChild().computeIfAbsent(word.charAt(i), c -> new Node());
        }
        current.setTerminal(true);
        current.getObjets().add(newObjet);
    }

    public void insertInTypeAutomate(Character type, Objet newObjet){
        Node current = this.root.getChild().computeIfAbsent(type, c -> new Node());
        current.setTerminal(true);
        current.getObjets().add(newObjet);
    }

    public ArrayList<Objet> findInAutomate(String word){
        ArrayList<Objet> list = new ArrayList<Objet>();
        Node current = root;
        for(int i = 0; i < word.length(); i++){
            Character c = word.charAt(i);
            current = current.getChild().get(c);
            if(current == null){
                return list;
            }
        }
        if(current.isTerminal()){
            for (Objet o: current.getObjets()) {
                list.add(o);
            }
        }
        current.findTerminals(list, current.getChild());
        return list;
    }

    public ArrayList<Objet> findInTypeAutomate(Character type){
        if(type == null){
            if (type == 'A' || type == 'B' || type =='C'){
                ArrayList<Objet> resultA = this.root.getChild().get('A').getObjets();
                ArrayList<Objet> resultB = this.root.getChild().get('B').getObjets();
                ArrayList<Objet> resultC = this.root.getChild().get('C').getObjets();
                ArrayList<Objet> result = new ArrayList<Objet>();
                result.addAll(resultA);
                result.addAll(resultB);
                result.addAll(resultC);
                return result;
            }
        }
        return this.root.getChild().get(type).getObjets();
    }

    public void deleteInAutomate(String word, Objet objet){
        delete(this.root, word, 0, objet);
    }

    private boolean delete(Node current, String word, int index, Objet objet){       //il ne faut pas utiliser cette méthode avec type dont la valeur est à 0
        if (index == word.length()) {
            if (!current.isTerminal()) {        //si mot entré n'est pas terminal, on ne delete pas
                return false;
            }
            current.setTerminal(false);
            return current.getChild().isEmpty();        //si mot entré est terminal et a un enfant, ne pas le delete mais le faire passer à non terminal
        }
        char ch = word.charAt(index);
        Node node = current.getChild().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1, objet) && !node.isTerminal();

        if (shouldDeleteCurrentNode) {
            if(current.getChild().get(ch).getObjets().size() > 1){
                current.getChild().get(ch).getObjets().remove(objet);
                current.getChild().get(ch).setTerminal(true);
                return false;       //child is not empty
            } else{
                current.getChild().remove(ch);
                return current.getChild().isEmpty();
            }
        }
        return false;
    }

    public void deleteInTypeAutomate(Character type, Objet objet){
        Node node = this.root.getChild().get(type);
        if(node.getObjets().size() > 1) {
            node.getObjets().remove(objet);
        } else{
            this.root.getChild().remove(type);
        }
    }

}
