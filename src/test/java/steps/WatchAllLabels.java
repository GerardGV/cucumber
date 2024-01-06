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

public class WatchAllLabels {
    WebDriver driver;

    @Given("the user is in main page to select to watch all the categories")
    public void userMainPage(){
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("https://es.y8.com/");
        driver.manage().deleteAllCookies();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-ik5ir8")));
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()) { // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }
    }

    @When("the user clicks in all the labels")
    public void clicksAllLabelsButton(){

        driver.findElement(By.cssSelector("a.tag.all-tags.top")).click();
    }

    @Then("checking if appears the header with the number of labels,categories")
    public void checkingHeaderNewPage(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.header-7")));
            String header=driver.findElement(By.cssSelector("h3.header-7")).getText();
            Assert.assertEquals(header,"Todas las 232 etiquetas para encontrar el juego gratis de tus sueños");
        } catch (NoSuchElementException e) {
            System.out.println("El elemento no se encontró.");
        }
        driver.quit();
    }


}
