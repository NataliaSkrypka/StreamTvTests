package ua.net.streamtv.testSteps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by nskrypka on 8/24/15.
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = {"ua.net.streamtv.testSteps", "cucumber.api.spring"})
public class StepDefsTest {
}
