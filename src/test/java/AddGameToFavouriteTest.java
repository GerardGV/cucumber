import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/AddGameToFavourite.feature", glue = "steps")
public class AddGameToFavouriteTest extends AbstractTestNGCucumberTests {
}
