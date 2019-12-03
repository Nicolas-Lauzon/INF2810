package Inventory;

public class Objet {
    private String name;
    private String identificator;
    private Character type;

    public Objet(String name, String identificator, Character type){
        this.name = name;
        this.identificator = identificator;
        this.type = type;
    }
    public String getName(){
        return this.name;
    }
    public String getIdentificator(){
        return this.identificator;
    }
    public Character getType(){
        return this.type;
    }
}
