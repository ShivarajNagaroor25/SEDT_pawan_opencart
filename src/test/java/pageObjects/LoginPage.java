package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super (driver);
	}
	
	@FindBy (id = "input-email")
	WebElement txtemailAddress;
		
	@FindBy (id = "input-password")
	WebElement txtPassword;
	
	@FindBy (xpath = "//input[@type='submit']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//div[text()=\"Warning: No match for E-Mail Address and/or Password.\"]")
	WebElement errorMsg;
	
	
	public void setEmail(String emailId) {
		txtemailAddress.sendKeys(emailId);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickOnLogin() {
		btnLogin.click();
	}
	
	public boolean errorMsgLoginUnsuccessful() {
		try{
			return(errorMsg.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
}
