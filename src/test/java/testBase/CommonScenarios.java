package testBase;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.selcukes.commons.helper.RandomUtils;



public class CommonScenarios  {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
		
		
		@BeforeClass(groups = {"Sanity", "Regression", "Master"})
		@Parameters({"os", "browser"})
		public void Setup(String os, String br) throws FileNotFoundException, IOException, MalformedURLException {
		    FileReader file = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
		    p = new Properties();
		    p.load(file);

		    logger = LogManager.getLogger(this.getClass());

		    if (p.getProperty("Exec_env").equalsIgnoreCase("remote")) {
		        DesiredCapabilities capabilities = new DesiredCapabilities();

		        // Set platform based on OS parameter
		        switch (os.toLowerCase()) {
		            case "windows":
		                capabilities.setPlatform(Platform.WIN10);
		                break;
		            case "mac":
		                capabilities.setPlatform(Platform.MAC);
		                break;
		            case "linux":
		                capabilities.setPlatform(Platform.LINUX);
		                break;
		            default:
		                logger.error("No matching OS found");
		                return;
		        }

		        // Set browser based on browser parameter
		        switch (br.toLowerCase()) {
		            case "chrome":
		                capabilities.setBrowserName("chrome");
		                break;
		            case "edge":
		                capabilities.setBrowserName("MicrosoftEdge");
		                break;
		            default:
		                logger.error("Invalid browser");
		                return;
		        }

		        // Use class-level driver
		        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		    } else {
		        // Local execution
		        switch (br.toLowerCase()) {
		            case "chrome":
		                ChromeOptions chromeOptions = new ChromeOptions();
		                chromeOptions.addArguments("--start-maximized");
		                driver = new ChromeDriver(chromeOptions);
		                break;
		            case "edge":
		                EdgeOptions edgeOptions = new EdgeOptions();
		                edgeOptions.addArguments("--start-maximized");
		                driver = new EdgeDriver(edgeOptions);
		                break;
		            default:
		                logger.error("Invalid browser");
		                return;
		        }
		    }

		    // Open the application URL
		    try {
		        driver.get(p.getProperty("AppURL"));
		        logger.info("Page title after opening: " + driver.getTitle());
		    } catch (Exception e) {
		        logger.error("Error opening URL: ", e);
		    }

		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}

		@AfterClass(groups={"Sanity","Regression","Master"})
		
		public void Close() {
			driver.quit();
		}
		
		public String RandomAlpha() {
			String generatedString=RandomUtils.randomAlphabetic(5);
			return generatedString;
		}
		
		public String RandomNum() {
			String generatedNum=RandomUtils.randomNumeric(10);
			return generatedNum;
			
		}
		public String AlphaNumeric() {
			String generatedAlphanumeric=RandomUtils.randomAlphaNumeric(8);
			return generatedAlphanumeric;
		}
		
		
	    // Capture Screenshot method
	    public String captureScreenshot(String tname) throws IOException {
	        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	        TakesScreenshot takesScreenshot=(TakesScreenshot) driver; 
	        File sourceFile=takesScreenshot.getScreenshotAs (OutputType.FILE); 
	        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timestamp+".png";
	        File targetFile=new File(targetFilePath);
	        sourceFile.renameTo(targetFile); 
	        return targetFilePath;
	    }

	    // Ensure driver cleanup
//	    public void tearDown() {
//	        if (driver != null) {
//	            driver.quit();
//	            driver = null;
//	        }
//	    }
}
	

		

