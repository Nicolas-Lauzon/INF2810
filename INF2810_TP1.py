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
        
f=open("entrepot.txt", "r")
x=lecture_fichier(f)
tableau1=x[0]
tableau2=x[1]

