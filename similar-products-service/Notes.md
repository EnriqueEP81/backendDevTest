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
With the initial solution, k6 tests make around 5000 HTTP requests when executed on my laptop  
I'm considering two strategies to improve the performance :
### Asynchronous:  
I create a `findSimilarProductsAsyn`c using ExecutorService with 100 threads. The throughput  increases slightly to 5500 requests
### catching: 
Using Caffeine to cache results for 5 minutes. k6 tests  execute around 12000+ requests. This approach could be combined with the asynchronous method for further improvement.
###

