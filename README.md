# CharApex

## Présentation
Projet de programmation mobile, dans lequel il faut créer une application qui affiche une liste (cf Consignes respectées pour plus de détails). Ici on affiche une liste des personnages du jeu vidéo Apex Legends.

## Prérequis
*Installer Android Studio
* Récupérer la branche master

## Consignes respectées
* Ecran avec une liste d'élements
* Ecran avec le détail d'un élément
* Appel WebService à une API Rest
* Stockage de données en cache

## Ajouts personnels
* MVC
* Singleton
* Principes SOLID
* Splash Screen au début

## Fonctionnalités
### Premier écran
* Splash qui affiche le logo du jeu Apex Legends
![Splash Screen](https://github.com/Laartem/CharApex/blob/master/Images%20Readme/Screenshot_1592777377.png)

### Ecran principal
* Liste des légendes d'Apex Legends, affichées par appel API la première fois, puis en allant chercher les données en cache les fois suivantes
![Ecran Principal](https://github.com/Laartem/CharApex/blob/master/Images%20Readme/Screenshot_1592777504.png)

### Ecran de détails
* Ecran qui s'affiche en cliquant sur une légende, affichant des informations sur ses capacités.
* J'ai eu quelques soucis avec cette partie, seule une capacité par légende s'affiche
![Ecran de Details](https://github.com/Laartem/CharApex/blob/master/Images%20Readme/Screenshot_1592777551.png)


## Pistes d'amélioration
* Ajout d'une API pour avoir des news sur le jeu (quand il y a une mise à jour du jeu par exemple).
* Ajout de notifications push si un nouveau personnage est ajouté à la liste
* Changement d'affichage
* Ajout de photos pour chaque personnage
