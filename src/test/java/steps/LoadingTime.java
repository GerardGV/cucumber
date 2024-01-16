package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Instant;

import java.util.List;

public class LoadingTime {
    WebDriver driver;
    Instant start;

    @Given("the user is in main page to select to measuring loading time")
    public void userMainPage(){
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("https://es.y8.com/");
        driver.manage().deleteAllCookies();


        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-ik5ir8")));
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()) { // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }

    }

    @When("the user clicks the first game")
    public void clicksFirstGame(){
        //saving list of games
        List<WebElement> games = driver.findElements(By.cssSelector("#items_container li a"));

        //clicking first game
        games.get(0).click();

        start= Instant.now();


    }

    @Then("checking loading time")
    public void checkingLoadingTime(){
        try {

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#item-container")));

            Instant end=Instant.now();
            long milliSecDiff=end.toEpochMilli()-start.toEpochMilli();
            System.out.println(milliSecDiff);
            Assert.assertTrue((milliSecDiff <= 70000));
        } catch (NoSuchElementException e) {
            System.out.println("El elemento no se encontró.");
        }
        driver.quit();
    }

}
