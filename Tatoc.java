package Tatoc_Ng;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Tatoc {
  public static WebDriver driver;
  //Launch Browser
  @BeforeClass
  public void launchbrowser() {
	  System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
	  driver = new ChromeDriver();
	  
  }
  @Test(priority=1)
  public void pageverifaction() {
	  driver.get("http://10.0.1.86/tatoc");
	  String pagetitle = driver.getTitle();
	  Assert.assertEquals("Welcome - T.A.T.O.C", pagetitle);
	  System.out.println("PAge verified");
  }
  @Test(priority = 2)
  public void clickonbasiccourseandclickongreenbox() {
	  driver.findElement(By.linkText("Basic Course"));
	  driver.findElement(By.className("greenbox")).click();
	  System.out.println("Successfully click on Basic course and greenbox");
	  
  }
  @Test(priority = 3)
  public void box1andbox2colorsame() {
	    driver.switchTo().frame("main");
		WebElement box1 = driver.findElement(By.id("answer"));
		String Box1 = box1.getAttribute("class");
		String Box2 = "";
		while(!Box1.equals(Box2)){
		    driver.switchTo().frame("main");
		    driver.findElement(By.cssSelector("a")).click();
		    driver.switchTo().frame("child");
		    Box2=driver.findElement(By.id("answer")).getAttribute("class");
		}
		driver.switchTo().frame("main");
		driver.findElement(By.linkText("Proceed")).click();
		System.out.println("Successfully matches of both the box and click on Proceed Button");
  }
  @Test(priority = 4)
  public void draganddropfunction() {
	    Actions act=new Actions(driver);
		WebElement drop = driver.findElement(By.cssSelector("#dropbox"));
		WebElement drag = driver.findElement(By.cssSelector("#dragbox"));
		act.dragAndDrop(drag, drop).build().perform();
		driver.findElement(By.linkText("Proceed")).click();
		System.out.println("Successful drag and drop action");
  }
  @Test(priority = 5)
  public void newwindowandsearchsomething() {
	// It will return the parent window name as a String
		String parentWindow = driver.getWindowHandle();
		// This will return the number of windows opened by Webdriver and will return Set of St//rings
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);
		Iterator<String> I1= handles.iterator();
		while(I1.hasNext())
		{
		   String child_window=I1.next();
		// Here we will compare if parent window is not equal to child window then we            will close 
		if(!parentWindow.equals(child_window))
		{
		driver.switchTo().window(child_window);
		    	driver.findElement(By.id("name")).sendKeys("Rishabh");
			    driver.findElement(By.id("submit")).click();
			    break;
			}
	    }
		driver.switchTo().window(parentWindow);
		driver.findElement(By.linkText("Proceed")).click();
	    driver.switchTo().window(parentWindow);
	    System.out.println("Successfully opening of new tab and seacrching");
  }
  @Test(priority = 6)
  public void tokengeneration() {
	    driver.findElement(By.linkText("Generate Token")).click();
		String Cookie_val = driver.findElement(By.id("token")).getText();
		Cookie cookie = new Cookie("Token", Cookie_val.substring(7));
		driver.manage().addCookie(cookie);
		driver.findElement(By.linkText("Proceed")).click();
  }
  @AfterClass
  public void closebrowser() {
	  driver.close();
	  
  }
  
  
}
  
