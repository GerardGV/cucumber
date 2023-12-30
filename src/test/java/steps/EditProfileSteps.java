package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class EditProfileSteps {

    WebDriver driver;

    @Given("^the user is logged with its (.*) and (.*) in and in the index page")
    public void userIsInIndexPage(String emailLogin, String passwordLogin){
        // CHROME
        //System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");
        //driver = new ChromeDriver();

        //FIREFOX
        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
        driver = new FirefoxDriver();

        driver.navigate().to("https://es.y8.com");
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

        driver.findElement(By.id("account_email")).sendKeys(emailLogin); // Enter email

        driver.findElement(By.id("account_password")).sendKeys(passwordLogin); // Enter password

        wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("login"))));
        driver.findElement(By.className("login")).click();
        driver.switchTo().parentFrame();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("avatar")));
            Assert.assertNotNull(driver.findElement(By.className("avatar"))); // Esto lanzará una excepción si el elemento no se encuentra
        } catch (NoSuchElementException e) {
            System.out.println("El elemento no se encontró.");
        }
    }

    @When("the user clicks in its profile icon")
    public void userClicksProfileIcon(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("user-toggle")));
        driver.findElement(By.className("user-toggle")).click();
    }

    @When("the user clicks in the edit profile option")
    public void userClicksEditProfile(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-menu-link-edit-profile")));
        driver.findElement(By.id("account-menu-link-edit-profile")).click();
    }

    @When("^the user changes its (.*), (.*), (.*), (.*), (.*) and (.*)")
    public void userClicksEditProfile(String identity, String username, String name, String gender, String birthDateMonthNumber, String language){

        // CHANGE DRIVER WINDOW
        Set<String> allWindows = driver.getWindowHandles();
        allWindows.remove(driver.getWindowHandle());
        String newWindow = allWindows.iterator().next();
        driver.switchTo().window(newWindow);

        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identity_form")));

        // IDENTITY
        driver.findElement(By.id("identity_identity_title")).clear();
        driver.findElement(By.id("identity_identity_title")).sendKeys(identity);

        // USERNAME
        driver.findElement(By.id("identity_nickname")).clear();
        driver.findElement(By.id("identity_nickname")).sendKeys(username);

        // NAME
        driver.findElement(By.id("identity_first_name")).clear();
        driver.findElement(By.id("identity_first_name")).sendKeys(name);

        // GENDER
        if (Objects.equals(gender, "female")){
            driver.findElement(By.id("identity_gender_female")).click();
        } else if (Objects.equals(gender, "male")) {
            driver.findElement(By.id("identity_gender_male")).click();
        } else{
            Assert.fail("Invalid gender value: " + gender);
        }

        // BIRTH MONTH
        WebElement dropdownElement = driver.findElement(By.id("identity_date_of_birth_2i"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(birthDateMonthNumber);

        // LANGUAGE
        dropdownElement = driver.findElement(By.id("identity_language"));
        dropdown = new Select(dropdownElement);
        dropdown.selectByValue(language);
    }

    @When("the user updates the identity values")
    public void userClicksUpdateIDentity(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("commit")));
        driver.findElement(By.name("commit")).click();
    }

    @Then("the identity is updated")
    public void userIdentityUpdated(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash_notice")));
        String updated = driver.findElement(By.id("flash_notice")).getText();
        Assert.assertTrue(updated.contains("La identidad se ha actualizado con éxito."));
    }

}
