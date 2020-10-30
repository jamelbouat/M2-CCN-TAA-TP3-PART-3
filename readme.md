Projet : TAA - TP3 - Partie - 3 :
- Utilisation de Spring data pour les classes métiers du projet Kanban, utilisation de d'injection propre à Spring.
- Utilisation d'un aspect pour logger tous les acces au service Rest.

Lancement du projet:
- Configuration base de donnée : dans le fichier application.properties, 
  nom de base de données = TP3_TAA_PART3_database | port = 3300 | nom utilisateur = jamel | mot de passe = oooo
- Execution de  src/main/java/sample/data/jpa/SampleDataJpaApplication.java

Exemple d'application : 
- http://localhost:8080/kanban | POST
	{
		"name": "kanban-1"
	}

	response :
	{
	 	"id": 1,
		"name": "kanban",
		"sections":[]
	}

- http://localhost:8080/section/1 | GET
	response :
	{
	 	"id": 1,
		"name": "kanban",
		"sections":[]
	}
	
- http://localhost:8080/kanban | PUT	
	{
		"id" : 1,
  		"name": "kanban-01" 
	}
	
	
- http://localhost:8080/section/1 | GET
	
	response :
	{
	 	"id": 1,
		"name": "kanban-01",
		"sections":[]
	}
