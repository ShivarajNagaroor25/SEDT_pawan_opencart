package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups = {"Regression","Master"})
	public void LoginTest() {
		try {
			logger.info("***Starting_TC_002_LoginTest***");

			HomePage hp=new HomePage(getDriver());
			hp.clickOnMyAccount();;
			hp.clickOnLogin();

			LoginPage lp=new LoginPage(getDriver());
			lp.setEmail(p.getProperty("emailId"));
			lp.setPassword(p.getProperty("pwd"));
			lp.clickOnLogin();

			MyAccountPage mac=new MyAccountPage(getDriver());
			boolean confirmMsg=mac.MyAccountMsg();

			Assert.assertEquals(confirmMsg, true, "Login Failed");
			
			mac.clickOnLogout();
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("***Finished exceution***");
	}
}
