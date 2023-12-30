package steps;

import io.cucumber.java.en.And;
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

public class AddGameToFavouriteSteps {

    WebDriver driver;

    @Given("^the user is logged with its (.*) and (.*) in and in the main page")
    public void userLoggedAndInMainPage(String userEmail, String userPassword){
        // CHROME
        //System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        //driver = new ChromeDriver();

        //FIREFOX
        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
        driver = new FirefoxDriver();

        driver.navigate().to("https://es.y8.com");
        driver.manage().deleteAllCookies();

        // COOKIES
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-ik5ir8")));
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()){ // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }

        // LOGIN PROCESS
        driver.findElement(By.className("idnet-fast-login-link")).click();

        String iframeName = null;

        final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {
            if (!Objects.equals(iframe.getAttribute("id"), "easyXDM_id_xd_api_provider")){
                iframeName = iframe.getAttribute("id"); // we get the iframe name this way cause it changes
            }
        }


        wait = new WebDriverWait(driver, 3);
        assert iframeName != null;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(iframeName))); // wait until iframe loads

        driver.switchTo().frame(driver.findElement(By.id(iframeName))); // We switch to the iframe that popped up

        wait = new WebDriverWait(driver, 3); // wait some time if necessary
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("account_email")));

        driver.findElement(By.id("account_email")).sendKeys(userEmail); // Enter email

        driver.findElement(By.id("account_password")).sendKeys(userPassword); // Enter password

        wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("login"))));
        driver.findElement(By.className("login")).click();
        driver.switchTo().parentFrame();

        driver.navigate().refresh();
    }

    @When("^the user searches a (.*), selects its (.*), searches it")
    public void theUserSearchesAGameSelectsItsGameIdentifierSearchesIt(String game, String gameIdentifier) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("q")));
        driver.findElement(By.id("q")).sendKeys(game);
        driver.findElement(By.className("y-icon--search")).click();
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(gameIdentifier)));
        driver.findElement(By.id(gameIdentifier)).click();
    }

    @And("the user clicks to add the game to favourites")
    public void theUserClicksToAddTheGameToFavourites() {
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("favorites_add")));
        driver.findElement(By.className("favorites_add")).click();
    }

    @And("the user checks its favourites in his profile")
    public void theUserChecksItsFavouritesInHisProfile() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("user-toggle")));
        driver.findElement(By.className("user-toggle")).click();

        wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-menu-link-edit-profile")));
        driver.findElement(By.id("account-menu-link-profile")).click();
    }

    @Then("the user has the game in favourites and deletes it from there")
    public void theUserHasTheGameInFavouritesAndDeletesItFromThere() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("js-profile-favorites-count")));

        WebElement spanElement = driver.findElement(By.className("js-profile-favorites-count"));
        int number = Integer.parseInt(spanElement.getText());

        Assert.assertTrue(number > 0, "Game was not added to favourites");

        wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("favorites_remove")));
        driver.findElement(By.className("favorites_remove")).click();

        try {
            Thread.sleep(1000); // Espera 1000 milisegundos, que equivalen a 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement pElement = driver.findElement(By.xpath("//p[contains(text(), 'Todavía no hay ítems en tus favoritos')]"));
        Assert.assertEquals(pElement.getText(), "Todavía no hay ítems en tus favoritos");

    }
}
