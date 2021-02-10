Swagger URl
http://localhost:8080/swagger-ui.html#/

Firstly we should create two aiports <br/>
Post Method=<br/>
**localhost:8080/api/airport/add?location=istanbul&name=Sabiha**<br/>
**localhost:8080/api/airport/add?location=İstanbul&name=Sabiha**

After this, we should create an airline<br/>
POST<br/>
**localhost:8080/api/airline/add?name=THY**<br/>

When the process finishes, we should keep the company and airline relation<br/>
POST<br/>
**localhost:8080/api/airlineAirportsRelation/add?airlineCompanyName=THY&airportName=Sabiha**<br/>

After we create airline and airport relation, we should create a route for a flight.<br/>
POST<br/>
**localhost:8080/api/route/add?departureDate=2012-07-10 14:58:00.000000&landingDate=2013-07-10 14:58:00.000000&routeName=istanbul-ankara&departureAirportName=AGA&landingAirportName=Sabiha**<br/>

To create a flight, we need a flight code,quota, airline company name, route name, initial price.<br/>
When we crate a flight, we also create tickets in amount of given quote name. Seat numbers goes from A1 ....Z6<br/>
POST<br/>
**localhost:8080/api/flight/create?code=THY-3131&quota=20&airlineCompanyName=THY&routeName=istanbul-ankara&initialPrice=100**<br/>

To buy a flight ticket we will need a customer, we will create the customer with given name and credit card.
*credit card format must be valid, otherwise customer can't register to system. For ex : 4221-1611-2233-0000.
valid delimiters are : '.','-','/' etc.<br/>
POST<br/>
**localhost:8080/api/customer/add?nameSurname=Ahmet Kocabaş&cardNumber=4221-1611-2233-0000**<br/>

For search Methods :<br/>
GET <br/>
**localhost:8080/api/airport/search/Sabiha**<br/>
**localhost:8080/api/airline/search/THY**<br/>
**localhost:8080/api/flight/search/THY-3131**<br/>
**localhost:8080/api/route/search/istanbul-ankara**<br/>

----------<br/>
Database URL**:http://localhost:8080/h2-console/login.jsp?jsessionid=dd2e15623673a4a0e37d0819c4826459**<br/>
User name =sa<br/>
password:password<br/>
Note:For not valid conditions we can change parameters with not valid parameters and we can see exceptions that i created.<br/>






