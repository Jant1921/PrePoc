package imageHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PruebaSistemaUno {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	
	  @Before
	  public void setUp() throws Exception {
		  File file = new File("C:\\Users\\jruiz\\Downloads\\geckodriver.exe");
		  System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost:3001/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  //Thread.sleep(3000);
	  
	  @Test
	  public void pruebaTiempo() throws Exception {
	  	driver.get(baseUrl + "/");
	    driver.findElement(By.name("images[]")).clear();
	    driver.findElement(By.name("images[]")).sendKeys("C:\\Users\\jruiz\\Desktop\\pruebas\\C4_02_1_1_DAPI_001.jpg");
	    driver.findElement(By.name("Gauss")).click();
	    driver.findElement(By.name("Bilateral")).click();
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	    Thread.sleep(6000);
	    assertEquals("C4_02_1_1_DAPI_001.jpg", driver.findElement(By.cssSelector("p")).getText());
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	

}
