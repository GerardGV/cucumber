import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/ChangeLanguage.feature", glue = "steps")
public class ChangeLanguage extends AbstractTestNGCucumberTests{
}
