
def constanteRobotX(masse):
    return (1 + masse)

def constanteRobotY(masse):
    return (1.5 + 0.6 * masse)

def constanteRobotZ(masse):
    return (2.5 + 0.2 * masse)

def calculerMasseTotale(commande):
    return commande[0]*1 + commande[1]*3 + commande[2]*6


def prendreCommande():
    nObjetA = int(input("Entrez le nombre d'objets A "))
    nObjetB = int(input("Entrez le nombre d'objets B "))
    nObjetC = int(input("Entrez le nombre d'objets C "))
    return [nObjetA, nObjetB, nObjetC]

def afficherCommande(commande):
    print("Nombre de paquets A:  ", commande[0], "\n")
    print("Nombre de paquets B:  ", commande[1], "\n")
    print("Nombre de paquets C:  ", commande[2], "\n")



def calculerMasseTotale(commande):
    return commande[0]*1 + commande[1]*3 + commande[2]*6


class robot:
    def __init__(self, poidsMax,nom):
        self.poidsMax = poidsMax
        self.nom=nom  

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
    del m
    del section
    return [tab,tab2]




def afficherGraphe(x) :
    
    for i in range (0, len(x[0])):
        premierElement = 1
        print("(", i, ",", x[0][i][0], ",", x[0][i][1], ",", x[0][i][2], ",", "( ", end = '')
        for j in range (0, len(x[1])):
            if x[1][j][0] == i:
                if (premierElement == 1):
                   print("(", x[1][j][1], ",", x[1][j][2], ")",end="")
                   premierElement=0
                else:
                    print(",(", x[1][j][1], ",", x[1][j][2], ")", end="")
                
                
            elif x[1][j][1] == i:
                if (premierElement == 1):
                    print("(", x[1][j][0], ",", x[1][j][2], ")", end="")
                    premierElement =0
                else:
                    print(",(", x[1][j][0], ",", x[1][j][2], ")", end = "")
                
                
        del premierElement    
        print(" ))")
    
    




def trouver_min(tab,pos):
    index=0
    for i in range(0,len(tab)):
        if (i == 0):
            min = tab[0][pos]
        elif (tab[i][pos] < min):
            min=tab[i][pos]
            index=i
    return index


def dijkstra(deb,fin,tab):
    tab_zone= []
    tab_calcul=[]
    
    tab_zone.append([])
    tab_zone[0].append(deb)
    tab_zone[0].append(int(0))
    tab_zone[0].append([deb])
    
    current =-1
    m=1
    longueur =0

    while(current != fin):
        n=0
        tab_calcul.clear()
        for i in range(0,len(tab)):
            for j in range(0,(len(tab[i])-1)):
                for k in range(0,len(tab_zone)):
                    if (tab[i][j] == tab_zone[k][0]):
                        #ajouter les noeuds adjacents a la zone dans le tableau calcul
                        tab_calcul.append([])
                        if (j==0):
                            tab_calcul[n].append(tab[i][1])
                        else:
                            tab_calcul[n].append(tab[i][0])
                        tab_calcul[n].append(tab[i][2] + tab_zone[k][1])

                        #copier le chemin deja emprunté et l'associer au nouveau noeud
                        tab_calcul[n].append([])
                        for l in range(0,len(tab_zone[k][2])):
                            tab_calcul[n][2].append(tab_zone[k][2][l])
                        tab_calcul[n][2].append(tab_calcul[n][0])

                        n+=1

        #retirer les noeuds du tableau calcul s'ils sont déja dans la zone                
        for i in range (0,len(tab_zone)):
            pos=0
            while(1):
                if (pos >= len(tab_calcul)):
                    break
                elif (tab_zone[i][0] == tab_calcul[pos][0]):
                    tab_calcul.pop(pos)
                else:
                    pos+=1
        #mettre le noeuds adjacent a la zone avec la plus petite distance, dans la zone

        ind = trouver_min(tab_calcul,1)
        tab_zone.append([])
        tab_zone[m].append(tab_calcul[ind][0])
        tab_zone[m].append(tab_calcul[ind][1])
        tab_zone[m].append(tab_calcul[ind][2])
        current = tab_zone[m][0]
        longueur = tab_zone[m][1]
        plus_court_chemin = tab_zone[m][2]
        m+=1
        
    return [longueur, plus_court_chemin]



def ramassage_paquet(lettre):
    if(lettre == "A"):
        commande_prise = "Paquet A ramassé"
    elif(lettre == "B"):
        commande_prise = "Paquet B ramassé"
    elif(lettre == "C"):
        commande_prise = "Paquet C ramassé"

    return commande_prise    


def calculerMasseTotale(commande):
    return commande[0]*1 + commande[1]*3 + commande[2]*6



