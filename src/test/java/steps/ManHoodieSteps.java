package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ManHoodieSteps {

    WebDriver driver;

    @Given("the user is in the index page")
    public void userInIndexPage(){
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www2.hm.com/es_es/index.html");
    }
    @When("the user clicks man options")
    public void userClickManOptions(){
        driver.findElement(By.partialLinkText("es_es/hombre")).click();
    }
    @When("the user enters hoodie in the search bar")
    public void userEntersHoodie(){
        //driver.findElement(By.className("//input[contains(@class, 'BaseInput-module--input__1yGxv') and contains(@class, 'Input-module--inputIcon__5pVpE')]"))
        System.out.println();
        driver.findElement(By.xpath("//input[contains(@class, 'BaseInput-module--input__1yGxv') and contains(@class, 'Input-module--inputIcon__5pVpE')]")).sendKeys("hoodie");

    }
    @When("the user clicks the search button")
    public void userClicksSearch(){
        driver.findElement(By.xpath("//input[contains(@class, 'CTA-module--action__1qN9s CTA-module--medium__1uoRl') and contains(@class, 'Input-module--icon__oV22P')]")).click();
    }

    @Then("the hoodie list appears")
    public void hoodieListAppears(){
        String title = driver.findElement(By.className("heading")).getText();
        System.out.println(title);
        Assert.assertTrue(title.contains("MOSTRAR RESULTADOS DE \"hoodie\""));
    }
}
