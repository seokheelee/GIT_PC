package com.cj.ui;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.util.SmartProperties;

/**
 * 
 * @author SeokheeLee 
 * Date : 2016-11-14
 * Subject : CJ Mall 운영 
 * Name : TC_190
 * Scenario : 로그인 > 마이존 > 쇼핑통장 > 쿠폰 > CJmall이 추천하는 쿠폰수첩 > Coupon Book 노출
 * Assertion : 팝업 창 브라우저 Title "쿠폰수첩" 체크
 * Update : 불필요한 코드의 제거 (2016.11.14)
 *
 */

public class Live_TC190 {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	
	private String userId = null;
	private String passwd = null;
	private String browser = null;
	private long waitTime = 50;
	private String testpasswd = null;
	private String word_tc014_01 = null;
	private String word_tc051_01 = null;
	private String word_tc051_02 = null;
		

	@Before
	public void setUp() throws Exception {
		
		SmartProperties sp = SmartProperties.getInstance();
		userId = sp.getProperty("ID");
		passwd = sp.getProperty("PWD");
		waitTime = Long.parseLong(sp.getProperty("WaitTime"));
		browser = sp.getProperty("Browser");
		word_tc014_01 = sp.getProperty("Word_TC014_01");
		word_tc051_01 = sp.getProperty("Word_TC051_01");
		word_tc051_02 = sp.getProperty("Word_TC051_02");
		testpasswd = sp.getProperty("TESTPWD");

		if (browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
			}
			
		else {driver = new ChromeDriver();
		}

		sp.list(System.out);

		System.out.println("userId 	= " + userId);
		System.out.println("passwd 	= " + passwd);
		System.out.println("waitTime=" + waitTime);
		System.out.println("word_tc014_01 = " + word_tc014_01);
		System.out.println("word_tc051_01 = " + word_tc051_01);
		System.out.println("word_tc051_02 = " + word_tc051_02);
		System.out.println("testpasswd=" + testpasswd);

		baseUrl = "http://www.cjmall.com/";
		driver.manage().window().maximize();
	}	

	@Test
	public void TC190() throws Exception {
		try {
			WebDriverWait wait = null;

			driver.manage().window().maximize();

			// 메인 페이지 요청
			driver.get(baseUrl + "/index_tab1.jsp");
			System.out.println("메인 페이지 요청");

			// 메인 페이지 뜨고, 로그인 객체가 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span")));
			System.out.println("메인 페이지 뜨고, 로그인 객체가 뜰 때까지 기다림");
			
			//로그인 클릭
		    driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span")).click();
			System.out.println("로그인 클릭");

			// 로그인 창이 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='mid_1']")));

			driver.findElement(By.xpath(".//*[@id='mid_1']")).click();
			driver.findElement(By.xpath(".//*[@id='mid_1']")).clear();
			driver.findElement(By.xpath(".//*[@id='mid_1']")).sendKeys(userId);
			driver.findElement(By.xpath(".//*[@id='mpass']")).clear();
			driver.findElement(By.xpath(".//*[@id='mpass']")).sendKeys(passwd);
			driver.findElement(By.xpath(".//*[@id='login_area']/form/div/div/div[2]/input")).click();

			// 로그인이 되고 메인화면이 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span"),"로그아웃"));
			System.out.println("로그인이 되고 메인화면이 뜰 때까지 기다림");

			// 마이존 클릭
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[3]/a/span")).click();
			System.out.println("마이존 클릭");
			
			// 쿠폰 버튼 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='midLeft']/div/ul/li[2]/ul/li[1]/a")));
			System.out.println("쿠폰 버튼 뜰 때까지 기다림");
			
			// 쿠폰 클릭
			driver.findElement(By.xpath(".//*[@id='midLeft']/div/ul/li[2]/ul/li[1]/a")).click();
			System.out.println("쿠폰 클릭");
			
			// CJmall이 추천하는 쿠폰수첩 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='Map']/area")));
			System.out.println("CJmall이 추천하는 쿠폰수첩 뜰 때까지 기다림");
			
			// CJmall이 추천하는 쿠폰수첩 클릭
			driver.findElement(By.xpath(".//*[@id='Map']/area")).click();
			System.out.println("CJmall이 추천하는 쿠폰수첩 클릭");
			
			// Coupon Book 팝업 확인
			Set<String> allWindows2 = driver.getWindowHandles();
		    for(String curWindow2 : allWindows2){
		        driver.switchTo().window(curWindow2);
		    }
		    
		    // 팝업 창 브라우저 Title "쿠폰수첩" 체크
		    boolean isExist = existElement(driver, By.xpath(".//*[@id='popup']/div/div/div[2]/h1/img"), "Coupon Book");
		    System.out.println("dd="+driver.getTitle());
			if (isExist && driver.getTitle().equals("쿠폰수첩 | O! Shopping Smart - CJmall")) {
				System.out.println("[TC_190] success");
				assertTrue(true);
				return;
			} else {
				System.out.println("[TC_190] failure : '쿠폰수첩' Title 불일치");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_190] failure");
			assertTrue(false);
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	public boolean existElement(WebDriver wd, By by, String meaning) {
		WebDriverWait wait = new WebDriverWait(wd, 2);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException e) {

			System.out.println("[" + meaning + "] WebElement does not Exist. time out ");
			return false;
		}
		System.out.println("[" + meaning + "] WebElement Exist.");
		return true;
	}

}
