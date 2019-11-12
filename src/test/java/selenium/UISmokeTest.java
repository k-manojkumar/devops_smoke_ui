package selenium;

import org.junit.Test;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

 

public class UISmokeTest {

 

   @Test

   public void uiSmokeTest() {

	   System.setProperty("webdriver.gecko.driver", "/home/manojkumar_ibt/geckodriver");
	   
       FirefoxDriver driver = new FirefoxDriver();

       driver.get("http://35.228.146.167:7000/");

       WebElement title = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[1]/label[2]"));

       System.out.println("********************************************************");

       System.out.println("* " + title.getAttribute("innerHTML") + " *");

       WebElement date = driver.findElement(By.xpath("/html/body/div/div/div[3]/div[1]/div[1]/div[3]/h2"));

       System.out.println("* " + date.getAttribute("innerHTML") + " *");

       System.out.println("********************************************************");
      
       
       driver.quit();

   }

}

