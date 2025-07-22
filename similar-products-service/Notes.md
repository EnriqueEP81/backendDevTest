## Notes
Spring boot rest service using OpenAPI(Swagger) and lombok.
Swagger url: 
* http://localhost:5000/swagger-ui.html  

Added dockerized service in the existing docker-compose.yml file
```
docker-compose up -d simulado influxdb grafana similar-productservice
```
To execute the test run:
```
docker-compose run --rm k6 run scripts/test.js
```
With the initial solution, k6 tests make around 5000 http requests when executed on my laptop  
I'm considering two strategies to improve the performance :
### catching: 
Using Caffeine to cache results for 5 minutes. k6 tests  execute around 12000+ requests

