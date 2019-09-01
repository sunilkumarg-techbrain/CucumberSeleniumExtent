package seleniumgluecode;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
/**
 * The test class provides step definitions to test.feature file
 * @author G Sunil kumar
 *
 */
public class test {
	String SEARCH_STRING = "abcd";
	String HREF = "href";
	String currentProjectPath;
    public WebDriver driver;
    @Given("^User is  on google homepage$")
    public void user_is_on_google_homepage() {
    	currentProjectPath = System.getProperty("user.dir");
    	System.setProperty("webdriver.chrome.driver",currentProjectPath+ "\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://google.com");
        driver.manage().window().maximize();
    }
    @When("^User enters a search string$")
    public void user_enters_a_search_string() {
    	driver.findElement(By.xpath("//*[@name='q']")).clear();
    	driver.findElement(By.xpath("//*[@name='q']")).sendKeys(SEARCH_STRING + "\n");
    }
    @Then("^Search string is validated in the first search result$")
    public void search_string_is_validated_in_the_first_search_result() {
		Assert.assertTrue(" Assertion status for first search result containing " + SEARCH_STRING+ " - "+ " Actual HREF - " + driver.findElement(By.xpath("//*[@id='rso']//a[contains(@href,'abcd')]")).getAttribute(HREF) + " Expected search string - "+ SEARCH_STRING
				, driver.findElement(By.xpath("//*[@id='rso']//a[contains(@href,'abcd')]")).getAttribute(HREF).contains(SEARCH_STRING));
    	driver.quit();  
    }
}
