package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        WebDriver driver;

        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("https://es.y8.com");
        driver.findElement(By.partialLinkText("Conducir")).click();
    }
}