import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 {
	@Test
	public void script1() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Archana\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		driver.manage().window().maximize();
		System.out.println("STEP : Browser Maximized");
		driver.get("https://archanadec21-trials7401.orangehrmlive.com/auth/login");
		System.out.println("Step : URL Loded In Browser");

		WebElement logo = driver.findElement(By.xpath("//div[contains(@class,'organization-logo')]/child::img"));

		// Assert.assertEquals(logo.isDisplayed(), true);
		Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");

		WebElement loginPanel = driver.findElement(By.xpath("//div[@id='divLogin']/div[position()=2]/child::div"));
		Assert.assertTrue(loginPanel.isDisplayed(), "Login Panel is not displayed");

		WebElement userName = driver.findElement(By.xpath("//input[@id='txtUsername' and @name = 'txtUsername']"));
		if (userName.isEnabled()) {
			userName.sendKeys("admin");
			System.out.println("STEP : User Enters the UserName as Admin");
		}

		WebElement password = driver.findElement(By.xpath("//input[starts-with(@id, 'txtPassword')]"));
		if (password.isEnabled()) {
			password.sendKeys("y@IWABi7i0");
			System.out.println("STEP : User Enters the Password");
		}

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("Step : click on login");

		boolean titleDisplay = wait.until(ExpectedConditions.titleIs("Dashboard"));
		// Assert.assertEquals(driver.getTitle(), "Dashboard");
		Assert.assertTrue(titleDisplay, "Title not matched");
		System.out.println("Step : Verified title");

		List<WebElement> widgetList = wait.until(
				ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='widget-header']/span[2]"), 1));
		// List<WebElement> widgetList =
		// driver.findElements(By.xpath("//div[@class='widget-header']/span[2]"));
		Assert.assertEquals(widgetList.size() - 1, 11);
		System.out.println("Step : 11 widget on Dashboard");

		for (int index = 0; index < widgetList.size() - 1; index++) {
			String widgetName = widgetList.get(index).getText();
			boolean isWidgetDisplay = driver
					.findElement(By.xpath("//div[@class='widget-header']/span[2][text()='" + widgetName + "']"))
					.isDisplayed();

			Assert.assertTrue(isWidgetDisplay, "Widget not displayed : " + widgetName);
		}
	}
}
