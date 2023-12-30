package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class AddGameToFavouriteSteps {

    WebDriver driver;

    @Given("^the user is logged with its (.*) and (.*) in and in the main page")
    public void userLoggedAndInMainPage(String userEmail, String userPassword){

    }

    @When("the user searches a (.*), selects its (.*), searches it")
    public void theUserSearchesAGameSelectsItsGameIdentifierSearchesIt() {
        
    }

    @And("the user clicks to add the game to favourites")
    public void theUserClicksToAddTheGameToFavourites() {

    }

    @And("the user checks its favourites in his profile")
    public void theUserChecksItsFavouritesInHisProfile() {

    }

    @Then("the user has the game in favourites and deletes it from there")
    public void theUserHasTheGameInFavouritesAndDeletesItFromThere() {
    }
}
