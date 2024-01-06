import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/PageArrows.feature", glue = "steps")
public class PageArrows extends AbstractTestNGCucumberTests{
}
