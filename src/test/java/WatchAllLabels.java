import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/WatchAllLabels.feature", glue = "steps")
public class WatchAllLabels extends AbstractTestNGCucumberTests{
}
