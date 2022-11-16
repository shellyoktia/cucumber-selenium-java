package Step_Definitions;
import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class test1_steps {
	
	WebDriver driver;
	JavascriptExecutor js;
	
	public test1_steps() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();	
		js = (JavascriptExecutor)driver;
	}
	
	@Given("User go to demoqa")
	public void user_go_to_demoqa() {
		//go to demoqa.com
		driver.navigate().to("https://demoqa.com/select-menu");
		//maximize window
		driver.manage().window().maximize();
	}

	@When("User in select menu page")
	public void user_in_page_menu_page() {
		WebElement pageTitle = driver.findElement(By.cssSelector(".main-header"));
		String actualPageTitle = pageTitle.getText();
		String expectedPageTitle = "Select Menu";
		assertEquals(actualPageTitle, expectedPageTitle);
	}

	@When("User choose select value Another root option")
	public void user_choose_select_value_Another_root_option() {
		driver.findElement(By.id("withOptGroup")).click();
		WebElement anotherRootOption = driver.findElement(By.id("react-select-2-option-3"));
		js.executeScript("arguments[0].click();", anotherRootOption);
	}

	@When("User choose select one Other")
	public void user_choose_select_one_Other() {
		driver.findElement(By.id("selectOne")).click();
		WebElement otherOption = driver.findElement(By.id("react-select-3-option-0-5"));
		js.executeScript("arguments[0].click();", otherOption );
	}

	@When("User choose old style select menu Aqua")
	public void user_choose_old_style_select_menu_Aqua() {
		WebElement oldSelectMenu = driver.findElement(By.id("oldSelectMenu"));
		oldSelectMenu.click();
		oldSelectMenu.findElement(By.xpath("//option[. = 'Aqua']")).click();
	}

	@When("User choose multi select drop down all color")
	public void user_choose_multi_select_drop_down_all_color() {
		WebElement Element = driver.findElement(By.xpath("//b[contains(text(),'Multiselect drop down')]"));
		js.executeScript("arguments[0].scrollIntoView();", Element);
		driver.findElement(By.xpath("//b[contains(text(),'Multiselect drop down')]/following::div[@class=' css-1hwfws3']")).click();
		for(int i = 0; i<4; i++) {
			driver.findElement(By.id("react-select-4-option-"+i)).click();
		}
	}

	@Then("User success input all select menu")
	public void user_success_input_all_select_menu() {
		String actualSelectValue = driver.findElement(By.id("withOptGroup")).getText();
		String expectedSelectValue = "Another root option";
		assertEquals(actualSelectValue, expectedSelectValue);
		
		String actualSelectOne = driver.findElement(By.id("selectOne")).getText();
		String expectedSelectOne = "Other";
		assertEquals(actualSelectOne, expectedSelectOne);
		
		Select select = new Select (driver.findElement(By.id("oldSelectMenu")));
		WebElement option = select.getFirstSelectedOption();
		String actualOldStyleSelectMenu = option.getText();
		String expectedOldStyleSelectMenu = "Aqua";
		assertEquals(actualOldStyleSelectMenu, expectedOldStyleSelectMenu);
		
		String multiselectDropDown = driver.findElement(By.xpath("//b[contains(text(),'Multiselect drop down')]/following::div[@class=' css-1hwfws3']")).getText();
		ArrayList<String> expectedArray = new ArrayList<String>();
		String[] arraySelected = multiselectDropDown.split("\n");
		expectedArray.add("Green");
		expectedArray.add("Blue");
		expectedArray.add("Black");
		expectedArray.add("Red");
		
		for (int i=0; i<arraySelected.length; i++) {
			assertEquals(arraySelected[i], expectedArray.get(i));
		}
	}
}
