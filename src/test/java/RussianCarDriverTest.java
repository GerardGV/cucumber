import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/RussianCarDriver.feature", glue = "steps")
public class RussianCarDriverTest extends AbstractTestNGCucumberTests{

}
