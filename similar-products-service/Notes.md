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
