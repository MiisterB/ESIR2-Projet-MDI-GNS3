## Installation

### 1 Installation globale 

<p> L'installation du projet est plutôt simple. </p>

  * Copier l'adresse du répertoire du projet 
 
![alt text](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/blob/develop/repogit.PNG)

  * Depuis votre console, cloner le dépôt
  
  ```
  git clone https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3.git
  ```
  * Vous avez accès aux fichiers du projet depuis votre machine 

### 2 Installation spécifique

#### 2.1 Utiliser les sources

Si vous voulez utiliser l'API depuis les sources ou modifier les sources : 

  * Installer JDK _version 1.9_ si vous ne l'avez pas encore. 

  Vous pouvez le télécharger [ici](https://jdk.java.net/java-se-ri/9)

  * Ajouter la librairie `commons-net-3.6` :

     * Aller dans le dossier `lib` à la racine du projet
     * Ajouter le jar `commons-net-3.6.jar` au projet (sur IntelliJ : clique-droit puis `Add as library`)

#### 2.2 Utiliser le JAR

  <p> Si vous voulez juste utiliser les méthodes de l'API , il faut ajouter le jar à votre projet. Pour celà il suffit de le télécharger puis de l'ajouter comme librairie extérieur avec votre IDE, ou bien de l'ajouter au moment de compiler votre projet java. Vous avez maintenant accès à l'API GNS3 de l'équipe projet *stob-gns*.</p>
