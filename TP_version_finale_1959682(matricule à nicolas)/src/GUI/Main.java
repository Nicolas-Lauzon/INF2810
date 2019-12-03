package GUI;
import java.awt.*;


import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import Inventory.Inventory;
import Inventory.Node;
import Inventory.Tree;
import Inventory.Objet;

/*
Le main se trouve au bas de ce fichier. Dans le main, vous pouvez changer le lexique utilisé.
 */


public class Main extends JFrame {
    private JPanel mainPanel;
    private JButton ajouterAuPanierButton;
    private JButton viderLePanierButton;
    private JButton retirerDuPanierButton;
    private JButton passerLaCommandeButton;
    private JTextField rechercheFieldNom;

    private JPanel panneauBoutton;
    private JPanel panneauFields;
    private JLabel etiquetteRechercherParNom;
    private JTextField rechercheFieldCode;
    private JTextField rechercheFieldType;
    private JLabel etiquetteRechercheParCode;
    private JLabel etiquetteRechercheParType;
    private JPanel counterField;
    private JButton commande;
    private JScrollPane fenetreRecherche;
    private JScrollPane fenetreInventaire;
    private JScrollPane fenetrePanier;


    private ArrayList<Objet> listeRechercheObjet;
    private ArrayList<Objet> listeInventaireObjet;
    private ArrayList<Objet> listePanierObjet;


    private JList<String> listeRechercheString;
    private JList<String> listeInventaireString;
    private JList<String> listePanierString;
    private Objet selectedItem;

    private ArrayList<String> compteurElementsRecherche ;
    private ArrayList<String> compteurElementInventaire ;
    private ArrayList<String> compteurElementPanier ;



    private JList<String> listeCompteurRecherche ;
    private JList<String> listeCompteurInventaire;
    private JList<String> listeCompteurPanier ;



