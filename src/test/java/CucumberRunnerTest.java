import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty","html:reports/test-report"},tags= "@apiTest")
public class CucumberRunnerTest {
}
