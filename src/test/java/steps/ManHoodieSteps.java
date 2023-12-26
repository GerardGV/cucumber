package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class ManHoodieSteps {

    WebDriver driver;

    @Given("the user is in the index page")
    public void userInIndexPage(){
        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.navigate().to("https://es.y8.com");
    }

    @When("the user clicks man options")
    public void userClickManOptions(){
        driver.findElement(By.partialLinkText("Conducir")).click();
    }

    @When("the user enters hoodie in the search bar")
    public void userEntersHoodie(){
        driver.findElement(By.id("q")).sendKeys("Russian car driver hd");

    }
    @When("the user clicks the search button")
    public void userClicksSearch(){
        driver.findElement(By.className("y-icon--search")).click();
    }

    @Then("the hoodie list appears")
    public void hoodieListAppears(){
        //String title = driver.findElement(By.className("heading")).getText();
        //System.out.println(title);
        //Assert.assertTrue(title.contains("MOSTRAR RESULTADOS DE \"hoodie\""));
    }
}
