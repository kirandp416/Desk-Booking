# Testing

## Configuration for running tests

Before running tests, remember to create an application.properties file in `test/resources` to ensure your main database
is not written with test data. This may cause unexpected behaviour and cause tests to fail.

```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/deskbookingtest?createDatabaseIfNotExist=true
spring.datasource.username=your username
spring.datasource.password=your password
```

## Creating tests with a database

In the `test/resources` directory, there is a test database script named `schema.sql`. You can use this script to
add tables for your tests and have Spring execute the script before tests.

1. Create an application.properties file in the test/resources folder:
    ```properties
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/deskbookingtest?createDatabaseIfNotExist=true
    spring.datasource.username=your username
    spring.datasource.password=your password
    ```
2. Create your Java test file.
3. Add the `@Sql` annotation at the class level. The database script is executed before each test.
    ```java
   @SpringBootTest
   @Sql("/schema.sql")
   public class MyTestClass {
   }
   ```
