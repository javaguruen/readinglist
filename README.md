https://codeday.me/en/qa/20190306/13468.html

http://frugalisminds.com/spring-boot/configure-swagger-with-jersey-and-spring-boot/
https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Getting-started
https://github.com/brightzheng100/springboot-jersey-swagger/blob/master/src/main/java/bright/zheng/poc/api/config/JerseyConfig.java
https://github.com/swagger-api/swagger-ui
https://github.com/brightzheng100/springboot-jersey-swagger





https://reflectoring.io/spring-boot-data-jpa-test/


Redoc
http://localhost:8080/redoc.html

openapi.json:
http://localhost:8080/v3/api-docs



## Run locally
### Maven with profile:
`mvn spring-boot:run -Dspring-boot.run.profiles=dev`

### Java -jar
` java  -jar target/readinglist-0.0.1-SNAPSHOT.jar --spring.profiles.active=production`


### Schenarier
* Kopiere ren tekst eller lenke og sende inn
    `POST /books/raw`
* Sende inn bok-objekt
    `POST /books`
   1. Hvis forfatter ikke finnes, opprett
   1. Hvis bok ikke finnes, opprett
   1. Hvis tags ikke finnes, opprett tag
* Opprett forfatter
* Slå sammen duplikate forfattere
* Slå sammen duplikate bøker
* Endre bok (ikke tagger)
    `PUT /books`
* Legg til tagger
* Ta vekk tagger

    