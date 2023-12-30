import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/AddFriend.feature", glue = "steps")
public class AddFriendTest extends AbstractTestNGCucumberTests {
}
