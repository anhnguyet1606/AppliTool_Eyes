import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;

public class exercise1 {
	private static BatchInfo batch;
    private EyesRunner runner;
    private Eyes eyes;
    WebDriver driver;
   
		@BeforeClass
		  public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\Libraby\\chromedriver.exe");
			
		     driver=new ChromeDriver();
			 batch=new BatchInfo("Demo Applitools");
			 
			 runner=new ClassicRunner();
            
			 
		  }	
		
		
		@Test
		public void login() {
            
			driver.get("https://demoqa.com/login");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.findElement(By.xpath("//input[@id='userName']")).sendKeys("Moon");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678@aA");
			
			
			 //scrollToElement(driver.findElement(By.xpath("//button[@id='login']")));
			
			driver.findElement(By.xpath("//button[@id='login']")).click();
			
		
			
		}
		
		
		/*@Test
		 public void testCheckWindow() {
			 Eyes eyes= new Eyes(runner);
			 
			 eyes.setApiKey("my2100ii3aHg6VvqJv699Mc0bcVGqjD3rhpTWk6WiDmhOA110");
			 
			 eyes.setBatch(batch);
			
			eyes.open(driver, "Demo Applitools", "testCheckWindow");
			
		    //String xpathSelector=String.format("//ul[@class='menu-list']/li[starts-with(@id,'item')and(.='%s')]");
		    
			
		
			goToMenuBook();
			eyes.checkWindow("Book Store");
			
			searchBook("Learning Javascript");
			
			 eyes.checkWindow("Search book");
			 eyes.close();
			 } */
		
			 @Test
			 public void testDynamicContent() {
				 Eyes eyes= new Eyes(runner);
				 
				 eyes.setApiKey("my2100ii3aHg6VvqJv699Mc0bcVGqjD3rhpTWk6WiDmhOA110");
				 
				 eyes.setBatch(batch);
				 eyes.open(driver, "Demo applitool", "TestDynamicContent");
				 goToMenuBook();
				 eyes.checkWindow("Book Store");
				 
				 
				 String searchKey="Understanding ECMA";
				 searchBook(searchKey);
				 
				 
				 eyes.setMatchLevel(MatchLevel.CONTENT);
				 eyes.checkWindow("Search book");
				 eyes.close();
				 
			 }
			 
		 public void goToMenuBook() {
			 By book=By.xpath("//ul[@class='menu-list']/li/span[(text()='Book Store')]");
				
				 WebElement element=driver.findElement(book);
				 scrollToElement(element);
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				 element.click();
		 }
		 public void searchBook(String search) {
			 By searchBox= By.xpath("//input[@id='searchBox']");
			 driver.findElement(searchBox).sendKeys(search);
			 driver.findElement(By.xpath("//span[@id='basic-addon2']")).click();
		 }
		public void scrollToElement(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		}
		@AfterClass
		public void tearDownTest() {
			eyes.abortIfNotClosed();
			driver.quit();
		}

		 

	}

