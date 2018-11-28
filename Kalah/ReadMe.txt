Kalah Assignement was very interesting request :)

Tools
-------------------------
IDE   	: Netbeans, easily can import the project to eclipse as it's a maven project.
DB    	: H2
Build	: Maven war
FW	: Spring 2.0.6


Allowed Commands
-------------------------
A. Create a game and get a new game ID

Request  1	:	 http://<host>:9090/games
Response 1	: 	{"id":2,"uri":"http://<host>:9090/games/2","message":"New game started with ID: 2"}

B. Check the game status by adding the ID of the game to the URL. This request can be done any time to check the gae status.

Request  2	: 	http://<host>:9090/games/2
Response 2	: 	{"id":2,"boardList":[6,6,6,6,6,6,0,6,6,6,6,6,6,0],"status":"PLAYER1TURN","message":"New game started with ID: "}

C. Apply a move by adding to the URL "{game_id}/pit/{pit required to move}". This request can be done till the end of the game.

Request  3	:  http://<host>:9090/games/2/pit/1
Response 3	:  {"id":2,
				"url":"http://<host>:9090/games/2",
				"message":"Game is currently in progress with ID: 2",
				"status":{"1":0,"2":7,"3":7,"4":7,"5":7,"6":7,"7":1,"8":6,"9":6,"10":6,"11":6,"12":6,"13":6,"14":0}}

D. Future plan
-------------------------
1- Test with Jmockito
2- Profiles & ActiveProfile -OK
3- Microservices
4- Security on Microservices using OAuth2
5- Annotate most cases -OK
6- Expose Rest from Repository -OK