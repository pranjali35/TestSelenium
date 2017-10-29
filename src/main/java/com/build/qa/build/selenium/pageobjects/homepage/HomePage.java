package com.build.qa.build.selenium.pageobjects.homepage;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import com.build.qa.build.selenium.pageobjects.BasePage;


public class HomePage extends BasePage {

	private By buildThemeBody;
	private By itemName;
	private By alert;
	private By product;
	private By emailNotification;

	public HomePage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		buildThemeBody = By.cssSelector("body.build-theme");
		alert = By.id("newsletter-modal");
		itemName = By.cssSelector("#heading");
		product=By.cssSelector(".col-xs-9 > a:nth-child(2)");
		emailNotification = By.xpath(".//*[@id='easyNotification']");
	}

	public boolean onBuildTheme() { 
		return wait.until(ExpectedConditions.presenceOfElementLocated(buildThemeBody)) != null;
	}

	public boolean isAlertPresent()
	{
		return wait.until(ExpectedConditions.presenceOfElementLocated(alert)) != null;

	}


	public boolean SearchItem() throws InterruptedException
	{	
		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.switchTo().activeElement();
		WebElement ele=driver.findElement(By.xpath(".//*[@id='email-subscribe-splash']/div/div/div[1]/button"));
		ele.sendKeys(Keys.ENTER);
		WebElement element = driver.findElement(By.id("search_txt"));
		element.sendKeys("Quoizel MY1613");
		element.submit();
		return wait.until(ExpectedConditions.presenceOfElementLocated(itemName)) != null;


	}

	public boolean addProduct() throws InterruptedException
	{	
		driver.manage().window().maximize();
//		Thread.sleep(5000);
//		driver.switchTo().activeElement();
//		Thread.sleep(2000);
//		WebElement ele=driver.findElement(By.xpath(".//*[@id='email-subscribe-splash']/div/div/div[1]/button"));
//		Thread.sleep(2000);
//		ele.sendKeys(Keys.ENTER);
		dismissAlert();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		//Scroll vertically downward by 250 pixels
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div/div[5]/div[2]/div[4]/ul/li[2]/div[2]/a/div[1]/img")));
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div/div[5]/div[2]/div[4]/ul/li[2]/div[2]/a/div[2]/span")).click();
		jse.executeScript("window.scrollBy(0,350)", "");	
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='configure-product-wrap']/button")).click();     
		return wait.until(ExpectedConditions.presenceOfElementLocated(product)) != null;

	}

	public boolean emailCart() throws InterruptedException
	{
//		Thread.sleep(5000);
//		driver.switchTo().activeElement();
//		Thread.sleep(2000);
//		WebElement ele=driver.findElement(By.xpath(".//*[@id='email-subscribe-splash']/div/div/div[1]/button"));	
//		ele.sendKeys(Keys.ENTER);
		dismissAlert();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		//Scroll vertically downward by 250 pixels
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div/div[5]/div[2]/div[4]/ul/li[2]/div[2]/a/div[1]/img")));
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div/div[5]/div[2]/div[4]/ul/li[2]/div[2]/a/div[2]/span")).click();
		jse.executeScript("window.scrollBy(0,350)", "");	
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='configure-product-wrap']/button")).click();  
		//		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='header']/section[2]/div/div/div/a[2]/button")).click();
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(6000);
		driver.findElement(By.xpath(".//*[@id='page-content']/div[1]/div[1]/div/section[2]/div/div[1]/table/tbody/tr[2]/td/button[1]")).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.switchTo().activeElement()));
		driver.findElement(By.xpath(".//*[@id='yourName']")).sendKeys("Pranjali");
		driver.findElement(By.xpath(".//*[@id='yourEmail']")).sendKeys("pct9@njit.edu");
		driver.findElement(By.xpath(".//*[@id='recipientName']")).sendKeys("jgilmore");
		jse.executeScript("window.scrollBy(0,500)", ""); 
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='recipientEmail']")).sendKeys("SeleniumTest@build.com");
		//		Thread.sleep(2000);
		//		jse.executeScript("window.scrollBy(0,500)", ""); 
		driver.findElement(By.xpath(".//*[@id='quoteMessage']")).sendKeys("This is Pranjali, sending you a cart from my automation!");
		driver.findElement(By.xpath(".//*[@id='cart-email']/div/div/div[2]/div[2]/form/div[4]/button")).click();
		//		Thread.sleep(1000);
		String email =driver.switchTo().activeElement().getText();
		Thread.sleep(1000);

		return (email.equals("Emailing Cart..."));

	}

	public boolean narrowSelection() throws InterruptedException
	{
//		Thread.sleep(5000);
//		driver.switchTo().activeElement();
//		WebElement ele=driver.findElement(By.xpath(".//*[@id='email-subscribe-splash']/div/div/div[1]/button"));
//		Thread.sleep(2000);
//		ele.sendKeys(Keys.ENTER);
		dismissAlert();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='header']/nav/div/ul/li[2]/a")));
		WebElement element = driver.findElement(By.xpath(".//*[@id='header']/nav/div/ul/li[2]/a"));
		Actions action = new Actions(driver);	 
		action.moveToElement(element).perform();
		Thread.sleep(1000);
		 
		action.moveToElement(driver.findElement(By.xpath(".//*[@id='header']/nav/div/ul/li[2]/div/div/div[1]/a[1]"))).click();
		action.perform();

		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='facet-options']/li[1]/ul/li[1]/label/span")).click();
		String type = driver.findElement(By.xpath(".//*[@id='facet-options']/li[1]/ul/li[1]/label/span")).getAttribute("checked");
		Thread.sleep(5000);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", ""); 
		driver.findElement(By.xpath(".//*[@id='facet-options']/li[2]/ul/li[2]/label")).click();
		String work = driver.findElement(By.xpath(".//*[@id='facet-options']/li[2]/ul/li[2]/label")).getAttribute("checked");
		Thread.sleep(5000);
		jse.executeScript("window.scrollBy(0,250)", ""); 
		Thread.sleep(5000);	
		if (type == null && work == null)
		{	//Scroll vertically downward by 250 pixels
			return true;
		}
		else
		{
			return false;
		}
	}
	public void dismissAlert() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.switchTo().activeElement();
		Thread.sleep(2000);
		WebElement ele=driver.findElement(By.xpath(".//*[@id='email-subscribe-splash']/div/div/div[1]/button"));
		Thread.sleep(2000);
		ele.sendKeys(Keys.ENTER);
	}
	
	public void addProductToCart() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		//Scroll vertically downward by 250 pixels
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div/div[5]/div[2]/div[4]/ul/li[2]/div[2]/a/div[1]/img")));
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[1]/div/div[5]/div[2]/div[4]/ul/li[2]/div[2]/a/div[2]/span")).click();
		jse.executeScript("window.scrollBy(0,350)", "");	
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='configure-product-wrap']/button")).click();     
	}
}
