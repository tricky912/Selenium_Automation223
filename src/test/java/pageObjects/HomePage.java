package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//a//span[text()='My Account']") 
	WebElement lnkMyAcc;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement lnkLogin;
	
	
	public void ClickonMyAcc() {
		lnkMyAcc.click();
	}
	
	public void ClickonReg() {
		lnkRegister.click();
	}
	
	public void ClickonLogin() {
		lnkLogin.click();
	}
	
	

}
