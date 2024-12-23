package testCase;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.regPage;
import testBase.CommonScenarios;

public class TC001RegTestcase extends CommonScenarios{
	
	
	
	@Test(groups= {"Master"})
	public void VerifyAccReg() throws InterruptedException {
	try{
		HomePage hp=new HomePage(driver);
	
	logger.info("Navigated to Home page");
	hp.ClickonMyAcc();
	hp.ClickonReg();
	
	logger.info("Clicked on Registration Page");
	regPage RP= new regPage(driver);
	RP.EnterFname(RandomAlpha().toUpperCase());
	RP.EnterLname(RandomAlpha().toUpperCase());
	RP.EnterEmail(RandomAlpha()+"@gmail.com");
	RP.EnterPwd(AlphaNumeric() );
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
