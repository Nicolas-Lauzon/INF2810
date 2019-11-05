##x="abcde"
##for i in range (0, len(x)):
##    print(x[i])
#def calculerTemps(constante, distance):
    #return (constante * distance)

#from partie_nicolas import trouver_min, dijkstra, ramassage_paquet, plusCourtChemin, choisir_robot
from TP1_fonctions import plusCourtChemin,creerGraphe, afficherGraphe, prendreCommande, afficherCommande, robot


robotX = robot(5,"X")
robotY = robot(10,"Y")
robotZ = robot(25,"Z")
robot = [robotX, robotY, robotZ]

commandePrise = 0
optionChoisie = 0
grapheCree = 0
fichier = open("entrepot.txt", "r")
while(1):   
    optionChoisie=input("Que voulez-vous faire ? (Entrer le numero)\n 1. creer le graphe \n 2. afficher le graphe \n 3. prendre une commande \n 4. afficher la commande \n 5. trouver le plus court chemin \n 6. quitter \n")
    #optionChoisie = input()
    if(optionChoisie == "1"):
        graphe = creerGraphe(fichier)
        grapheCree = 1
        print("le graphe a ete cree !")

    elif(optionChoisie == "2"):
        if(grapheCree == 1):
            afficherGraphe(graphe)
        else:
            print("le graphe n'a pas ete cree")

    elif(optionChoisie == "3"):
        commandePrise = 1
        commande = prendreCommande()       

    elif(optionChoisie == "4"):
        if(commandePrise == 1):
            afficherCommande(commande)
        else:
            print("Aucune commande n'a ete prise")

    elif(optionChoisie == "5"):
        if(grapheCree == 1 and commandePrise == 1):
            #print(type(graphe[0][0][0]))
            aa=plusCourtChemin(graphe[0],graphe[1],commande,robot)
        elif (grapheCree == 0):
            print("le graphe n'a pas ete cree")
        else:
            print("La commande n'a pas été prise")

    elif(optionChoisie == "6"):
        break

    else:
        print("Choix invalide")


while (1):
    fini= input("OK BYEBYE: appuyer sur entrer pour fermer")
    if (fini == ""):
        break






