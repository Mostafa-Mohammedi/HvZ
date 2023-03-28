# HvZ 


Human vs Zombie (HvZ) is a full-stack tag game that simulates a zombie infestation through a population. 
The game is built using Java with the Spring framework, along with the Lombok and JPA libraries, and Spring Security for authentication. 
HvZ involves creating games that players can join and play tag in, with options for players to form squads and complete missions. 
The backend provides API endpoints for handling user and game management, including creating, updating, and deleting games, squads, and kills, as well as retrieving information on players and games.

## Setup 

The backend is created with Java with Spring framework.

Library: 

- Lombok
- JPA
- Spring security 

## Classes

- The User class is used to handle users who connect through Keycloak for authentication and authorization.
- The Game class is responsible for creating games in which players can participate in the tag game.
- The Player class represents a player who can participate in various games.
- The Squad class enables players to form groups to play together in the game.
- The SquadMember class represents a player who is a member of a squad.
- The Kill class represents the unique code that zombies need to collect from humans to complete their objectives.
- The Chat class includes separate chat channels for human and zombie players to communicate with each other.
- The Mission class represents the objectives or tasks that players can undertake in the game.



## User Controller

- Get: request Get user by ID api/v1/user{id}: takes the user id as the parameter
- PUT: request update a user by ID  api/v1/ user/{id}: takes the 
- POST: request api/v1/user Add a new user takes the username and bearer token as a parameter
- GET: request api/v1/user/token/{idToken} get user by token takes the user token as a parameter

## Game controller

- GET: request api/v1/games gets all game 
- Get: request api/v1/games/{id}  takes the game by id as a parameter
- PUT: request api/v1/games/{id} updates game by id takes a request body 
- GET: request api/v1/games/{id}/squads Gets all squads in game by game_id 
- GET: requset api/v1/games/{id}/squad takes the  squad id and return all the player in the squad
- PUT: request api/v1/games/{id}/squad takes the squad id as a parameter and a list of player Id 
- Get: request api/v1/games/{id}/kill get all kill in a game by id squad id as a parameter
- PUT: request api/v1/games/{id}/kills updates kills in game by game_id takes the game id as a parameter and a list og kills id
- Get: request api/v1/games get all games return a list of games
- Post: request api/v1/games Adds a new game takes title, description, gametype and maxPLayers 
- Delete: request api/v1/ games/{id}/games takes the game id as a parameter


## The rest se Swagger Documetation

Check out the Swagger documentation for the APIs [here](https://docker-hvz-container.azurewebsites.net/swagger-ui/index.html).


