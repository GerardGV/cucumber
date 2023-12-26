package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        WebDriver driver;

        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.navigate().to("https://www2.hm.com/es_es/index.html");


        driver.findElement(By.partialLinkText("es_es/hombre")).click();


        System.out.println("Hola");
        //driver.findElement(By.xpath("//input[contains(@class, 'BaseInput-module--input__1yGxv') and contains(@class, 'Input-module--inputIcon__5pVpE')]")).sendKeys("hoodie");

       // driver.findElement(By.xpath("//input[contains(@class, 'CTA-module--action__1qN9s CTA-module--medium__1uoRl') and contains(@class, 'Input-module--icon__oV22P')]")).click();


        //String title = driver.findElement(By.className("heading")).getText();
       // System.out.println(title);

    }
}