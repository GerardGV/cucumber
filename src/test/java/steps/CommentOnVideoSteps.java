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

public class CommentOnVideoSteps {

    WebDriver driver;

    @Given("^the user is in the video section and logged in with its (.*) and (.*)")
    public void userIsInIndexPage(String userEmail, String userPassword){
        // CHROME
        //System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        //driver = new ChromeDriver();

        //FIREFOX
        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
        driver = new FirefoxDriver();

        driver.navigate().to("https://es.y8.com/anim");
        driver.manage().deleteAllCookies();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-ik5ir8")));
        if (driver.findElement(By.className("css-ik5ir8")).isDisplayed()){ // Cookies popup displayed
            driver.findElement(By.className("css-ik5ir8")).click();
        }

        // LOGIN PROCESS
        driver.findElement(By.className("idnet-fast-login-link")).click();

        String iframeName = null;
        try {
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            for (WebElement iframe : iframes) {
                if (!Objects.equals(iframe.getAttribute("id"), "easyXDM_id_xd_api_provider")){
                    writer.println(iframe.getAttribute("id"));
                    iframeName = iframe.getAttribute("id"); // we get the iframe name this way cause it changes
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
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

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        // Miramos que la sesión esté iniciada
        try {
            wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("avatar")));
            Assert.assertNotNull(driver.findElement(By.className("avatar"))); // Esto lanzará una excepción si el elemento no se encuentra
        } catch (NoSuchElementException e) {
            System.out.println("El elemento no se encontró.");
        }
    }

    @When("^the user searchs random (.*) and clicks on the (.*)")
    public void userClicksOnVideo(String videoName, String videoIdentifier) {
        driver.findElement(By.id("q")).sendKeys(videoName); // Search video by name
        driver.findElement(By.id("items-search-form")).click(); // Click on search
        driver.findElement(By.id(videoIdentifier)).click(); // Select video by identifier
    }

    @When("^the user writes its (.*)")
    public void userCommentWrite(String comment){
        // Window change
        // Hago que el thread actual espere 2 segundos para que cargue la página correctamente
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.switchTo().window(driver.getWindowHandle());

        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("idnet-comment-text")));
        driver.findElement(By.id("idnet-comment-text")).sendKeys(comment);
    }

    @And("publishes the comment")
    public void userPublishesTheComment() {
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-comment-cell-btn")));
        driver.findElement(By.className("post-comment-cell-btn")).click();
    }

    @Then("the comment is sent to revision")
    public void theCommentIsSEntToRevision() {

        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-comment-message-success")));
        try {
            WebElement elemento = driver.findElement(By.id("post-comment-message-success"));
            Assert.assertNotNull(elemento);
        } catch (NoSuchElementException e) {
            Assert.fail("El elemento con el ID 'post-comment-message-success' no se encontró.");
        }
    }
}
