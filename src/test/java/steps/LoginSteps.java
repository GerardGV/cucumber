package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginSteps {

    WebDriver driver;

    @Given("the user is in the index page to login")
    public void userIsInIndexPage(){
        // CHROME
        //System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        //driver = new ChromeDriver();

        //FIREFOX
        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
        driver = new FirefoxDriver();

        driver.navigate().to("https://es.y8.com");
        driver.manage().deleteAllCookies();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-ik5ir8")));
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()){ // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }
    }

    @When("the user clicks Login")
    public void userClickLogin(){
        driver.findElement(By.className("idnet-fast-login-link")).click();
    }

    @When("^the user enters (.*) in the email bar")
    public void userEntersItsEmail(String emailLogin){

        String iframeName = null;
        try {
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            for (WebElement iframe : iframes) {
                if (!Objects.equals(iframe.getAttribute("id"), "easyXDM_id_xd_api_provider")){
                    writer.println(iframe.getAttribute("id"));
                    iframeName = iframe.getAttribute("id"); // we get the iframe name this way cause it changes
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        WebDriverWait wait = new WebDriverWait(driver, 3);
        assert iframeName != null;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(iframeName))); // wait until iframe loads

        driver.switchTo().frame(driver.findElement(By.id(iframeName))); // We switch to the iframe that popped up

        wait = new WebDriverWait(driver, 3); // wait some time if necessary
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("account_email")));

        driver.findElement(By.id("account_email")).sendKeys(emailLogin); // enter email

    }

    @When("^the user enters in the password bar to log in the (.*)")
    public void userEntersItsPassword(String passwordLogin){
        driver.findElement(By.id("account_password")).sendKeys(passwordLogin);
    }

    @When("the user clicks connect")
    public void userClickConnect(){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.className("login")).click();
        driver.switchTo().parentFrame();
    }

    @Then("the user is logged")
    public void userLogged(){
        // El título es el que toca
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            Assert.assertNotNull(driver.findElement(By.id("avatar"))); // Esto lanzará una excepción si el elemento no se encuentra
        } catch (NoSuchElementException e) {
            System.out.println("El elemento no se encontró.");
        }
    }
}
