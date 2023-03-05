package org.openweathermap.api.weather;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@valid or @invalid", 
				 glue = {"org.openweathermap.api.weather"},
				 features = {"src/test/resources"},
				 plugin = {"pretty", "html:reports/sample.html","json:reports/sample.json","junit:reports/sample.xml"}
				)

public class CucumberRunnerTest {

}

	//and - tag1 and tag2 - both should be there
	//or  - tag1 or  tag2  - either one
	//not - not tag1 - except this.
