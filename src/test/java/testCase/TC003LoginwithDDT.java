package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.loginPage;
import testBase.CommonScenarios;
import utilities.Dataprovider;

public class TC003LoginwithDDT extends CommonScenarios {
	
	@Test(dataProvider="Logindata", dataProviderClass=Dataprovider.class,groups={"Sanity","Regression"})
	public void testLoginwithdata(String email,String pwd,String exp) throws InterruptedException {
		
		logger.info("*******Starting TC003LoginwithDDT*******");
		
//		try {
			HomePage hp= new HomePage(driver);
			logger.info("Navigated to Home page");
						hp.ClickonMyAcc();	
						
						
						
						hp.ClickonLogin();						
			logger.info("Clicked on Login");
			
					loginPage lp=new loginPage(driver);
			
			logger.info("Entering details on Login Page");
					lp.enterEmail(email);
					lp.enterPwd(pwd);
					lp.clickonBtnLogin();
			
			
				MyAccountPage ap=new MyAccountPage(driver);
				Thread.sleep(2000);
				boolean targetpage=ap.validateMyAccPage();		
				
				logger.info("Logged in Successfully");
				
				if(exp.equalsIgnoreCase("valid")){
					if(targetpage==true) {
						Thread.sleep(3000);
						ap.clickLogout();
						Assert.assertTrue(true);
					}
					else {
						Assert.assertTrue(false);
					}
				}
				else {
					if(targetpage==true) {
						ap.clickLogout();
						Assert.assertTrue(false);
					}
					else {
						Assert.assertTrue(true);
					}
		}
		
//		}
//		catch(Exception e) {
//			Assert.fail();
//		}
		logger.info("******************Finished Executing Testcase***************");
	
	
	
	

}
}
