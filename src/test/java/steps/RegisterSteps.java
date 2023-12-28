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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class RegisterSteps {

    WebDriver driver;

    @Given("the user is in the index page to register")
    public void userInIndexPage(){
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

    @When("the user clicks Register")
    public void userClickRegister(){
        driver.findElement(By.className("idnet-fast-register-link")).click();
    }

    @When("^the user enters (.*) in the username bar")
    public void userEntersUsername(String username){

        String iframeName = null;
        try {
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            for (WebElement iframe : iframes) {
                if (!Objects.equals(iframe.getAttribute("id"), "easyXDM_id_xd_api_provider")){
                    writer.println(iframe.getAttribute("id"));
                    iframeName = iframe.getAttribute("id");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        WebDriverWait wait = new WebDriverWait(driver, 3);
        assert iframeName != null;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(iframeName)));

        driver.switchTo().frame(driver.findElement(By.id(iframeName)));

        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identity_nickname")));
        driver.findElement(By.id("identity_nickname")).sendKeys(username);
    }
    @When("^the user enters (.*) in the name button")
    public void userEntersName(String name){
        driver.findElement(By.id("meta_first_name")).sendKeys(name);
    }

    @When("the user clicks next 1")
    public void userClickNext(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("next")).findElement(By.className("norm"))));
        driver.findElement(By.className("next")).click();

    }

    @When("^the user enters in the email bar his (.*)")
    public void userEntersTemporalEmail(String emailRegister){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meta_email")));
        driver.findElement(By.id("meta_email")).sendKeys(emailRegister);
    }

    @When("^the user enters the (.*)")
    public void userEntersPassword(String passwordRegister){
        driver.findElement(By.id("meta_password")).sendKeys(passwordRegister);
    }

    @When("the user clicks next 2")
    public void userClickNext2(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("next")).findElement(By.className("norm"))));
        driver.findElement(By.className("next")).click();
    }

    @When("the user chooses boy or girl")
    public void userClickBoyOrGirl(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("male-icon")));
        driver.findElement(By.className("male-icon")).click();
    }

    @When("the user clicks next 3")
    public void userClickNext3(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("next")).findElement(By.className("norm"))));
        driver.findElement(By.className("next")).click();
    }

    @When("the user chooses year")
    public void userSelectsYear(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("meta_date_of_birth_1i")));
        WebElement dropdownElement = driver.findElement(By.id("meta_date_of_birth_1i"));
        Select dropdown = new Select(dropdownElement);

        // Seleccionamos un año en particular
        dropdown.selectByValue("1996");
    }

    @When("the user chooses month")
    public void userSelectsMonth(){
        WebElement dropdownElement = driver.findElement(By.id("meta_date_of_birth_2i"));
        Select dropdown = new Select(dropdownElement);

        // Seleccionamos un mes en particular
        dropdown.selectByValue("1"); // Enero
    }

    @When("the user clicks finish")
    public void userClickFinish(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("next")).findElement(By.className("finish"))));
        driver.findElement(By.className("finish")).click();
        driver.switchTo().parentFrame();
    }

    @Then("the user is registered")
    public void userRegistered(){
        // El título es el que toca
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("avatar")));
            Assert.assertNotNull(driver.findElement(By.className("avatar"))); // Esto lanzará una excepción si el elemento no se encuentra
        } catch (NoSuchElementException e) {
            System.out.println("El elemento no se encontró.");
        }
    }
}
