import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/CheckInstagram.feature", glue = "steps")
public class CheckInstagram extends AbstractTestNGCucumberTests{
}
