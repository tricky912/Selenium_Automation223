package testCase;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.regPage;
import testBase.CommonScenarios;

public class RegTestcase2 extends CommonScenarios{
	
	
	
	@Test
	public void VerifyAccReg() throws InterruptedException {
	try{
		HomePage hp=new HomePage(driver);
	
	logger.info("Navigated to Home page");
	hp.ClickonMyAcc();
	hp.ClickonReg();
	
	logger.info("Clicked on Registration Page");
	regPage RP= new regPage(driver);
	RP.EnterFname(p.getProperty("FirstName"));
	RP.EnterLname(p.getProperty("lastName"));
	RP.EnterEmail(p.getProperty("email"));
	RP.EnterPwd(p.getProperty("pwd"));
//	RP.ChkSubscribe();
	RP.ChkAgree();
	RP.SubmitDetails();
	
	logger.info("Submitted Details");
	String Message=RP.GetConfirmationMsg();
	Thread.sleep(5000);
	
	if(Message.equals("Your Account Has Been Created!")) {
		Assert.assertTrue(true);
	}
	else {
		Assert.assertTrue(false);
		logger.error("Test failed..");
		logger.debug("Test failed");
	}
	
	
	}
	catch(Exception e) {
		Assert.fail();
	}

}
}
