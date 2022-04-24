package uk.ac.cf.nsa.team2.deskbookingapp.unitTests;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;


@Disabled()
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


public class webDriverTests {


    private WebDriver webDriver;

    @Value("${local.server.port}")
    private int port;

    @Test
   public void testingPageContents() {
       System.setProperty("webdriver.chrome.driver", "C:\\Users\\c21091401\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-debugging-port=42227");
        options.addArguments("--headless");
        this.webDriver = new ChromeDriver(options);


        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/Home");
        assertTrue(webDriver.findElement(By.id("Test")).getText().contains("Testing"));
        this.webDriver.get("http://localhost:" + Integer.toString(port) + "/admin/rooms/all");
        this.webDriver.findElement(By.name("username")).sendKeys("admin");
        this.webDriver.findElement(By.name("password")).sendKeys("admin");
        this.webDriver.findElement(By.tagName("button")).click();
        assertTrue(webDriver.findElement(By.cssSelector("body > main > div > h1")).getText().contains("Rooms"));
        webDriver.quit();
    }

}
