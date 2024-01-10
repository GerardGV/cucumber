import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/LoadingTime.feature", glue = "steps")
public class LoadingTime extends AbstractTestNGCucumberTests{
}
