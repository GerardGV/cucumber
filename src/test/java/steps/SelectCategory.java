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

public class SelectCategory {
    WebDriver driver;

    @Given("the user is in the main page to select a game category")
    public void userInMainPage(){
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

    @When("the user clicks in all the categories")
    public void clicksAllCategories(){
        driver.findElement(By.className("all-categories-btn")).click();

    }

    @When("^select (.*)")
    public void selectCategory(String category){
        //%1$s indicates 1 element as String in format function
        String cssSelector = String.format("a[aria-label='%1$s'][title='%1$s']", category);
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[aria-label='Juegos de Deportes'][title='Juegos de Deportes']")));
            driver.findElement(By.cssSelector(cssSelector)).click();

        } catch (NoSuchElementException e) {
            System.out.println("El elemento no se encontró.");
        }

    }

    @Then("^checking (.*) title of the web page")
    public void checkingTitle(String category){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.fake-button.idnet-fast-login-link")));
            String text= driver.findElement(By.cssSelector("h1.title.header-5")).getText();
            Assert.assertEquals(text, category);

        } catch (NoSuchElementException e) {
            System.out.println("El elemento no se encontró.");
        }
        driver.quit();
    }

}
