package Step_Definitions;

import static org.testng.Assert.assertEquals;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class test3_steps {
	WebDriver driver;
	String booksName;
	BookModel book;
	
	public test3_steps() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();	
		booksName = "Git Pocket Guide";
		book = new BookModel();
	}
	
	@Given("User go to demoqa.com\\/books")
	public void user_go_to_demoqa_com_books() {
		//go to demoqa.com
		driver.navigate().to("https://demoqa.com/books");
		//maximize window
		driver.manage().window().maximize();
	}

	@When("User in the Book Store page")
	public void user_in_the_Book_Store_page(){
		WebElement pageTitle = driver.findElement(By.cssSelector(".main-header"));
		String actualPageTitle = pageTitle.getText();
		String expectedPageTitle = "Book Store";
		assertEquals(actualPageTitle, expectedPageTitle);
	}
    @And("User search book Git Pocket Guide")
    public void user_search_book_Git_Pocket_Guide(){
	    driver.findElement(By.id("searchBox")).sendKeys(booksName);
	    
	    WebElement Table = driver.findElement(By.cssSelector(".rt-tbody"));
	    List<WebElement> column_table = Table.findElements(By.className("rt-td"));
	    	    
	    for(int row = 0; row < 1; row++) {	    	
	    	for(int column = 0; column < column_table.size(); column++) {
		    	String data = column_table.get(column).getText();
		    	if (data.trim().isEmpty())
		    		continue;
		    	
		    	switch (column) {
		    		case 1: {
		    			book.setName(data);
		    			break;
		    		} 
		    		case 2: {
		    			book.setAuthor(data);
		    			break;
		    		}
		    		case 3: {
		    			book.setPublisher(data);
		    			break;
		    		}
		    	}		    	
	    	}
	    	
	    }
    }
    
    @And("User click book Git Pocket Guide")
    public void user_click_book_Git_Pocket_Guide(){
    	driver.findElement(By.xpath("//a[contains(text(),'"+ booksName+ "')]")).click();
    	
    }
    @Then("User see Git Pocket Guide")
    public void user_see_Git_Pocket_guide(){
    	String actualName = driver.findElement(By.xpath("//label[@id='title-label']/following::label")).getText();
    	String expectedName = book.getName();
    	assertEquals(actualName, expectedName);
    	
    	String actualAuthor = driver.findElement(By.xpath("//label[@id='author-label']/following::label")).getText();
    	String expectedAuthor = book.getAuthor();
    	assertEquals(actualAuthor, expectedAuthor);
        	
    	String actualPublisher = driver.findElement(By.xpath("//label[@id='publisher-label']/following::label")).getText();
    	String expectedPublisher = book.getPublisher();
    	assertEquals(actualPublisher, expectedPublisher);
    }
}

class BookModel {
	private String name;
	private String author;
	private String publisher;
	
	public void setName(String value) {
		this.name = value;
	}
	
	public void setAuthor(String value) {
		this.author = value;
	}
	
	public void setPublisher(String value) {
		this.publisher = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getPublisher() {
		return this.publisher;
	}
}
