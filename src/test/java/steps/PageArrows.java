package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class PageArrows {
    WebDriver driver;


    @Given("the user is in the main page to user arrow")
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

    @When("the user clicks the right arrow to go to the next web page of games")
    public void clicksNextArrow(){
        driver.findElement(By.cssSelector("a.arrow.next")).click();
    }

    @Then("check if the web page is the next one")
    public void checkingPageChange() throws MalformedURLException {
        String currentUrl = driver.getCurrentUrl();

        // creating URL class
        URL url = new URL(currentUrl);

        // Getting REST parameters from the URL
        String parametersRest = url.getQuery();

        // Imprime los parámetros
        Assert.assertEquals(parametersRest, "page=2");
        driver.quit();
    }

}
