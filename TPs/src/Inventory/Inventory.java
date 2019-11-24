package Inventory;

import java.io.*;
import java.util.ArrayList;

public class Inventory {

    private Tree nameTree;
    private Tree codeTree;
    private Tree typeTree;

    public Inventory(Tree nameTree, Tree codeTree, Tree typeTree){
        this.codeTree = codeTree;
        this.nameTree = nameTree;
        this.typeTree = typeTree;
    }

    public void createInventory(String fileName){

        File file = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while((line = br.readLine()) != null){
                String name = "";
                String code = "";
                Character type = null;
                int critere = 0;
                for (int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) != ' ' && critere == 0){
                        name += line.charAt(i);
                    } else if(line.charAt(i) != ' ' && critere == 1){
                        code += line.charAt(i);
                    } else if(critere == 2){
                        type = line.charAt(i);
                    } else{
                        critere ++;
                    }
                }
                Objet newObjet = new Objet(name, code, type);
                this.nameTree.insertInAutomate(name, newObjet);
                this.codeTree.insertInAutomate(code, newObjet);
                this.typeTree.insertInTypeAutomate(type, newObjet);

            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Something went wrong.");
        }
    }

    public ArrayList<Objet> filter(String name, String identificator, Character type){

        ArrayList<Objet> nameObjets = this.nameTree.findInAutomate(name);
        ArrayList<Objet> identificatorObjets = this.codeTree.findInAutomate(identificator);
        ArrayList<Objet> typeObjets = this.typeTree.findInTypeAutomate(type);

        nameObjets.retainAll(identificatorObjets);
        nameObjets.retainAll(typeObjets);
        return nameObjets;

    }
}
