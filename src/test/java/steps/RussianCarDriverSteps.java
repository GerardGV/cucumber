package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.io.IOException;
import java.io.PrintWriter;

public class RussianCarDriverSteps {

    WebDriver driver;

    @Given("the user is in the index page")
    public void userInIndexPage(){
        // CHROME
        //System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        //driver = new ChromeDriver();

        //FIREFOX
        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
        driver = new FirefoxDriver();

        driver.navigate().to("https://es.y8.com");
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
        try {
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            writer.println(title);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Assert.assertTrue(title.contains("buscar juegos"));
    }
}
