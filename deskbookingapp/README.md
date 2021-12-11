# Testing

## Testing with a test database
In the test/resources directory, there is a test database script named `database_test.sql`.
You can use this script to add tables for your tests and have Spring execute the script
before tests.

1. Create an application.properties file in the test/resources folder:
    ```properties
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/deskbookingtest
    spring.datasource.username=your username
    spring.datasource.password=your password
    ```
2. Create your Java test file.
3. If you want Spring to execute the SQL script before every test, add the `@Sql` annotation at the class level.
    ```java
   @SpringBootTest
   @Test("/database_test.sql")
   public class MyTestClass {
   }
    ```
4. If you want Spring to execute the SQL script once before running all tests, i.e. you'd like to maintain the state
of the database between each test, then create a method with the `@BeforeAll` and `@Sql` annotations instead.
   ```java
   @BeforeAll
   @Test("/database_test.sql")
   public static void initialiseOnce() {
   }
   ```
