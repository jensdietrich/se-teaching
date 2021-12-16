package test.nz.ac.vuw.jenz.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * A junit test that acts as an adapter with cucumber.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = { "classpath:features/Adder.feature" },
        glue = {"test.nz.ac.vuw.jenz.bdd" })
public class TestAdder {}