# examenopdracht_EP2_Java_Spring_22-23
## TIAO/2A_DePaepe_Bart

### Software requirements
- Spring Tool Suite 4
- JDK17
- Apache tomcat 9
- Eclipse Java EE Developer Tools
- Eclipse Enterprise Java and Web Developer Tools
- Lombok
- MySQL


### application.properties content
```
## MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/springbootopdracht?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=create-drop
#spring.jpa.hibernate.ddl-auto=create
#spring.jpa.hibernate.ddl-auto=none

#messages.properties
spring.messages.basename=i18n/messages
```
### localhost:8080

### Log-in demo accounts
- "tania@example.com", "pass"   -> ROLE_USER
- "sandra@example.com", "pass"  -> ROLE_USER
- "jurgen@example.com", "pass"  -> ROLE_USER
- "ann@example.com", "pass"     -> ROLE_ADMIN