package com.cj.ui;

import static org.junit.Assert.*;
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
 * Date : 2016-11-15
 * Subject : CJ Mall 운영 
 * Name : TC_251
 * Scenario : 회원정보 > 쇼핑알리미 설정 > 방송알리미 탭 > TV편성표 보기 클릭시 페이지 이동
 * Assertion : "TV쇼핑>주간편성표" Text 체크
 * Update : 불필요한 코드의 제거 (2016.11.15)
 *
 */

public class Live_TC251 {
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
	public void TC251() throws Exception {
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

			// 쇼핑알리미 설정 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='midLeft']/div/ul/li[4]/ul/li[3]/a")));
			System.out.println("쇼핑알리미 뜰 때까지 기다림");

			// 쇼핑알리미 설정 클릭
			driver.findElement(By.xpath(".//*[@id='midLeft']/div/ul/li[4]/ul/li[3]/a")).click();
			System.out.println("쇼핑알리미 설정 클릭");
			
			// 방송 알리미 탭 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sub2_right']/div/div[1]/div[1]/ul/li[1]/a/img")));
			System.out.println("방송 알리미 탭 뜰 때까지 기다림");
			
			// 방송 알리미 클릭
			driver.findElement(By.xpath(".//*[@id='sub2_right']/div/div[1]/div[1]/ul/li[1]/a/img")).click();
			System.out.println("방송 알리미 클릭");
			
			// TV편성표 보기 버튼 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sub2_right']/div/div[1]/div[2]/ul/li[2]/a/img")));
			System.out.println("TV편성표 보기 버튼 뜰 때까지 기다림");
			
			// TV편성표 보기 버튼 클릭
			driver.findElement(By.xpath(".//*[@id='sub2_right']/div/div[1]/div[2]/ul/li[2]/a/img")).click();
			System.out.println("TV편성표 보기 버튼 클릭");
			
			// "TV쇼핑 > 주간편성표" Text 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='locationEtcDiv']")));
			System.out.println("'TV쇼핑 > 주간편성표' Text 뜰 때까지 기다림");
						
			// "TV쇼핑 > 주간편성표" Text 확인
			if("TV쇼핑 > 생방송/편성표보기".equals(driver.findElement(By.xpath(".//*[@id='locationEtcDiv']")).getText())){
				System.out.println("[TC_251] success");
				assertTrue(true);
				return;
			} else {
				System.out.println("[TC_251] failure : 'TV쇼핑 > 주간편성표 Text 불일치'");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_251] failure");
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
