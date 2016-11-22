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
 * Date : 2016-03-04
 * Subject : CJ Mall 운영 
 * Name : TC_241
 * Scenario : 로그인 > 마이존 > 회원정보 > 비밀번호 변경 > 비밀번호 입력후 확인 클릭시 정보변경 가능
 * Assertion : 비밀번호 변경후 재로그인하여 확인 체크
 * Update : 불필요한 코드의 제거 (2016.11.15)
 *
 */

public class Live_TC241 {
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
	public void TC241() throws Exception {
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

			// 개인정보변경 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath(".//*[@id='midLeft']/div/ul/li[4]/ul/li[1]/a")));
			System.out.println("개인정보변경 뜰 때까지 기다림");

			// 개인정보변경 클릭
			driver.findElement(By.xpath(".//*[@id='midLeft']/div/ul/li[4]/ul/li[1]/a")).click();
			System.out.println("개인정보변경 클릭");

			// 개인정보변경 img 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sub2_right']/form/div/h1/img")));
			System.out.println("개인정보변경 img 뜰 때까지 기다림");

			// 비밀번호 입력
			driver.findElement(By.xpath(".//*[@id='pwd']")).click();
			driver.findElement(By.xpath(".//*[@id='pwd']")).sendKeys(passwd);
			driver.findElement(By.xpath(".//*[@id='sub2_right']/form/div/p[3]/a/img")).click();
			System.out.println("비밀번호 입력");
			
			// 탭 뜰때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sub2_right']/div/form/div[1]/ul/li[2]/a/img")));
			System.out.println("탭 뜰때까지 기다림");
			
			// 비밀번호 변경 탭 클릭
			driver.findElement(By.xpath(".//*[@id='sub2_right']/div/form/div[1]/ul/li[2]/a/img")).click();
			System.out.println("비밀번호 변경 탭 클릭");	
			
			// 입력창 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='old_password']")));
			System.out.println("입력창 뜰 때까지 기다림");
			
			
			// 기존 비밀번호 입력
			driver.findElement(By.xpath(".//*[@id='old_password']")).sendKeys(passwd);
			System.out.println("기존 비밀번호 입력");
			
			// 새 비밀번호 입력
			driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(testpasswd);;
			System.out.println("새 비밀번호 입력");
			
			// 새 비밀번호 확인 입력
			driver.findElement(By.xpath(".//*[@id='password2']")).sendKeys(testpasswd);;
			System.out.println("새 비밀번호 확인 입력");
			
			Thread.sleep(3000);
			 // 확인 버튼 클릭
			driver.findElement(By.xpath(".//*[@id='searchId']/p[2]/img")).click();
			System.out.println("확인 버튼 클릭");
			
			Thread.sleep(3000);
			
			driver.switchTo().alert().accept();
			
			// 개인정보변경 뜰 때까지 기다림. -2
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='midLeft']/div/ul/li[4]/ul/li[1]/a")));
			System.out.println("개인정보변경 뜰 때까지 기다림");

			// 개인정보변경 클릭 -2
			driver.findElement(By.xpath(".//*[@id='midLeft']/div/ul/li[4]/ul/li[1]/a")).click();
			System.out.println("개인정보변경 클릭");

			// 개인정보변경 img 뜰 때까지 기다림. -2
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sub2_right']/form/div/h1/img")));
			System.out.println("개인정보변경 img 뜰 때까지 기다림");

			// 비밀번호 입력 -2
			driver.findElement(By.xpath(".//*[@id='pwd']")).click();
			driver.findElement(By.xpath(".//*[@id='pwd']")).sendKeys(testpasswd);
			driver.findElement(By.xpath(".//*[@id='sub2_right']/form/div/p[3]/a/img")).click();
			System.out.println("비밀번호 입력");
			
			// 탭 뜰때까지 기다림. -2
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sub2_right']/div/form/div[1]/ul/li[2]/a/img")));
			System.out.println("탭 뜰때까지 기다림");
			
			// 비밀번호 변경 탭 클릭 -2
			driver.findElement(By.xpath(".//*[@id='sub2_right']/div/form/div[1]/ul/li[2]/a/img")).click();
			System.out.println("비밀번호 변경 탭 클릭");
			
			// 입력창 뜰 때까지 기다림. -2
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='old_password']")));
			System.out.println("입력창 뜰 때까지 기다림");
			
			// 기존 비밀번호 입력 -2
			driver.findElement(By.xpath(".//*[@id='old_password']")).sendKeys(testpasswd);
			System.out.println("기존 비밀번호 입력");
			
			// 새 비밀번호 입력 -2
			driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(passwd);;
			System.out.println("새 비밀번호 입력");
			
			// 새 비밀번호 확인 입력 -2
			driver.findElement(By.xpath(".//*[@id='password2']")).sendKeys(passwd);;
			System.out.println("새 비밀번호 확인 입력");

			Thread.sleep(3000);

			// 확인 버튼 클릭
			driver.findElement(By.xpath(".//*[@id='searchId']/p[2]/img")).click(); 
			System.out.println("확인 버튼 클릭");

			Thread.sleep(3000);

			driver.switchTo().alert().accept();
			
			// MY ZONE 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='wrap']/div[1]/a/img")));
			System.out.println("MY ZONE 뜰 때까지 기다림");
			
			// MY ZONE img alt 체크 (최종화면 도달 체크 목적)
			if("MY ZONE".equals(driver.findElement(By.xpath(".//*[@id='wrap']/div[1]/a/img")).getAttribute("alt"))){
				System.out.println("[TC_241] success");
				assertTrue(true);
				return;
			} else {
				System.out.println("[TC_241] failure : 'MY ZONE img alt 불일치'");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_241] failure");
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
