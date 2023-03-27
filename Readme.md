# HvZ 


Human vs Zombie is a fullstack tag game. The game is a simulation of zombie infactuinn trough a population.


## Setup 

The backend is created with Java with Spring framework.

Library: 

- Lombok
- JPA
- Spring security 

## Classes

* User is in for handling user that connect trough keykloack 
* Game is in charge of creating that the player can play the Tag game in 
* Player is on of creating player that can play in the different games
* Squad Lets player play in a group 
* SquadMember is a player in the squad
* Kill represent the bit code that the zombies requires from the human
* Chat are divided into human and zombies chat that lets the different player chat with each other
* Mission represent mission that we can take in the game



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

## Player controller
- Get: request api/v1/players/{id} Gets player by ID takes the player id as a parameter
- Post: request api/v1/players takes a body request with the user token as a id, bitecode and game id 
- Put: request api/v1/plauers/{id} updated a player by id as a parameter and requestbody of the squad id, and player id.

## The rest se Swagger 