    public Main(Inventory inventory) {

        listeRechercheObjet = inventory.filter("","",null);
        listeRechercheString = new JList<>((inventory.printInventory(listeRechercheObjet).toArray(new String[0])));
        fenetreRecherche = new JScrollPane(listeRechercheString);

        listeInventaireObjet = inventory.filter("","",null);
        listeInventaireString = new JList<>((inventory.printInventory(listeInventaireObjet).toArray(new String[0])));
        fenetreInventaire = new JScrollPane(listeInventaireString);

        listePanierObjet = new ArrayList<Objet>();
        listePanierString=new JList<>(inventory.printInventory(listePanierObjet).toArray(new String[0]));
        fenetrePanier = new JScrollPane(listePanierString);

        panneauFields.setLayout(new GridLayout(1,3));

        panneauFields.add(fenetreRecherche);
        panneauFields.add(fenetreInventaire);
        panneauFields.add(fenetrePanier);

        compteurElementsRecherche = new ArrayList<String>();
        compteurElementInventaire = new ArrayList<String>();
        compteurElementPanier = new ArrayList<String>();

        compteurElementsRecherche.add( listeRechercheObjet.size() + " éléments correspondants à la recherche" );
        compteurElementInventaire.add( listeInventaireObjet.size() + " éléments dans l'inventaire" );
        compteurElementPanier.add(listePanierObjet.size() + " éléments dans le panier");

        listeCompteurRecherche = new JList<>(compteurElementsRecherche.toArray(new String[0]));
        listeCompteurInventaire = new JList<>(compteurElementInventaire.toArray(new String[0]));
        listeCompteurPanier = new JList<>(compteurElementPanier.toArray(new String[0]));

        counterField.setLayout(new GridLayout(1,3));
        counterField.add(listeCompteurRecherche);
        counterField.add(listeCompteurInventaire);
        counterField.add(listeCompteurPanier);

        passerLaCommandeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!listePanierObjet.isEmpty()){
                    int poids=0;
                    for (Objet objet : listePanierObjet){
                        if (objet.getType().equals('A')){
                            poids+=1;
                        }
                        else if (objet.getType().equals('B')){
                            poids+=3;
                        }
                        else if (objet.getType().equals('C')){
                            poids+=6;
                        }
                    }
                    if (poids <= 25 ) {
                        JOptionPane.showMessageDialog(null,"La commande est passé");
                        listePanierObjet = new ArrayList<Objet>();
                        listePanierString = new JList<>(inventory.printInventory(listePanierObjet).toArray(new String[0]));
                        fenetrePanier = new JScrollPane(listePanierString);
                        panneauFields.remove(2);
                        panneauFields.add(fenetrePanier);
                        panneauFields.validate();
                        panneauFields.repaint();

                        compteurElementPanier = new ArrayList<>();
                        compteurElementPanier.add(listePanierObjet.size() + " éléments dans le panier");
                        listeCompteurPanier = new JList<>(compteurElementPanier.toArray(new String[0]));
                        counterField.remove(2);
                        counterField.add(listeCompteurPanier,2);
                        counterField.validate();
                        counterField.repaint();

                    }
                    else
                        JOptionPane.showMessageDialog(null,"La commande n'est pas passé: le poids excede 25 kg. Le poids de votre commande est: "+poids+" kg");

                }
                else
                    JOptionPane.showMessageDialog(null, "Aucun élément dans le panier pour passer la comande");

            }
        });

        rechercheFieldNom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                listeRechercheObjet = new ArrayList<Objet>();

                if (!rechercheFieldType.getText().equals("")) {
                    Character paramChar = rechercheFieldType.getText().charAt(0);
                    listeRechercheObjet = inventory.filter(rechercheFieldNom.getText(), rechercheFieldCode.getText(), paramChar);
                }
                else{
                    listeRechercheObjet = inventory.filter(rechercheFieldNom.getText(), rechercheFieldCode.getText(), null);

                }
                listeRechercheString = new JList<>(inventory.printInventory(listeRechercheObjet).toArray(new String[0]));
                fenetreRecherche = new JScrollPane(listeRechercheString);

                listeRechercheString.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        super.mouseClicked(e);
                        selectedItem = listeRechercheObjet.get(listeRechercheString.getSelectedIndex());
                    }
                });

                compteurElementsRecherche = new ArrayList<>();
                compteurElementsRecherche.add(listeRechercheObjet.size() + " éléments correspondants à la recherche");
                listeCompteurRecherche = new JList<>(compteurElementsRecherche.toArray(new String[0]));
                counterField.remove(0);
                counterField.add(listeCompteurRecherche,0);
                counterField.validate();
                counterField.repaint();

                panneauFields.remove(0);
                panneauFields.add(fenetreRecherche,0);
                panneauFields.validate();
                panneauFields.repaint();

            }
        });

        rechercheFieldCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                listeRechercheObjet = new ArrayList<Objet>();

                if (!rechercheFieldType.getText().equals("")) {
                    Character paramChar = rechercheFieldType.getText().charAt(0);
                    listeRechercheObjet = inventory.filter(rechercheFieldNom.getText(), rechercheFieldCode.getText(), paramChar);
                }
                else{
                    listeRechercheObjet = inventory.filter(rechercheFieldNom.getText(), rechercheFieldCode.getText(), null);

                }
                listeRechercheString = new JList<>(inventory.printInventory(listeRechercheObjet).toArray(new String[0]));

                fenetreRecherche = new JScrollPane(listeRechercheString);

                listeRechercheString.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        super.mouseClicked(e);
                        selectedItem = listeRechercheObjet.get(listeRechercheString.getSelectedIndex());
                    }
                });
                compteurElementsRecherche = new ArrayList<>();
                compteurElementsRecherche.add(listeRechercheObjet.size() + " éléments correspondants à la recherche");
                listeCompteurRecherche = new JList<>(compteurElementsRecherche.toArray(new String[0]));
                counterField.remove(0);
                counterField.add(listeCompteurRecherche,0);
                counterField.validate();
                counterField.repaint();

                panneauFields.remove(0);
                panneauFields.add(fenetreRecherche,0);
                panneauFields.validate();
                panneauFields.repaint();

            }
        });

        rechercheFieldType.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                listeRechercheObjet = new ArrayList<Objet>();

                if (!rechercheFieldType.getText().equals("")) {
                    Character paramChar = rechercheFieldType.getText().charAt(0);
                    listeRechercheObjet = inventory.filter(rechercheFieldNom.getText(), rechercheFieldCode.getText(), paramChar);
                }
                else{
                    listeRechercheObjet = inventory.filter(rechercheFieldNom.getText(), rechercheFieldCode.getText(), null);

                }
                listeRechercheString = new JList<>(inventory.printInventory(listeRechercheObjet).toArray(new String[0]));

                fenetreRecherche = new JScrollPane(listeRechercheString);

                listeRechercheString.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        super.mouseClicked(e);
                        selectedItem = listeRechercheObjet.get(listeRechercheString.getSelectedIndex());
                    }
                });
                compteurElementsRecherche = new ArrayList<>();
                compteurElementsRecherche.add(listeRechercheObjet.size() + " éléments correspondants à la recherche");
                listeCompteurRecherche = new JList<>(compteurElementsRecherche.toArray(new String[0]));
                counterField.remove(0);
                counterField.add(listeCompteurRecherche,0);
                counterField.validate();
                counterField.repaint();

                panneauFields.remove(0);
                panneauFields.add(fenetreRecherche,0);
                panneauFields.validate();
                panneauFields.repaint();

            }
        });

        viderLePanierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Objet objet : listePanierObjet){
                    inventory.getCodeTree().insertInAutomate(objet.getIdentificator(), objet);
                    inventory.getTypeTree().insertInTypeAutomate(objet.getType(), objet);
                }

                listeRechercheObjet = inventory.filter("","",null);
                listeRechercheString = new JList<>((inventory.printInventory(listeRechercheObjet).toArray(new String[0])));
                fenetreRecherche = new JScrollPane(listeRechercheString);

                listeInventaireObjet = inventory.filter("","",null);
                listeInventaireString = new JList<>((inventory.printInventory(listeInventaireObjet).toArray(new String[0])));
                fenetreInventaire = new JScrollPane(listeInventaireString);

                panneauFields.remove(0);
                panneauFields.add(fenetreRecherche,0);
                panneauFields.remove(1);
                panneauFields.add(fenetreInventaire,1);

                listePanierObjet = new ArrayList<Objet>();
                listePanierString = new JList<>(inventory.printInventory(listePanierObjet).toArray(new String[0]));

                listeRechercheString.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        super.mouseClicked(e);
                        selectedItem = listeRechercheObjet.get(listeRechercheString.getSelectedIndex());
                    }
                });

                listeInventaireString.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        selectedItem = listeInventaireObjet.get(listeInventaireString.getSelectedIndex());
                    }
                });

                compteurElementsRecherche = new ArrayList<String>();
                compteurElementInventaire = new ArrayList<String>();
                compteurElementPanier = new ArrayList<String>();

                compteurElementsRecherche.add( listeRechercheObjet.size() + " éléments correspondants à la recherche" );
                compteurElementInventaire.add( listeInventaireObjet.size() + " éléments dans l'inventaire" );
                compteurElementPanier.add(listePanierObjet.size() + " éléments dans le panier");

                listeCompteurRecherche = new JList<>(compteurElementsRecherche.toArray(new String[0]));
                listeCompteurInventaire = new JList<>(compteurElementInventaire.toArray(new String[0]));
                listeCompteurPanier = new JList<>(compteurElementPanier.toArray(new String[0]));

                counterField.remove(2);
                counterField.remove(1);
                counterField.remove(0);

                counterField.add(listeCompteurRecherche);
                counterField.add(listeCompteurInventaire);
                counterField.add(listeCompteurPanier);
                counterField.validate();
                counterField.repaint();


                fenetrePanier = new JScrollPane(listePanierString);
                panneauFields.remove(2);
                panneauFields.add(fenetrePanier,2);
                panneauFields.validate();
                panneauFields.repaint();
            }
        });

        retirerDuPanierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listePanierString.getSelectedIndex() != -1) {
                    Objet temp;
                    temp = listePanierObjet.get(listePanierString.getSelectedIndex());
                    listePanierObjet.remove(temp);

                    inventory.getCodeTree().insertInAutomate(temp.getIdentificator(), temp);
                    inventory.getTypeTree().insertInTypeAutomate(temp.getType(), temp);

                    listeRechercheObjet = inventory.filter("", "", null);
                    listeRechercheString = new JList<>(inventory.printInventory(listeRechercheObjet).toArray(new String[0]));
                    fenetreRecherche = new JScrollPane(listeRechercheString);

                    listeInventaireObjet = inventory.filter("", "", null);
                    listeInventaireString = new JList<>((inventory.printInventory(listeInventaireObjet).toArray(new String[0])));
                    fenetreInventaire = new JScrollPane(listeInventaireString);

                    panneauFields.remove(0);
                    panneauFields.add(fenetreRecherche, 0);
                    panneauFields.remove(1);
                    panneauFields.add(fenetreInventaire, 1);


                    listeRechercheString.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            super.mouseClicked(e);
                            selectedItem = listeRechercheObjet.get(listeRechercheString.getSelectedIndex());
                        }
                    });

                    listeInventaireString.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            selectedItem = listeInventaireObjet.get(listeInventaireString.getSelectedIndex());
                        }
                    });


                    compteurElementsRecherche = new ArrayList<String>();
                    compteurElementInventaire = new ArrayList<String>();
                    compteurElementPanier = new ArrayList<String>();

                    compteurElementsRecherche.add(listeRechercheObjet.size() + " éléments correspondants à la recherche");
                    compteurElementInventaire.add(listeInventaireObjet.size() + " éléments dans l'inventaire");
                    compteurElementPanier.add(listePanierObjet.size() + " éléments dans le panier");

                    listeCompteurRecherche = new JList<>(compteurElementsRecherche.toArray(new String[0]));
                    listeCompteurInventaire = new JList<>(compteurElementInventaire.toArray(new String[0]));
                    listeCompteurPanier = new JList<>(compteurElementPanier.toArray(new String[0]));

                    counterField.remove(2);
                    counterField.remove(1);
                    counterField.remove(0);

                    counterField.add(listeCompteurRecherche);
                    counterField.add(listeCompteurInventaire);
                    counterField.add(listeCompteurPanier);
                    counterField.validate();
                    counterField.repaint();

                    listePanierString = new JList<>(inventory.printInventory(listePanierObjet).toArray(new String[0]));
                    fenetrePanier = new JScrollPane(listePanierString);
                    panneauFields.remove(2);
                    panneauFields.add(fenetrePanier, 2);
                    panneauFields.validate();
                    panneauFields.repaint();
                }
                else
                    JOptionPane.showMessageDialog(null,"Aucun objet sélectionné à supprimer du panier");


            }
        });

        listeRechercheString.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);
                selectedItem = listeRechercheObjet.get(listeRechercheString.getSelectedIndex());
            }
        });

        listeInventaireString.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                selectedItem = listeInventaireObjet.get(listeInventaireString.getSelectedIndex());
            }
        });

        ajouterAuPanierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedItem != null) {
                    listePanierObjet.add(selectedItem);
                    inventory.getCodeTree().deleteInAutomate(selectedItem.getIdentificator(), selectedItem);
                    inventory.getTypeTree().deleteInTypeAutomate(selectedItem.getType(), selectedItem);


                    listeRechercheObjet = inventory.filter("", "", null);
                    listeRechercheString = new JList<>(inventory.printInventory(listeRechercheObjet).toArray(new String[0]));
                    fenetreRecherche = new JScrollPane(listeRechercheString);

                    listeInventaireObjet = inventory.filter("", "", null);
                    listeInventaireString = new JList<>((inventory.printInventory(listeInventaireObjet).toArray(new String[0])));
                    fenetreInventaire = new JScrollPane(listeInventaireString);

                    panneauFields.remove(0);
                    panneauFields.add(fenetreRecherche, 0);
                    panneauFields.remove(1);
                    panneauFields.add(fenetreInventaire, 1);


                    listeRechercheString.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            super.mouseClicked(e);
                            selectedItem = listeRechercheObjet.get(listeRechercheString.getSelectedIndex());
                        }
                    });

                    listeInventaireString.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            selectedItem = listeInventaireObjet.get(listeInventaireString.getSelectedIndex());
                        }
                    });
                    selectedItem = null;
                    compteurElementsRecherche = new ArrayList<String>();
                    compteurElementInventaire = new ArrayList<String>();
                    compteurElementPanier = new ArrayList<String>();

                    compteurElementsRecherche.add(listeRechercheObjet.size() + " éléments correspondants à la recherche");
                    compteurElementInventaire.add(listeInventaireObjet.size() + " éléments dans l'inventaire");
                    compteurElementPanier.add(listePanierObjet.size() + " éléments dans le panier");

                    listeCompteurRecherche = new JList<>(compteurElementsRecherche.toArray(new String[0]));
                    listeCompteurInventaire = new JList<>(compteurElementInventaire.toArray(new String[0]));
                    listeCompteurPanier = new JList<>(compteurElementPanier.toArray(new String[0]));

                    counterField.remove(2);
                    counterField.remove(1);
                    counterField.remove(0);

                    counterField.add(listeCompteurRecherche);
                    counterField.add(listeCompteurInventaire);
                    counterField.add(listeCompteurPanier);
                    counterField.validate();
                    counterField.repaint();

                    listePanierString = new JList<>(inventory.printInventory(listePanierObjet).toArray(new String[0]));
                    fenetrePanier = new JScrollPane(listePanierString);
                    panneauFields.remove(2);
                    panneauFields.add(fenetrePanier, 2);
                    panneauFields.validate();
                    panneauFields.repaint();
                }
                else
                    JOptionPane.showMessageDialog(null,"Aucun objet sélectioné pour ajouter au panier");

            }
        });
    }


    public static void main(String[] args){
        Tree nameTree = new Tree(new Node());
        Tree codeTree = new Tree(new Node());
        Tree typeTree = new Tree(new Node());
        Inventory inventory = new Inventory(nameTree, codeTree, typeTree);


        // ------------------Entrez le nom du fichier du lexique ici----------------------------------------------------

        String fileName = "./inventaire.txt";

        //--------------------------------------------------------------------------------------------------------------

        inventory.createInventory(fileName);

        JFrame frame = new JFrame("TP2");
        frame.setContentPane(new Main(inventory).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
