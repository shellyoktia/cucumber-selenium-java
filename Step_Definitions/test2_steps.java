package Step_Definitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.testng.Assert.assertEquals;

public class test2_steps {
	WebDriver driver;
	
	public test2_steps() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();		
	}
	@Given("User go to demoqa.com")
	public void user_go_to_demoqa_com() {
			//go to demoqa.com
			driver.navigate().to("https://demoqa.com/books");
			//maximize window
			driver.manage().window().maximize();
	}

	@When("User in Book Store page")
	public void user_in_book_store_page() {
		WebElement pageTitle = driver.findElement(By.cssSelector(".main-header"));
		String actualPageTitle = pageTitle.getText();
		String expectedPageTitle = "Book Store";
		assertEquals(actualPageTitle, expectedPageTitle);
	}

	@When("User search book qa engineer")
	public void user_search_book_qa_engineer() {
		String booksName = "qa engineer";
	    driver.findElement(By.id("searchBox")).sendKeys(booksName);
	}

	@Then("User see No rows found")
	public void user_see_no_rows_found() {
	   WebElement noData = driver.findElement(By.cssSelector(".rt-noData"));
	   assertEquals(noData.isDisplayed(), true);
	   driver.close();
	}
}
