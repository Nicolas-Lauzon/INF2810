##x="abcde"
##for i in range (0, len(x)):
##    print(x[i])
#def calculerTemps(constante, distance):
    #return (constante * distance)

def constanteRobotX(masse):
    return (1 + masse)

def constanteRobotY(masse):
    return (1,5 + 0,6 * masse)

def constantRobotZ(masse):
    return (2,5 + 0,2 * masse)

def prendreCommande():
    nObjetA = input("Entrez le nombre d'objets A ")
    nObjetB = input("Entrez le nombre d'objets B ")
    nObjetC = input("Entrez le nombre d'objets C ")
    return [nObjetA, nObjetB, nObjetC]

def afficherCommande(commande):
    print(commande[0], commande[1], commande[2]) 

def calculerMasseTotale(commande):
    return commande[0]*1 + commande[1]*3 + commande[2]*6



#def robotsVSCharge(masse, robots):
    
    #if (masse <= robots[0].poidsMax)
        #return robots[

class robot:
    def __init__(self, poidsMax):
        self.poidsMax = poidsMax
        
def main() :
    robotX = robot(5)
    robotY = robot(10)
    robotZ = robot(25)
    robot = [robotX, robotY, robotZ]

    #if (calculerMasseTotale(prendreCommande) <= robotX.poidsMax)
        #return robot[robotX, robotY, robotZ];
    

def creerGraphe(f):
    tab = []
    tab2 = []
    section = 0
    m=0
    while (1):
        if (section == 0):
            line=f.readline()
            if (line == "\n"):
                section +=1
                continue
            line_split=line.split(",")
            n=int(line_split[0])
            x=int(line_split[1])
            y=int(line_split[2])
            z=int(line_split[3])
            comp=[x,y,z]
            tab.append([])
            for i in range (0,3):
                tab[n].append(comp[i])
        if (section == 1):
            line=f.readline()
            if (line==""):
                break
            line_split=line.split(",")
            N1= int(line_split[0])
            N2= int(line_split[1])
            D= int(line_split[2])
            comp=[N1,N2,D]
            tab2.append([])
            for i in range (0,3):
                tab2[m].append(comp[i])
            m+=1  
        
    return [tab,tab2]

def trouver_min(tab):
    min = tab[1][0]
    index=0
    for i in range(0,len(tab)):
        if (tab[i][1] < min):
            min=tab[i][1]
            index=i
    return index


def dijkstra(deb,fin,tab):
    tab_zone= []
    tab_calcul=[]
    
    tab_zone.append([])
    tab_zone[0].append(deb)
    tab_zone[0].append(int(0))
    
    current =-1
    m=1
    longueur =0

    while(current != fin):
        n=0
        tab_calcul.clear()
        for k in range (0,len(tab_zone)):
            for i in range(0,len(tab)):
                for j in range (0,1):
                    if (tab[i][j] == tab_zone[k][0]):
                        tab_calcul.append([])
                        if (j == 0):
                            tab_calcul[n]
                            tab_calcul[n].append(tab[i][1])
                        else:
                            tab_calcul[n].append(tab[i][0])
                        tab_calcul[n].append(tab[i][2] + tab_zone[k][1])
                        n+=1
        for i in tab_zone[:]:
            if i in tab_calcul:
                tab_calcul.remove(i)
        ind = trouver_min(tab_calcul)
        tab_zone.append([])
        tab_zone[m].append(tab_calcul[ind][0])
        tab_zone[m].append(tab_calcul[ind][1])

        current = tab_zone[m][0]
        longueur = tab_zone[m][1] 
        m+=1
        print("tab calcul: ",tab_calcul,"\n")
        print("tab zone: ", tab_zone, "\n")
        print("Longueur= ",longueur,"\n")
    return [tab_zone, longueur]



def afficherGraphe() :
    f=open("entrepot.txt", "r")
    x=creerGraphe(f)
   
    
    for i in range (0, len(x[0])):
        premierElement = 1
        print("(", i, ",", x[0][i][0], ",", x[0][i][1], ",", x[0][i][2], ",", "(", end = '')
        for j in range (0, len(x[1])):
            if x[1][j][0] == i:
                print("(", x[1][j][1], ",", x[1][j][2], ")", end = '')
                
                premierElement = 0
            elif x[1][j][1] == i:
                print("(", x[1][j][0], ",", x[1][j][2], ")", end = '')
                
                premierElement = 0
            
        print("))")
    
    
        
#f=open("entrepot.txt", "r")
#x=lecture_fichier(f)
#afficherGraphe()
#deb = int(input("quel est le point de départ? "))
#fin = int(input("quel est le point d'arrivée? "))
#aa=djikstra(deb,fin,tableau2)
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
            afficherGraphe()
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
        if(grapheCree == 1):
            dijkstra(0, 0, graphe)
        else:
            print("le graphe n'a pas ete cree")
    elif(optionChoisie == "6"):
        
        break
    else:
        print("Choix invalide")


while (1):
    fini= input("OK BYEBYE: appuyer sur entrer pour fermer")
    if (fini == ""):
        break






