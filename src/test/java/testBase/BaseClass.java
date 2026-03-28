package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;//log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public Logger logger;
	public Properties p;

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public WebDriver getDriver() {
		return driver.get();
	}

	@BeforeClass(groups = {"Sanity","Regression","Master","DataDrivern"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws Exception {
		//FileReader fr=new FileReader("./src//test//resources//config.properties");
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
		p=new Properties();//C:\Users\DELL\eclipse-workspace\SEDT_pawan_opencart\src\test\resources\config.properties
		p.load(fis);
	
		logger=LogManager.getLogger(this.getClass());

		if(p.getProperty("Execution_Env").equalsIgnoreCase("remote")) {
			if(br.equalsIgnoreCase("chrome")) {

				ChromeOptions options = new ChromeOptions();
				driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));

			}

			else if(br.equalsIgnoreCase("edge")) {

				EdgeOptions options = new EdgeOptions();
				driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));

			}
			else if(br.equalsIgnoreCase("firefox")){

				FirefoxOptions options=new FirefoxOptions();
				driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
			}
		}

		if(p.getProperty("Execution_Env").equalsIgnoreCase("local")) {

			switch(br.toLowerCase()) {
			case "chrome":driver.set(new ChromeDriver()); break;
			case "edge":driver.set(new EdgeDriver()); break;
			case "firefox":driver.set(new FirefoxDriver()); break;
			default:System.out.println("Ivalid browser name");return;
			} 
		}


		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		getDriver().get(p.getProperty("URL"));
		
	}

	@AfterClass(groups = {"Sanity","Regression","Master","DataDrivern"})
	public void tearDown() {
		getDriver().quit();
	}

	public String randomString() {
		String random=RandomStringUtils.randomAlphabetic(6);
		return random;
	}

	public String randomNumber() {
		String randomNum=RandomStringUtils.randomNumeric(10);
		return randomNum;
	}

	public String randomAplhanumeric() {
		String randomAphla=RandomStringUtils.randomAlphabetic(3);
		String randomNumeric=RandomStringUtils.randomAlphabetic(3);

		String AlphaNumeric=randomAphla+"@"+randomNumeric;
		return AlphaNumeric;
	}

	public String captureScreen(String tname) throws Exception{

		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takeScreenShot=(TakesScreenshot) getDriver();
		File sourceFile=takeScreenShot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}
}
