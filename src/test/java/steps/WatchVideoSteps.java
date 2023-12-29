package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Set;

public class WatchVideoSteps {

    WebDriver driver;

    @Given("the user is in the main page")
    public void userInMainPage(){
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

    @When("the user clicks in the video section")
    public void clickVideoSection(){
        driver.findElement(By.className("games-videos-switch")).click();
    }

    @When("^the user search a (.*) in specific")
    public void userSerchVideoName(String video){
        driver.findElement(By.id("q")).sendKeys(video);
    }

    @When("the user clicks on the search button")
    public void userSearchTheVideo(){
        driver.findElement(By.id("items-search-form")).click();
    }

    @When("^the user selects the video with its (.*)")
    public void videoSelection(String identification){
        driver.findElement(By.id(identification)).click();
    }

    @When("the user plays the video")
    public void playTheVideo(){


        // Window change
        // Hago que el thread actual espere 2 segundos para que cargue la página correctamente
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().window(driver.getWindowHandle());

        // Play video
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("item-container")));
        driver.findElement(By.className("item-container")).click();
    }

    @Then("video is playing")
    public void confirmationVideoPlaying(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("is-playing")));
        String classes = driver.findElement(By.id("item-direct-container")).getAttribute("class");
        boolean isPlaying = classes.contains("is-playing");

        // Utiliza Assert para verificar si el video está en pausa
        Assert.assertTrue(isPlaying, "El video no se está reproduciendo");

        driver.quit();
    }
}
