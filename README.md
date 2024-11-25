# 🌲🔥 Simulation de Feu de Forêt 🔥🌲

## 📖 Description
Ce projet implémente une **simulation de la propagation d’un feu de forêt** à travers une grille.  
À chaque étape :
- 🌟 Les cases en feu sont transformées en **cendres**.
- 🔥 Le feu peut se **propager** aux cases adjacentes (haut, bas, gauche, droite) avec une probabilité `p`.

La simulation continue jusqu’à ce qu’il n’y ait plus de cases en feu.

---

## 🛠️ Fonctionnalités
- ✅ Grille de dimensions personnalisables.
- ✅ Configuration des cases initialement en feu.
- ✅ Probabilité de propagation paramétrable.
- ✅ Calcul du **nombre total de cases réduites en cendre**.
- ✅ Détermination du **nombre total d'étapes nécessaires**.

---

## ⚙️ Configuration
### 📁 Fichier de configuration : `config.properties`
Le fichier `config.txt` contient les paramètres nécessaires à la simulation :
- **`hauteur`** : Hauteur de la grille.
- **`largeur`** : Largeur de la grille.
- **`departFeu`** : Liste des coordonnées des cases initialement en feu (séparées par `;`).
- **`probabilitePropagation`** : Probabilité (entre 0 et 1) que le feu se propage à une case adjacente.

Exemple de fichier `config.properties` :
```properties
# Forest dimensions
height=50
width=50
# Initial fire locations
fireStart=5,10;15,20;30,40
# Propagation probability
propagationProbability=0.3
```

## 🚀 Instructions d’exécution
### 💻 Prérequis
- Java 21
- Maven 3.8.4

## 📊 Résultats
Le programme retourne deux informations principales dans la console :

1. Nombre total de cases réduites en cendre :
   - Représente combien de cases ont été brûlées au cours de la simulation.
2. Nombre d’étapes nécessaires :
   - Indique combien d’étapes ont été nécessaires pour éteindre le feu.

## Structure de l'algorithme
1️⃣ Initialisation
- Chargement des paramètres à partir de `config.properties`.
- Placement des cases initiales en feu dans une file d’attente (queue).

2️⃣ Simulation
- À chaque étape :
    - Extinction du feu dans les cases en feu (elles deviennent cendres).
    - Propagation du feu aux cases adjacentes avec une probabilité `p`.

3️⃣ Condition d'arrêt
- La simulation s’arrête lorsque toutes les cases en feu ont été traitées.

4️⃣ Résultats
- Nombre total de cases brûlées.
- Nombre d’étapes nécessaires.



