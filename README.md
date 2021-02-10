Swagger URl
http://localhost:8080/swagger-ui.html#/

Firstly we should create two aiports
Post Method=
localhost:8080/api/airport/add?location=istanbul&name=Sabiha
localhost:8080/api/airport/add?location=İstanbul&name=Sabiha

After this, we should create an airline
POST
localhost:8080/api/airline/add?name=THY

When the process finishes, we should keep the company and airline relation
POST
localhost:8080/api/airlineAirportsRelation/add?airlineCompanyName=THY&airportName=Sabiha

After we create airline and airport relation, we should create a route for a flight.
POST
localhost:8080/api/route/add?departureDate=2012-07-10 14:58:00.000000&landingDate=2013-07-10 14:58:00.000000&routeName=istanbul-ankara&departureAirportName=AGA&landingAirportName=Sabiha

To create a flight, we need a flight code,quota, airline company name, route name, initial price.
When we crate a flight, we also create tickets in amount of given quote name. Seat numbers goes from A1 ....Z6
POST
localhost:8080/api/flight/create?code=THY-3131&quota=20&airlineCompanyName=THY&routeName=istanbul-ankara&initialPrice=100

To buy a flight ticket we will need a customer, we will create the customer with given name and credit card.
*credit card format must be valid, otherwise customer can't register to system. For ex : 4221-1611-2233-0000.
valid delimiters are : '.','-','/' etc.
POST
localhost:8080/api/customer/add?nameSurname=Ahmet Kocabaş&cardNumber=4221-1611-2233-0000

For search Methods :
GET 
localhost:8080/api/airport/search/Sabiha
localhost:8080/api/airline/search/THY
localhost:8080/api/flight/search/THY-3131
localhost:8080/api/route/search/istanbul-ankara

----------
Database URL:http://localhost:8080/h2-console/login.jsp?jsessionid=dd2e15623673a4a0e37d0819c4826459
User name =sa
password:password
Note:For not valid conditions we can change parameters with not valid parameters and we can see exceptions that i created.






