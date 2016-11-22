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
 * Date : 2016-11-07
 * Subject : CJ Mall 운영 
 * Name : TC_009
 * Scenario : "온마트" 클릭시 온마트 메인화면 전환 확인
 * Assertion : 브라우저 Title "CJ제일제당 직영몰, CJ온마트" 체크
 * Update : 불필요한 코드의 제거 (2016.11.07)
 *
 */

public class Live_TC009 {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	
	private String userId = null;
	private String passwd = null;
	private String browser = null;
	private long waitTime = 50;
	private String testpasswd = null;
	private String word_tc051_01 = null;
	private String word_tc051_02 = null;

	@Before
	public void setUp() throws Exception {
		
		SmartProperties sp = SmartProperties.getInstance();
		userId = sp.getProperty("ID");
		passwd = sp.getProperty("PWD");
		waitTime = Long.parseLong(sp.getProperty("WaitTime"));
		browser = sp.getProperty("Browser");
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
		System.out.println("word_tc051_01 = " + word_tc051_01);
		System.out.println("word_tc051_02 = " + word_tc051_02);
		System.out.println("testpasswd=" + testpasswd);

		baseUrl = "http://www.cjmall.com/";
		driver.manage().window().maximize();
	}	

	@Test
	public void TC009() throws Exception {
		try{
			WebDriverWait wait = null;
			
			driver.manage().window().maximize();
			
			// 메인 페이지 요청
			driver.get(baseUrl + "/index_tab1.jsp");
			System.out.println("메인 페이지 요청");
			
			// 메인 페이지 뜨고, 로그인 객체가 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span")));
			System.out.println("메인 페이지 뜨고, 로그인 객체가 뜰 때까지 기다림");
			
		    boolean isExist = existElement(driver, By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[9]/a"), "온마트");
			if (isExist) {
				// 온마트 클릭
				driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[9]/a")).click();
				System.out.println("온마트 클릭");
			} else {
				System.out.println("[TC_009] success : '온마트' 버튼 없음");
				assertTrue(true);
				return;
			}
			
			Set<String> allWindows2 = driver.getWindowHandles();
		    for(String curWindow2 : allWindows2){
		        driver.switchTo().window(curWindow2);
		    }
		    
		    // 브라우저 Title "CJ제일제당 직영몰, CJ온마트" 체크
		    boolean isExist1 = existElement(driver, By.xpath(".//*[@id='header_user']/div/dl[2]/dd[1]/h1/a/img"), "온마트");
			if (isExist1 && driver.getTitle().equals("CJ제일제당 직영몰, CJ온마트")) {
				System.out.println("[TC_009] success");
				assertTrue(true);
				return;
			} else {
				System.out.println("[TC_009] failure : 브라우저 Title 'CJ제일제당 직영몰, CJ온마트' 불일치");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_009] failure");
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
