package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BaseClass{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement spanMyacc;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	public boolean validateMyAccPage() {
			try {
		return(spanMyacc.isDisplayed());
		}
			catch(Exception e) {
				return false;
			}
	}
	public void clickLogout() 
	{
		JavascriptExecutor js=  (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", lnkLogout); // Correct scroll into view
		js.executeScript("arguments[0].click();", lnkLogout); // Correct click
//		lnkLogout.click();
	}

}

