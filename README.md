# Projet MDI de GNS3 de l'équipe *STOB-GNS*

## 1. Description 

### 1.1 Description du projet

  <p> Le projet consiste à créer une _API fluent pour GNS3_.

  <p> GNS3 est un logiciel libre permettant l'émulation ou la simulation de réseaux informatiques. Il est beaucoup utilisé dans le domaine des réseaux et télecom.</p> 

  <p> Une API REST GNS3 étant déjà existante, nous devons la prendre en main, la rendre fluent (c'est-à-dire qu'il deviendra possible d'avoir des compositions de méthodes), et y ajouter des méthodes de plus haut niveau pour faciliter l'expérience utilisateur.</p>

### 1.2 Du cahier des charges aux specs

  Après avoir compris les enjeux du projet, nous les avons mis en pages dans un cahier des charges ( voir [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Cahier-des-charges)). 
  
   Cela nous a permis de bien mettre à plat nos idées, ce qui nous a permis de concevoir la structure du projet, mais aussi les specs (voir [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Specs)).
  
   Le travail a été continue tout au long du projet. Pour surveiller notre évolution dans le temps nous avons, à chaque séance, réalisée un compte rendu de séance (voir [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Compte-rendus-des-s%C3%A9ances) ). Cela nous a beaucoup aidé lors de la ré-orientation des tâches à accomplir.

### 1.3 Liens utiles à la compréhension du projet

  <p> Ici, vous trouverez les liens utiles à une compréhension plus approfondie du projet :</p>

 * [Slides du projet](https://docs.google.com/presentation/d/1Y9bsva_MXeW0O-p26TSM3rojNJ_YYPb8EkOBdd_jSNk/edit#slide=id.p)
 * [Wiki du projet](https://github.com/MiisterB/ESIR2-Projet-MDI-GNS3/wiki)
 * [Kanban du projet](https://github.com/MiisterB/ESIR2-Projet-MDI-GNS3/projects/1)


## 2. Installation

### 2.1 Installation globale 

<p> L'installation du projet est plutôt simple. </p>

  * Copier l'adresse du répertoire du projet 
 
![alt text](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/blob/develop/repogit.PNG)

  * Depuis votre console, cloner le repo
  
  ```
  git clone https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3.git
  ```
  * Vous avez accès au projet depuis votre machine 

### 2.2 Installation spécifique

#### 2.2.1 Utiliser les sources

Si vous voulez utiliser l'API depuis les sources ou modifier les sources : 

  * Installer JDK _verion 1.9_ si vous ne l'avez. 

  Vous trouverez le lien pour le télécharger au lien [suivant](https://jdk.java.net/java-se-ri/9)

  * L'une des librairies n'est pas synchronisée automatiquement, il faut le faire maniellement.

     * Dans le dossier lib, clique-droit sur commons-net-3.6.jar
     * Synchroniser la lib :  'Synchronize commons-net-3.6.jar'

#### 2.2.2 Utiliser le JAR

  <p> Si vous ne voulez qu'utilisez l'API, il vous suufit d'utiliser le jar. Pour l'utiliser il suffit de le compiler dans le projet dans lequel il sera utilisé. Vous avez maintenant accès à l'API GNS3 de l'équipe projet STOB.</p>

## 3. Structure de l'API

  La structure de l'API a été pensé pour être optimisée au maximum. Le diagramme de classe suivant représente notre architecture :
![class_diagram](./class_diagramm.png)

  Vous pourrez trouver au niveau de la page [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Architecture-et-int%C3%A9gration-continue) suivante plus de détail sur l'archietecture et l'intégration continue. 


## 4. Utilisation 

### 4.1 Détails du GitFlow

  <p> Quand on veut développer une nouvelle fonctionnalité :</p> 
 * On commence par pull la branche *develop* 

```
git pull origin develop
```
...

  Pour avoir plus détail sur la façon de développer de nouvelles fonctionnalités : voir 
  [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Détail-du-GitFlow)
  .

### 4.2 Liens pratiques JAVA

   Pour toutes questions de programmation vous trouverez des indications et début de piste de réponse au lien [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Liens-pratiques-Java) suivant. Vous y trouverez :
  
  * Des tutos de prise en main de Spring
  * Des tutos de prise en main de REST template
  * La façon dont on manipule le JSON depuis JAVA 

### 4.3 Créationde structure avec l'API Fluent

Il est possible de créer des structures rapidement grâce à l'API Fluent et la class Structure du projet. Cette dernière permet de créer un noeud central de type "ethernet_switch" autour duquel se trouve d'autre noeuds dont on peut choisir le type. Il existe plusieurs constructeurs pour créer une strcuture :

* public Structure(String name, Controller controller, String projectName, int nbrNode, String nodeType, int xPos, int yPos, String orientation) : constructeur complet, où on peut choisir la position, l'orientation et le type du noeud.
* public Structure(String name, Controller controller, String projectName, int nbrNode, String nodeType, int xPos, int yPos) : constrcuteur basique, où peut on peut choisir la position et le type de noeud mais pas l'orientation (on a donc l'orientation de base qui est celle en étoile).
* public Structure(String name, Controller controller, String projectName, int nbrNode) : constructeur par défaut, où on a le type de noeud par défaut (vpcs), l'orientation par défaut (en étoile) et la position par défaut (0,0).

En ce qui concerne les paramêtres d'une strcture, voici quelques détails :

* Le type de noeud : correspond au type de noeud qui sera généré autour du noeud central (ou noeud de jonction), le noeud de base est le noeud vpcs qui comporte un seul port. Dans le cas où on choisit un type de noeud qui possède plus d'un port (autre qu'un noeud vpcs), les noeuds seront reliés les uns aux autres de la manière suivante : le 1er noeud lié au 2ème, le 2ème lié au 3ème, etc...  Cependant, pour que le premier et le dernier noeud soient liés entre eux il faut que l'orientation de la structure soit en étoile.
* La position (xPos et yPos) : correspond aux coordonnées du noeud central de la structure, la position des autres noeuds sera généré à partir de ces coordonnées.
* L'orientation : correspond à la manière dont la structure va être orienté, elle peut être orienté de 2 manières différentes :
  * Orientation linéaire : dans le cas où l'orientation est égal à : Right, Left, Up et Down. L'orientation sera linéaire et elle sera placé en fonction de la direction donnée (vers la droite pour Right par exemple).
  * Orientation en étoile (défaut) : c'est l'orientation par défaut, si on écrit autre chose que Right, Left, Up ou Down. Les noeuds seront positionnés tout autour du noeud central. Ils seront reliés entre eux si le type de noeud contiens un nombre de port supérieur à 1.
  
Il est possible de relier des noeuds à une structure dans le cas où elle possède encore des ports de libres grâce à la méthode connectNode(Node node, int intPort). Avec cette méhtode, on choisit quelle noeud on veut relier au noeud (node) de jonction de la structure et quel port du noeud à ajouter on veut utiliser (intPort). Si jamais le noeud de jonction de la structure ou le noeud externe ne possède pas de port libre, il n'y aura pas de lien de créé entre les deux et on aura un message d'information pour l'indiquer.

## 5. Contributeurs

### 5.1 Compositions de l'équipe

<p> L'équipe projet STOB-GNS3 est coposée de :</p>

* Simon Moisan
* Timothée Schneider-Maunoury
* Oumar Ballo
* Besma Kaouane

<p> L'origine du nom du groupe provient de nos initials..</p>

### 5.2 Répartition des rôles

 Voir [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/R%C3%B4les)

### 5.3 Pratiques Agiles

  Pour la réalisation de ce projet, nous avons essayé au maximum d'appliquer les principes agiles qui nous ont été enseignés (voir [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Pratiques-agiles) ).
