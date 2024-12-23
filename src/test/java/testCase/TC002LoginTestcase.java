package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.loginPage;
import testBase.CommonScenarios;

public class TC002LoginTestcase extends CommonScenarios{
	
	
	@Test (groups={"Sanity","Regression"})
	public void TC002testLogin(){
		
		logger.info("*******Starting TC002 Login Testcase*******");
		
		try {
	HomePage hp= new HomePage(driver);
	logger.info("Navigated to Home page");
				hp.ClickonMyAcc();				
				hp.ClickonLogin();
	logger.info("Clicked on Login");
	
			loginPage lp=new loginPage(driver);
	
	logger.info("Entering details on Login Page");
			lp.enterEmail(p.getProperty("email"));
			lp.enterPwd(p.getProperty("pwd"));
			lp.clickonBtnLogin();
	
	
		MyAccountPage ap=new MyAccountPage(driver);
		Thread.sleep(2000);
		boolean targetpage=ap.validateMyAccPage();	
		Assert.assertTrue(targetpage);
		logger.info("Logged in Successfully");
		
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("******************Finished Executing Testcase***************");
	
	
	
	
	
	}

}
