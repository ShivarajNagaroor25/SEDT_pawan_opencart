package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDTTest extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class, groups = "DataDrivern")
	public void verify_LoginData(String email, String pwd, String expectedResult) {
		try{
		logger.info("****Starting_TC_003_LoginDDT****");

		HomePage hp=new HomePage(getDriver());
		hp.clickOnMyAccount();
		hp.clickOnLogin();

		logger.info("***Navigated to Login Page***");
		System.out.println(email+" "+pwd+" "+expectedResult);
		LoginPage lp=new LoginPage(getDriver());
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickOnLogin();

		MyAccountPage mac=new MyAccountPage(getDriver());
		boolean LoginMsg=mac.MyAccountMsg();

		boolean unsuccessfulMsg=lp.errorMsgLoginUnsuccessful();
		if(expectedResult.equalsIgnoreCase("Valid")) {

			if(LoginMsg==true) {
				mac.clickOnLogout();
				Assert.assertTrue(true);
				logger.info("****Logout_successfully****");
			}
			else {
				Assert.assertTrue(false);
				logger.info("****Loing_Unsuccessfull****");
			}

		}

		if(expectedResult.equalsIgnoreCase("Invalid")) {
			if(LoginMsg==false) {
				//mac.clickOnLogout();
				Assert.assertEquals(unsuccessfulMsg, "Warning: No match for E-Mail Address and/or Password.");
				Assert.assertTrue(false);
				logger.info("****Loing_Unsuccessfull****");
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) {
			Assert.fail(); 
		}
		logger.info("****TC_003_LoginDDT_Finished****");
	}

}
