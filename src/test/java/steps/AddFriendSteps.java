package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AddFriendSteps {
    WebDriver driver;

    @Given("^the user is in the main page logged in with its (.*) and (.*)")
    public void theUserIsInTheMainPageAndLoggedIn(String userEmail, String userPassword){
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

    @When("the user enters in its profile")
    public void theUserEntersInItsProfile() {
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("user-toggle")));
        driver.findElement(By.className("user-toggle")).click();

        wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-menu-link-edit-profile")));
        driver.findElement(By.id("account-menu-link-profile")).click();
    }

    @And("the user selects the first person in its friend suggestions")
    public void theUserSelectsTheFirstPersonInItsFriendSuggestions() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("suggested-friend")));
        driver.findElement(By.className("suggested-friend")).findElement(By.className("avatar")).click();
    }

    @And("the user sends the friend invitation")
    public void theUserSendsTheFriendInvitation() {
        // CHANGE DRIVER WINDOW
        Set<String> allWindows = driver.getWindowHandles();
        allWindows.remove(driver.getWindowHandle());
        String newWindow = allWindows.iterator().next();
        driver.switchTo().window(newWindow);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("add-friend-button")));
        driver.findElement(By.className("add-friend-button")).click();
    }

    @Then("the friend invitation is sent correctly")
    public void theFriendInvitationIsSentCorrectly() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash-notice")));
        String friendRequest = driver.findElement(By.id("flash-notice")).getText();
        Assert.assertEquals(friendRequest,"Friend request sent", "The user did not send the friend request");
    }
}
