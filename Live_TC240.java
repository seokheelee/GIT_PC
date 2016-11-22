package com.cj.ui;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
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
 * Name : TC_240
 * Scenario : 로그인 > 마이존 > 회원정보 0> 개인정보 변경 > 필수 항목 미입력시 안내창 노출
 * Assertion : 필수 항목 inputbox null 입력 후 팝업 메시지 체크
 * Update : 불필요한 코드의 제거 (2016.11.14)
 *
 */

public class Live_TC240 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
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
	public void TC240() throws Exception {
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
			
			Thread.sleep(3000);
			
			// 개인정보 변경 페이지 이동 하단 버튼 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sub2_right']/div/form/p/a[1]/img")));
			System.out.println("개인정보 변경 페이지 이동 하단 버튼 뜰 때까지 기다림");
			
			// 자택전화 미입력 체크 - 1
			driver.findElement(By.xpath(".//*[@id='sub2_right']/div/form/div[2]/table/tbody/tr[3]/td/input[1]")).clear();;
			// 개인정보변경 클릭
			driver.findElement(By.xpath(".//*[@id='sub2_right']/div/form/p/a[1]/img")).click(); 
			System.out.println("자택전화 미입력 체크");
			
			Thread.sleep(3000);
			
			// "전화번호를 입력해 주세요!" 얼럿 메시지 확인
			assertTrue(closeAlertAndGetItsText().matches("전화번호를 입력해 주세요!"));
			if (acceptNextAlert) {
				driver.findElement(By.xpath(".//*[@id='sub2_right']/div/form/div[2]/table/tbody/tr[3]/td/input[1]")).sendKeys("1234");
			} else {
				assertTrue(false);
			}
			
			Thread.sleep(3000);
			
			// 이메일주소 미입력 체크 - 2
			driver.findElement(By.xpath(".//*[@id='sub2_right']/div/form/div[2]/table/tbody/tr[5]/td/input")).clear();;

			// 개인정보변경 클릭
			driver.findElement(By.xpath(".//*[@id='sub2_right']/div/form/p/a[1]/img")).click(); 
			System.out.println("이메일주소 미입력 체크");
			
			Thread.sleep(3000);
			
			// "이메일을 입력해 주세요!" 얼럿 메시지 확인
			assertTrue(closeAlertAndGetItsText().matches("이메일을 입력해 주세요!"));
			if (acceptNextAlert) {
				System.out.println("[TC_240] success");
				assertTrue(true);
				return;
			} else {
				System.out.println("[TC_240] failure : '이메일을 입력해 주세요!' 얼럿 메시지 불일치");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_240] failure");
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
