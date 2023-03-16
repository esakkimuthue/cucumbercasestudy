package StepDef;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ProductStoreStepDef {
	
	static WebDriver driver;
	static WebDriverWait wait;
	static String beforeDel;
	
	@BeforeAll
	public static void setup() throws IOException, InterruptedException
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
			driver.get("https://www.demoblaze.com/");
		}
	
	@Given("User is on login Page")
	public void user_is_on_login_page() throws InterruptedException {
		driver.findElement(By.xpath ( "//li/a[@id='login2']")).click();
	}
	@When("User INFO")
	public void User_INFO() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='loginusername']")).sendKeys("Esaki1");
		driver.findElement(By.xpath("//input[@id='loginpassword']")).sendKeys("Muthu");
		driver.findElement(By.xpath("//div/button[contains(text(),'Log in')]")).click();
	}
	@Then("Home Page")
	public void Home_page() throws InterruptedException {
		Thread.sleep(3000);
		 WebElement who = driver.findElement(By.xpath("//li/a[@id='nameofuser']"));
		 Thread.sleep(3000);
		 Assert.assertEquals(who.getText(), "Welcome Esaki1");
	}

	@Given("User is on home page")
	public void user_is_on_home_page() throws InterruptedException
	{
	    
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Home ']")).click();
	}
	@When("Add an Item {string} to {string} Cart")
	public void add_an_item_to_cart(String Category, String item) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Home ']")).click();
		String openCategory = "//a[text()='"+Category+"']";
		driver.findElement(By.xpath(openCategory)).click();
		Thread.sleep(2000);
		String openedItem = "//a[text()='"+item+"']";
		driver.findElement(By.xpath(openedItem)).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement button = driver.findElement(By.xpath("//a[text()='Add to cart']"));
		wait.until(ExpectedConditions.elementToBeClickable(button));
		button.click();
	}
	@Then("Should Add items to the Cart")
	public void should_add_items_to_the_cart() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}
	

	
	@Given("User is on cart the Cart Page")
	public void user_is_on_cart_the_cart_page() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Home ']")).click();
		driver.findElement(By.xpath("//li/a[@id='cartur']")).click();
	}
	@When("User deletes an Item")
	public void user_deletes_an_item() throws InterruptedException {
		Thread.sleep(3000);
		beforeDel = driver.findElement(By.xpath("//div/h3")).getText();
	    driver.findElement(By.xpath("//td/a")).click();
	}
	@Then("Delete an item from cart")
	public void delete_an_item_from_cart() throws InterruptedException {
		Thread.sleep(3000);
	    String afterDel = driver.findElement(By.xpath("//div/h3")).getText();
	    if(beforeDel.equals(afterDel)) {
			System.out.println("item not Deleted");
		}
		else {
			System.out.println("item Deleted");
		}
	
	}

	

	@When("User clicks PlaceOrder")
	public void user_clicks_place_order() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("button[data-toggle='modal']")).click(); 
	    Thread.sleep (3000);
	    driver.findElement(By.cssSelector("#name")).sendKeys("Esakki");
		driver.findElement(By.cssSelector("#country")).sendKeys("INDIA"); 
		driver.findElement(By.cssSelector("#city")).sendKeys("TVL");
	    driver.findElement(By.cssSelector("#card")).sendKeys("1234546789");
		driver.findElement(By.cssSelector("#month")).sendKeys("month"); 
		driver.findElement(By.cssSelector("#year")).sendKeys("year"); 
		Thread.sleep (3000);
		driver.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click(); 
	}
	@Then("Order is Purchased")
	public void order_is_purchased() {
		WebElement afterPurchase = driver.findElement(By.xpath("//h2[contains(text(),'Thank you')]"));
	    Assert.assertEquals(afterPurchase.getText(), "Thank you for your purchase!");
	    driver.findElement(By.cssSelector("button[tabindex='1']")).click();

	}
	
	@After
	public void attachImgToReport(Scenario scenario) {
		TakesScreenshot scr=(TakesScreenshot)driver;
		byte[] image=scr.getScreenshotAs(OutputType.BYTES);
		scenario.attach(image, "image/png", "imageOne");
		
	}
	
	
	@AfterAll
	public static void finish() 
	{
	   driver.close();
	}

}
