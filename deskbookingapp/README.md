## About application.yml and application.properties

if you want to run this project, please add application.yml or application.properties on src/resource. Besides, you should import database.sql.

``` application.yml
spring:
  datasource:
    username: root
    password: comsc
    url: jdbc:mysql://localhost:3306/deskbooking?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    driver-class-name: com.mysql.cj.jdbc.Driver
  servlet:
    multipart:
      enabled: true
      max-file-size: 200MB
      max-request-size: 215MB
      file-size-threshold: 2KB
  main:
    allow-bean-definition-overriding: true
  thymeleaf:
    cache: false
```

``` application.properties
spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=200MB
spring.servlet.multipart.max-request-size=215MB
spring.servlet.multipart.file-size-threshold=2KB
spring.main.allow-bean-definition-overriding=true
spring.datasource.url=jdbc:mysql://localhost:3306/deskbooking?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=comsc
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.thymeleaf.cache=false
```