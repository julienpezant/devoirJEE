# devoirJEE

Devoir JEE (Julien Pezant - 21304666)

app2 est le projet utilisant Spring et qui renvoie via api rest des données json.
appjsf est le projet implémentant jsf
personnes permet la représentations des personnes et des relations.
sqlpersons gère les interractions avec la base de données (avec hibernate)

Un fichier sql contenant de quoi déployer les tables nécessaires au bon fonctionnement des applications se trouve à la racine du dossier.
Il sera par ailleurs nécessaire d'éditer le contenu de hibernate.cfg.xml (dans sqlpersons/src/resources/) pour que les communications
puissent se faire avec une base de donnée personnelle.
