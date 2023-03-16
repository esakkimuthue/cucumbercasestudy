package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features ="src//test//resources//features//ProductStore.feature",

		glue= {"StepDef"},
		monochrome = true,
		dryRun=false,
	    plugin = {"pretty"}

		        )
public class ProductStoreRunner extends AbstractTestNGCucumberTests
{
	
	
	}
