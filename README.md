# devoirJEE

Devoir JEE (Julien Pezant - 21304666)

app2 est le projet utilisant Spring et qui renvoie via api rest des donn�es json.
appjsf est le projet impl�mentant jsf
personnes permet la repr�sentations des personnes et des relations.
sqlpersons g�re les interractions avec la base de donn�es (avec hibernate)

Un fichier sql contenant de quoi d�ployer les tables n�cessaires au bon fonctionnement des applications se trouve � la racine du dossier.
Il sera par ailleurs n�cessaire d'�diter le contenu de hibernate.cfg.xml (dans sqlpersons/src/resources/) pour que les communications
puissent se faire avec une base de donn�e personnelle.
