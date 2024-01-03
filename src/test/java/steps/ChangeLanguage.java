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

import static java.lang.Thread.sleep;

public class ChangeLanguage {

    WebDriver driver;

    @Given("the user starts the main page")
    public void userInTheMainPage() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("https://es.y8.com");
        driver.manage().deleteAllCookies();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-ik5ir8")));
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()) { // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }
    }

    @When("the user clicks in the spanish flag")
    public void userClicksLanguageOptions(){

        driver.findElement(By.id("locale-selector-dropdown")).click();
    }

    @When("^the user clicks a new (.*) option")
    public void userClicksNewLanguage(String newLanguage){
        driver.findElement(By.cssSelector("a.en.locale-link[aria-label='English']")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-ik5ir8")));
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()) { // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }
    }

    @Then("Text Iniciar sesion has change to Log In")
    public void checkLogInText(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.fake-button.idnet-fast-login-link")));
            String text= driver.findElement(By.cssSelector("button.fake-button.idnet-fast-login-link")).getText();
            Assert.assertEquals(text, "Log in");

        } catch (NoSuchElementException e) {
            System.out.println("El elemento no se encontró.");
        }
        driver.quit();
    }

}
