package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Sanity","Master"})
	public void verify_account_registration() {
		logger.info("****Starting_TC001_AccountRegistrationPage****");

		try {
			HomePage hp=new HomePage(getDriver());
			hp.clickOnMyAccount();
			logger.info("****Clicked on MyAccount Link****");

			hp.clickOnRegister();
			logger.info("****Clicked on Register Link****");

			AccountRegisterPage ag=new AccountRegisterPage(getDriver());
			logger.info("****Providing customer Details****");

			ag.setFirstname(randomString().toUpperCase());
			ag.setLastname(randomString().toUpperCase());
			ag.setEmail(randomString()+"@gmail.com");
			ag.setTelephone(randomNumber());

			String password=randomAplhanumeric();
			ag.setPassword(password);
			ag.setConfirm(password);

			ag.clickPrivacyPolicy();
			ag.clickContinue();

			logger.info("****Validating expected result****");
			String conMsg=ag.getConfirmationMsg();

			if(conMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}
			else {
				logger.error("Test failed");
				logger.debug("Debug Logs");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("****Finished_TC001_AccountRegistrationPage****");
	}
}
