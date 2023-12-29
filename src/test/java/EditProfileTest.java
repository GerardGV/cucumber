import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/EditProfile.feature", glue = "steps")
public class EditProfileTest extends AbstractTestNGCucumberTests{
}
