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
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.config.Configuration;
import com.applitools.eyes.selenium.BrowserType;

import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.model.DesktopBrowserInfo;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;

public class DemoUltrafastGrid {

	private static BatchInfo batch;
    private EyesRunner runner;
    private Eyes eyes;
    private Configuration classGridRunnerConfig;
    WebDriver driver;
   
		@BeforeClass
		  public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\Libraby\\chromedriver.exe");
			
		     driver=new ChromeDriver();
			 batch=new BatchInfo("Demo UltrafastGrid");
			 
			 setupVisualGridRunner();
			 
			 //runner=new ClassicRunner();
			 
			 eyes =new Eyes(runner);
			 eyes.setConfiguration(classGridRunnerConfig);
			 
			 eyes.setApiKey("my2100ii3aHg6VvqJv699Mc0bcVGqjD3rhpTWk6WiDmhOA110");
			 eyes.setBatch(batch);
            
			 
		  }	
		public void setupVisualGridRunner() {
			runner =new VisualGridRunner(new RunnerOptions().testConcurrency(10));
			
			classGridRunnerConfig =(Configuration) new Configuration()
					.addBrowser(new DesktopBrowserInfo(new RectangleSize(1600,900),BrowserType.CHROME))
					.addBrowser(new DesktopBrowserInfo(new RectangleSize(1600,900),BrowserType.FIREFOX))
					.setViewportSize(new RectangleSize(1600,900))
					.setApiKey("my2100ii3aHg6VvqJv699Mc0bcVGqjD3rhpTWk6WiDmhOA110")
					.setServerUrl("https://eyes.applitools.com/")
					.setAppName("Demo Ultrafast Grid")
					.setServerUrl("https://eyes.applitools.com/")
					.setBatch(batch);
			
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
		@Test
		public void testCheckWindowGrid() {
			Configuration testConfig=eyes.getConfiguration();
			testConfig.setTestName("Check windown test");
			eyes.setConfiguration(testConfig);
			
			eyes.open(driver,"demo application-Visual Grid","test visual grid");
			
			goToMenuBook();
			
			searchBook("API");
			eyes.checkWindow("Search Book");
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
			
		}
}
