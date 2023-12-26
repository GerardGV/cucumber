import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/manHoodie.feature", glue = "steps")
public class RunManHoodieTest extends AbstractTestNGCucumberTests{

}
