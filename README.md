## Mattia Buffa (190936) | mattia.buffa-1@studenti.unitn.it 
I worked with Denis Gallo.  

## GameServer

This project interface with RecombeeGame using SOAP and Steam api using REST in order to adapt games information from steam to RecombeeGame.

### Description of the project

It uses SOAP and Steam REST api to communicate.
This project let search and retrieve games information on steam database.
Using Recombee api retrieves item suggestion based on user preferences.
Steam uses only a gameId system to find games so it is really usefull to adapt information since it knows how to find them.
It is used by RecombeeGame to fill missing game information.
For more information about available methods check wsdl.

## Execution
It is deployed on heroku and the wsdl can be found at https://games-sde.herokuapp.com/games?wsdl