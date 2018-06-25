package Testing;

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
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\rishabhverma\\Downloads\\chromedriver_win32\\chromedriver.exe");
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
	  driver.findElement(By.linkText("Basic Course")).click();
	  driver.findElement(By.className("greenbox")).click();
	  System.out.println("Successfully click on Basic course and greenbox");
	  
  }
  @Test(priority = 3)
  public void box1andbox2colorsame() {
	  
	     String box1;
	    String box2;
	  boolean b= true;
		  driver.switchTo().frame("main");
	  box1= driver.findElement(By.id("answer")).getAttribute("class");
	  {
		System.out.println(box1);
	  while(b)
	       {
	    driver.switchTo().frame("child");
	    box2= driver.findElement(By.id("answer")).getAttribute("class");
	    System.out.println(box2);
	    if(box1.equals(box2))
			{
				b=false;
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			driver.switchTo().parentFrame();
			driver.findElement(By.linkText("Proceed")).click();
			
			}
			else
			{
				driver.switchTo().parentFrame();
				driver.findElement(By.linkText("Repaint Box 2")).click();
			}
	       }
	  }
			
	       
	   
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
	  driver.findElement(By.linkText("Launch Popup Window")).click();
	  //to open new tab
	   String abc = driver.getWindowHandle();
	   for( String xyz : driver.getWindowHandles())
	   {
	    	driver.switchTo().window(xyz);
	   }
	    
	    driver.findElement(By.id("name")).sendKeys("Rishabh");
	    driver.findElement(By.id("submit")).click();
	    driver.switchTo().window(abc);
	    driver.findElement(By.linkText("Proceed")).click();
	   
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
  
