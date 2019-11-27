package com.company;

import Inventory.Inventory;
import Inventory.Node;
import Inventory.Tree;
import Inventory.Objet;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Tree nameTree = new Tree(new Node());
        Tree codeTree = new Tree(new Node());
        Tree typeTree = new Tree(new Node());
        Inventory inventory = new Inventory(nameTree, codeTree, typeTree);

        String fileName = "./inventaire.txt";
        inventory.createInventory(fileName);

        ArrayList<Objet> result = inventory.filter("ami", "", 'B');

        inventory.getNameTree().deleteInAutomate("ami", result.get(0));
        inventory.getTypeTree().deleteInTypeAutomate('B', result.get(0));

    }
}
