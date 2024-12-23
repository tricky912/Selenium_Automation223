package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends BaseClass {

	public loginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmailLogin;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPwdLogin;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement btnLogin;
	
	public void enterEmail(String email) {
		txtEmailLogin.sendKeys(email);
	}
	public void enterPwd(String pwd) {
		txtPwdLogin.sendKeys(pwd);
	}
	public void clickonBtnLogin() {
		btnLogin.click();
	}
	

}
