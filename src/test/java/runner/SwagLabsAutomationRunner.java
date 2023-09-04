package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
					features = "src/test/resources/features",
					glue = "steps",
					monochrome = false,
					plugin = {"pretty", "html:target/cucumberReports.html"},
					tags = "@Regression"
				)
public class SwagLabsAutomationRunner {

}