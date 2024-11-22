/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package selenium;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author npvlo
 */
public class PractiseTest {

    WebDriver driver;
    final String URL = "https://practicetestautomation.com/practice-test-login/";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void loginSuccess() {
        driver.get(URL);
         driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // set username & password
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("student");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Password123");
        driver.findElement(By.xpath("//button[@id='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        String currentURL = driver.getCurrentUrl();
        Assertions.assertTrue(currentURL.contains("practicetestautomation.com/logged-in-successfully/"), "URL does not contain the expected text");
        WebElement bodyText = driver.findElement(By.tagName("body"));
        String pageText = bodyText.getText();
        
        Assertions.assertTrue((pageText.contains("Congratulations") || pageText.contains("successfully logged in")), "Text not found!");
        WebElement logoutBtn = driver.findElement(By.xpath("//a[normalize-space()='Log out']"));
        Assertions.assertTrue(logoutBtn.isDisplayed(), "Logout button not found");
    }
    
    @Test
    public void loginFailByUsername() {
        driver.get(URL);
         driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        // set username & password
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("student");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Password123");
        driver.findElement(By.xpath("//button[@id='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(currentUrl.equals(URL), "URL not match");
        WebElement divError = driver.findElement(By.xpath("//div[@id='error']"));
        Assertions.assertTrue(divError.isDisplayed(), "Error div not found");
        Assertions.assertTrue(divError.getText().equals("Your username is invalid!"), "Error message not match");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
