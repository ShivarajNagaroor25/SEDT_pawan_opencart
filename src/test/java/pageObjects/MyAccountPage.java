package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (xpath = "//h2[text()='My Account']")
	WebElement msgMyAccout;
	
	@FindBy (xpath = "//div[@class=\"list-group\"]//a[text()='Logout']")
	WebElement btnLogout;
	
	public boolean MyAccountMsg() {
		try {
		return(msgMyAccout.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickOnLogout() {
		btnLogout.click();
	}
}
