# Projet MDI de GNS3 de l'équipe *STOB-GNS*

## 1. Description 

  ### 1.1 Description du projet

  <p> Le projet consiste à créer une _API fluent pour GNS3_.

  <p> GNS3 est un logiciel libre permettant l'émulation ou la simulation de réseaux informatiques. Il est beaucoup utilisé dans le domaine des réseaux et télecom.</p> 

  <p> Nous avons utilisé l'API REST GNS3 déjà existante permettant de communiquer avec un serveur GNS3 pour developper une API Java fluent (c'est-à-dire qu'il est facile de faire des compositions de méthodes car chaque méthode retourne l'objet sur lequel elle est appelé). Nous avons également imaginé des des méthodes de plus haut niveau pour faciliter l'expérience utilisateur et créer rapidement des topologies de réseaux complexes.</p>

  ### 1.2 Du cahier des charges aux specs

  Après avoir compris les enjeux du projet, nous les avons mis en pages dans un cahier des charges ( voir [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Cahier-des-charges)). 
  
   Cela nous a ammené à bien mettre à plat nos idées, ce qui nous a permis de concevoir la structure du projet, mais aussi les specs (voir [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Specs)).
  
   Pour surveiller notre évolution dans le temps nous avons, à chaque séance, réalisé un compte rendu (voir [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Compte-rendus-des-s%C3%A9ances)). Ces réunions régulières nous ont beaucoup aidé lors de la ré-orientation des tâches à accomplir.

  ### 1.3 Liens utiles à la compréhension du projet

  <p> Ici, vous trouverez les liens utiles à une compréhension plus approfondie du projet :</p>

 * [Slides du projet](https://docs.google.com/presentation/d/1Y9bsva_MXeW0O-p26TSM3rojNJ_YYPb8EkOBdd_jSNk/edit#slide=id.p)
 * [Wiki du projet](https://github.com/MiisterB/ESIR2-Projet-MDI-GNS3/wiki)
 * [Kanban du projet](https://github.com/MiisterB/ESIR2-Projet-MDI-GNS3/projects/1)

## 2. Utilisation 

  ### 2.1 Détails du GitFlow

  <p> Quand on veut développer une nouvelle fonctionnalité :</p> 
 * On commence par pull la branche *develop* 

```
git pull origin develop
```
...

  Pour avoir plus détail sur notre gestion des branches git : voir 
  [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Détail-du-GitFlow)
  .

  ### 2.2 API fluent de l'équipe STOB-GNS3

   #### 2.2.1 Création de structure depuis l'API

  Il est possiblle dans la nouvelle API fluent de crée des structures complexes (comme des réseaux en étoile, ...). Pour plus de détails voir le [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Librairie-Structure-de-l'API-STOB-GNS3) suivant.

   #### 2.2.2 Ecriture dans la console des noeuds depuis l'API
  La plupart des noeuds instanciés dans les projets GNS3 (routeurs, vpcs, containers docker, machines virtuelles linux, ...) sont accessibles depuis le réseaux. Nous avons donc implémenté des méthodes permettant d'envoyer directement une commande à exécuter sur un noeud, avec un simple `node.sendCmd("$commande")`. Ces méthodes ouvrent un connexion telnet sur le noeud en précisant le port qui lui correspond, et peuvent aussi lire les résultats de ces exécutions.
  
   #### 2.2.3 Edition de la configuration des noeuds depuis l'API
   Souvent quand on crée une topologie avec GNS3, il est necessaire de modifier la configuration réseau de plusieurs noeuds (DHCP, ip statique, ...). Notre API offre un moyen rapide de récupérer et/ou modifier cette configuration avec juste un appel de méthode sur le noeud concerné.

  ### 2.3 Liens pratiques JAVA

   Pour toutes questions de programmation vous trouverez des indications et début de piste de réponse au lien [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Liens-pratiques-Java) suivant. Vous y trouverez :
  
  * Des tutos de prise en main de Spring
  * Des tutos de prise en main de REST template
  * La façon dont on manipule le JSON depuis JAVA 

## 3. Gestion de projet

  ### 3.1 Compositions de l'équipe

<p> L'équipe projet STOB-GNS3 est coposée de :</p>

* Simon Moisan
* Timothée Schneider-Maunoury
* Oumar Ballo
* Besma Kaouane

<p> L'origine du nom du groupe provient de nos initials..</p>

  ### 3.2 Répartition des rôles

 Voir [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/R%C3%B4les)

  ### 3.3 Pratiques Agiles

  Pour la réalisation de ce projet, nous avons essayé au maximum d'appliquer les principes agiles qui nous ont été enseignés (voir [wiki](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/wiki/Pratiques-agiles) ).
  Nous avons séparé chaque fonctionnalités à developer en users stries (issues Github) que nous avons rassemblé en trois sprints (milestones Github). Vous pouvez les retrouver [ici](https://github.com/PapaTimot/ESIR2-Projet-MDI-GNS3/milestones?state=closed). 
