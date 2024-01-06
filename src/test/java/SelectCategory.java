import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/SelectCategory.feature", glue = "steps")
public class SelectCategory extends AbstractTestNGCucumberTests {


}
