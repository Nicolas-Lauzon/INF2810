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
    
    
    