def plusCourtChemin(graph1,graph2,commande,robot):
    noeud_commandeA=[]
    noeud_commandeB=[]
    noeud_commandeC=[]
    commandes_combinee=[]
    current_A = 0
    current_B = 0
    current_C = 0
    cheminA=0
    cheminB=0
    cheminC=0
    noeudA=0
    noeudB=0
    noeudC=0
    if (commande[0] == 0 and commande[1] == 0 and commande[2] == 0):
        print("La commande est vide")
        return
    
    for i in range (0,len(graph1)):
        if (graph1[i][0] > 0 and commande[0] > 0):
            noeudA= i
            cheminA = dijkstra(0,noeudA,graph2)
            noeud_commandeA.append([])
            noeud_commandeA[current_A].append(cheminA[0])
            noeud_commandeA[current_A].append(cheminA[1])
            noeud_commandeA[current_A].append(noeudA)
            noeud_commandeA[current_A].append("A")
            current_A+=1

        if (graph1[i][1] > 0 and commande[1] > 0):
            noeudB= i
            cheminB = dijkstra(0,noeudB,graph2)
            noeud_commandeB.append([])
            noeud_commandeB[current_B].append(cheminB[0])
            noeud_commandeB[current_B].append(cheminB[1])
            noeud_commandeB[current_B].append(noeudB)
            noeud_commandeB[current_B].append("B")
            current_B+=1
            
        if (graph1[i][2] > 0 and commande[2] > 0):
            noeudC= i
            cheminC = dijkstra(0,noeudC,graph2)
            noeud_commandeC.append([])
            noeud_commandeC[current_C].append(cheminC[0])
            noeud_commandeC[current_C].append(cheminC[1])
            noeud_commandeC[current_C].append(noeudC)
            noeud_commandeC[current_C].append("C")
            current_C+=1

    noeud_commandeA.sort()
    noeud_commandeB.sort()
    noeud_commandeC.sort()

    if (commande[0] > len(noeud_commandeA) or commande[1] > len(noeud_commandeB) or commande[2] > len(noeud_commandeC)):
        print("La commande est impossible, car il n'y a pas tout les paquets de la commande dans l'entrepot")
        return
    
    for i in range(0,commande[0]):
        commandes_combinee.append(noeud_commandeA[i])
    for i in range(0,commande[1]):
        commandes_combinee.append(noeud_commandeB[i])
    for i in range(0,commande[2]):
        commandes_combinee.append(noeud_commandeC[i])


    del noeud_commandeA
    del noeud_commandeB
    del noeud_commandeC
    del cheminA
    del cheminB
    del cheminC
    del current_A
    del current_B
    del current_C
    del noeudA
    del noeudB
    del noeudC
    
    commandes_combinee.sort()
    commandes_combinee.reverse()
    
    
    tab_trajet=[]
    tab_segment=[]
    distance=0
    seg_courant=1
    
    distance+=dijkstra(0,commandes_combinee[0][2],graph2)[0]
    tab_trajet.append(dijkstra(0,commandes_combinee[0][2],graph2)[1])
    tab_trajet.append(ramassage_paquet(commandes_combinee[0][3]))

    tab_segment.append([])
    tab_segment[0].append("")
    tab_segment[0].append(distance)

    tab_segment.append([])
    tab_segment[seg_courant].append(tab_segment[seg_courant-1][0] + commandes_combinee[0][3])

    
    pos_courante=commandes_combinee[0][2]
    commandes_combinee.pop(0)
    
    while(1):
        if (len(commandes_combinee)==0):
            temp=dijkstra(pos_courante, 0, graph2)[0]
            distance+=temp
            tab_trajet.append(dijkstra(pos_courante, 0, graph2)[1])
            tab_segment[seg_courant].append(temp)
            seg_courant+=1

            break

        minimum=dijkstra(pos_courante , commandes_combinee[0][2] , graph2)[0]
        index=0
        for i in range(0,len(commandes_combinee)):
            temp = dijkstra(pos_courante,commandes_combinee[i][2],graph2)
            if (temp[0] < minimum):
                index=i
                minimum=temp[0]

        tab_trajet.append(temp[1])
        tab_trajet.append(ramassage_paquet(commandes_combinee[index][3]))
        distance+=minimum

        tab_segment[seg_courant].append(minimum)
        seg_courant+=1
        tab_segment.append([])
        tab_segment[seg_courant].append(tab_segment[seg_courant - 1][0] + commandes_combinee[index][3])
        
        pos_courante=commandes_combinee[index][2]
        commandes_combinee.pop(index)

    del seg_courant
    del minimum
    del temp
    del index
    

    
    masse_totale = calculerMasseTotale(commande)

    robot = choisir_robot(robot , tab_segment , masse_totale)

    del tab_segment
    print("le trajet emprunté par le robot est: ",tab_trajet,"\n")
    del tab_trajet
    print("le meilleur robot est le robot: ", robot[0], "   avec un temps de ", robot[1], " secondes \n")

    
    return [distance , robot]
        


def choisir_robot(robot, tab_segment, masse_totale):
    if (masse_totale > robot[0].poidsMax):
        robot.pop(0)
        if (masse_totale > robot[0].poidsMax):
            robot.pop(0)
            if (masse_totale > robot[0].poidsMax):
                robot.pop(0)
                print("le poid est trop élevé")
                return

    for i in range (0,len(robot)):
        masse=0
        temps=0
        for j in range (0,len(tab_segment)):
            masse=0
            for k in tab_segment[j][0]:
               if (k == "A"):
                   masse+=1
               elif(k=="B"):
                    masse+=3
               elif(k=="C"):
                    masse+=6
                    
            if (robot[i].nom == "X"):
                temps+=(constanteRobotX(masse) * tab_segment[j][1])

            elif(robot[i].nom == "Y"):
                temps+=(constanteRobotY(masse) * tab_segment[j][1])

            elif(robot[i].nom == "Z"):        
                temps+=(constanteRobotZ(masse) * tab_segment[j][1])

        if (i==0):
            minimum= temps
            meilleur_robot=robot[0].nom

        else:
            if (temps < minimum):
                minimum = temps
                meilleur_robot = robot[1].nom
            else:
                temps=minimum
    del masse
    del minimum
                
    return [meilleur_robot, temps]
            

    

    
                

        


