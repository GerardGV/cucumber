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

public class GoingHomeWithIcon {
    WebDriver driver;

    @Given("the user starts in page 3")
    public void startsPage3(){
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("https://es.y8.com/?page=3");
        driver.manage().deleteAllCookies();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-ik5ir8")));
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()) { // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }
    }

    @When("the user clicks icon of Y8")
    public void clicksIconY8(){
        driver.findElement(By.cssSelector("a.no-event[aria-label='Juegos en Línea Gratis Y8'")).click();
    }

    @Then("check if the web page is the home page")
    public void checkingHomePage(){
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://es.y8.com/");
    }

}
