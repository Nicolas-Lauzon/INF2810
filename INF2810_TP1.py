##x="abcde"
##for i in range (0, len(x)):
##    print(x[i])




def lecture_fichier(f):
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


def djikstra(deb,fin,tab):
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

    
        
f=open("entrepot.txt", "r")
x=lecture_fichier(f)
tableau1=x[0]
tableau2=x[1]
deb = int(input("quel est le point de départ? "))
fin = int(input("quel est le point d'arrivée? "))
aa=djikstra(deb,fin,tableau2)






