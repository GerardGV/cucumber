package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class CheckInstagram {

    WebDriver driver;

    @Given("the user is in the main page for Social Media test")
    public void theUserInMainPage(){
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("https://es.y8.com");
        //driver.manage().deleteAllCookies();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-ik5ir8")));
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()) { // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }
    }

    @When("^the user clicks (.*) icon")
    public void userClicksSocialMedia(String socialMedia) throws InterruptedException {

        driver.findElement(By.cssSelector("[aria-label='" +  socialMedia + "'][title='" +  socialMedia + "']")).click();
        sleep(3000);
    }

    @Then("^the title of new web page contains (.*)")
    public void checkingTitle(String socialMedia){
        try {
            //Getting all the windows un the driver
            ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
            while(tabs.size() <= 1){
                tabs = new ArrayList<String> (driver.getWindowHandles());
            }
            String currentTab=driver.getWindowHandle();
            tabs.remove(currentTab);
            driver.switchTo().window(tabs.get(0));

            String text= driver.getTitle();
            Assert.assertTrue(text.contains(socialMedia));
            driver.quit();
        } catch (NoSuchElementException e) {
            System.out.println("El elemento no se encontró.");
            driver.quit();
        }
    }
}
