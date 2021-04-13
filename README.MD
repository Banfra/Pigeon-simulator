## 8INF957
Bastien Anfray - Clément de Gaillande

## Question 1 : Pigeons

# Instructions pour jouer au jeu :

Pour jouer au jeu, lancer le programme via le main présent dans la classe Main.java
Une fenêtre s'ouvrira, le jeu débutera automatiquement.

Pour ajouter de la nourriture, il vous suffit de cliquer sur l'écran.

## Rapport

Dans cette question, nous devions simuler un système de pigeons qui mangent du pain.


Le code est séparé en plusieurs parties : La fenêtre d’affichage, les pigeons et la nourriture.


La classe Fenêtre représente l’environnement. Cette classe s’occupe de la création et de 
l’affichage de l’environnement, ainsi que de la création des entités dans l’environnement. 
C’est cette classe qui créée les pigeons au début du programme et la nourriture lorsque l’on 
clique sur l’écran.

Cet environnement a d’ailleurs été réalisé avec le package java.awt et se déroule en même 
temps que les autres threads du programme. Cela signifie qu’on peut créer de nouvelles 
nourritures à tout moment du programme.


La classe Entité est un Jpanel permettant d’afficher les entités dans la fenêtre. Cette classe 
contient des coordonnées x et y permettant d’afficher les entités à différents endroits de la 
fenêtre.

Dans le cas présent, les entités sont affichées via des images qui leurs sont attribuées dans 
leur classe respective (Pigeon et Nourriture). Lors de l’affichage, l’environnement fait appel 
aux fonctions paintComponent() de ces classes.


La classe Pigeon hérite de la classe Entité. Cette classe s’occupe du comportement du pigeon 
tel que ses déplacements vers la nourriture ou encore le fait de manger la nourriture.

Chaque pigeon est contrôlé par un thread. Ces threads sont lancés par la classe
runPigeonThread. D’ailleurs, s’il n’y a pas de nourriture fraiche dans l’environnement, les 
pigeons s’endormiront et resteront sur place (ou seront dispersés de temps en temps).


La classe runPigeonthread s’occupe de la dispersion des pigeons. Il va calculer de façon 
aléatoire une probabilité que les pigeons se dispersent, et peut attribuer de nouvelles 
coordonnées aléatoires aux pigeons.


La classe Nourriture hérite de la classe Entité. Elle sert principalement à l’affichage des 
images de nourriture. Lorsqu’une nourriture est touchée par un pigeon, elle disparait 
immédiatement de l’environnement.


La classe runNourritureThread s’occupe de rendre les nourritures fraiches ou non. Pour cela, 
chaque nourriture possède une limite qui décrémente à chaque boucle. Une fois que la 
limite atteint la valeur 0, la nourriture devient périmée. La nourriture périmée disparait 
d’ailleurs lorsque la limité atteint -20