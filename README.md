# spring-data-mongodb-dynamic-queries

Advanced Search and Filtering API Using Spring Data and MongoDB

## Prerequisites
Ensure you have this installed before proceeding further

* Spring Boot 3.3.2
* Lombok
* Java 17
* MongoDB 

## Post link

https://dzone.com/articles/advanced-search-amp-filtering-api-using-spring-dat


Endpoint demo
```
GET http://localhost:8080/employee/page?page=1&size=5
GET http://localhost:8080/employee/page?page=1&size=5&orders=firstName;ASC
GET http://localhost:8080/employee/page?page=1&size=5&and=firstName;like;C&orders=firstName;DESC

GET http://localhost:8080/api/employee/search?page=1&size=10&or=firstName;startwith;K,firstName;like;ir
GET http://localhost:8080/api/employee/search?page=1&size=10&or=firstName;startwith;K,firstName;like;on
```