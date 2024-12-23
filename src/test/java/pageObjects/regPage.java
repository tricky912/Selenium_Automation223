package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class regPage extends BaseClass {

	public regPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@name='firstname']")
	WebElement txtFname;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement txtLname;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtpwd;
	
	@FindBy(xpath="//input[@id='input-newsletter']")
	WebElement chksubscribe;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkAgree;
	
	@FindBy(xpath="//button[text()='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()=\"Your Account Has Been Created!\"]")
	WebElement msgConfirmation;
	
	
	
	
	public void EnterFname(String fname) {
		txtFname.sendKeys(fname);		
	}
	public void EnterLname(String lname) {
		txtLname.sendKeys(lname);		
	}
	public void EnterEmail(String email) {
		txtemail.sendKeys(email);		
	}
	public void EnterPwd(String Pwd) {
		txtpwd.sendKeys(Pwd);		
	}
//	public void ChkSubscribe() {
//		chksubscribe.click();		
//	}
	public void ChkAgree() {
//		chkAgree.click();
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", chkAgree);
	}
	public void SubmitDetails() {
//		btnContinue.click();
		btnContinue.submit();
	}
	
	
	public String GetConfirmationMsg() {
		try {
			return(msgConfirmation.getText());
		}
		catch(Exception e) {
		
		return (e.getMessage());
		
		}
		
		
		
		                                                          
	}
	
	
}
