package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class RussianCarDriverSteps {

    WebDriver driver;

    @Given("the user is in the index page to search a game")
    public void userInIndexPage(){
        // CHROME
        //System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        //driver = new ChromeDriver();

        //FIREFOX
        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
        driver = new FirefoxDriver();

        driver.navigate().to("https://es.y8.com");
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()){ // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }
    }

    @When("the user clicks conducir options")
    public void userClickManOptions(){
        driver.findElement(By.partialLinkText("Conducir")).click();
    }

    @When("^the user enters (.*) in the search bar")
    public void userEntersHoodie(String article){
        driver.findElement(By.id("q")).sendKeys(article);

    }
    @When("the user clicks the search button")
    public void userClicksSearch(){
        driver.findElement(By.className("y-icon--search")).click();
    }

    @Then("^the (.*) list appears")
    public void hoodieListAppears(String article){
        // El título es el que toca
        String title = driver.getTitle();

        Assert.assertTrue(title.contains("buscar juegos"));
    }
}
