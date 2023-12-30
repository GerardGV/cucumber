import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features/CommentOnVideo.feature", glue = "steps")
public class CommentOnVideoTest extends AbstractTestNGCucumberTests {
}
