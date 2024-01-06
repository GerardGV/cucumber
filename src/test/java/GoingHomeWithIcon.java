import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(features = "src/test/java/features/GoingHomeWithIcon.feature", glue = "steps")
public class GoingHomeWithIcon extends AbstractTestNGCucumberTests{
}
