package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage{
		
	WebDriver driver;
	
	public AccountRegisterPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "input-firstname")
	WebElement txtFirstname;
	
	@FindBy(id="input-lastname")
	WebElement txtLastname;
	
	@FindBy(id="input-email")
	WebElement txtEmail;
	
	@FindBy(id="input-telephone")
	WebElement txtTelephone;
	
	@FindBy(id="input-password")
	WebElement txtPassword;
	
	@FindBy(id="input-confirm")
	WebElement txtConfirm;
	
	@FindBy(xpath = "//label[text()='Yes']")
	WebElement radbtnYes;
	
	@FindBy(xpath = "//label[text()='No']")
	WebElement radbtnNo;
	
	@FindBy(name = "agree")
	WebElement chkPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstname(String first) {
		txtFirstname.sendKeys(first);
	}
	
	public void setLastname(String last) {
		txtLastname.sendKeys(last);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel) {
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirm(String pwd) {
		txtConfirm.sendKeys(pwd);
	}
	
	public void clickPrivacyPolicy() {
		chkPolicy.click();
	}
	
	public void clickContinue() {
		//sol1
		btnContinue.click();
		
		//sol2
		//btnContinue.submit();
		
		//sol3
		//Actions a=new Actions(driver);
		//a.moveToElement(btnContinue).click().perform();
		
		//sol4
		//JavascriptExecutor jc=(JavascriptExecutor)driver;
		//jc.executeScript("arguments[0].click();", btnContinue);
		
		//sol5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//sol6 explicit wait
		//WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	public String getConfirmationMsg() {
		try {
		return(msgConfirmation.getText());
		}
		catch(Exception e) {
			return(e.getMessage());
		}
	}
}
