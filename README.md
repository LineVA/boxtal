#Doctolib "Scrapper"

## Objectifs 
Proposer un démonstrateur permettant de récupérer les rendez-vous disponibles sur Doctolib pour une vaccination d'une personne de plus de 18 ans pour un code postal donnée.

Le choix a été fait de retourner une liste des rendez-vous disponibles classés par ordre chronologique et non par structure.

## Livrables
Un application en Java/SpringBoot à compiler
Des requêtes à exécuter dans Postman (src/test/resources/)

## Architecture
Démonstrateur mis à disposition sous forme d'API.

### Package dédié à Doctolib
Les classes concernant Doctolib (connecteur, service et DTO) sont regroupées dans un package afin de ne pas "polluer" le reste de l'application.
Cela a plusieurs avantages : 
* clarté du code en ne mélangeant pas les objets "de Doctolib" des objets "de l'application"
* si on souhaitait étendre le démonstrateur à d'autres plateformes de rendez-vous, les objets propres à chacune ne sortirait pas de leur package dédié : gain en clarté
* si la structure des objets renvoyés par Doctolib change, seule la partie dédiée à Doctolib serait à impacter


### Bibliothèques utilisées
Lombok, Slf4j, Jsoup (parsing HTML).
Pour les tests : Mockito et AssertJ

## Limitations et améliorations

* Seuls les rendez-vous des centres de vaccination sont retournés : le format de retour de l'appel retournant la liste des rendez-vous pour une structure donnée n'est pas la même selon qu'il s'agit d'une centre de vaccination ou d'un médecin.
Ici, le parsing a été fait sur le modèle des centres de vaccination.
* La recherche n'est faite que sur les structures renvoyées dans la première page de Doctolib (soit 10 structures au maximum).
On pourrait sans trop de soucis récupérer les rendez-vous pour toutes les structures proposées par Docotolib mais cela entraînerait un temps de réponse de l'application assez élevé. En effet, il faut faire un appel à l'APi Doctolib par structure pour récupérer ses rendez-vous.
* Le webservice prends en entrée une chapine de carctères de la forme "codePostal-ville". On pourrait ne prendre qu'un code postal et faire appel à une application tierce pour récupérer la liste des villes correspondantes.
