package com.cj.ui;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.util.SmartProperties;

/**
 * 
 * @author JungHyunLee 
 * Date : 2016-03-04
 * Subject : CJ Mall 운영 
 * Name : TC_239
 * Scenario : 로그인 > 마이존 > 회원정보 > 개인정보 변경 > 항목 변경 후 개인정보 변경 버튼 클릭시 정보변경 가능 
 * Assertion : 팝업 메시지 "~님의 정보가 성공적으로 수정되었습니다." 체크
 *
 */

public class Live_TC239 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	private String userId = null;
	private String passwd = null;
	private String browser = null;
	private long waitTime = 50;
	private String videoPath = null;
	private String testpasswd = null;
	private String word_tc014_01 = null;
	private String word_tc051_01 = null;
	private String word_tc051_02 = null;
	private String word_tc052_01 = null;
	private String word_tc098_01 = null;
	private String word_tc098_02 = null;
		

	@Before
	public void setUp() throws Exception {
		String path = System.getProperty("user.dir"); // current path of project
		
		SmartProperties sp = SmartProperties.getInstance();
		userId = sp.getProperty("ID");
		passwd = sp.getProperty("PWD");
		waitTime = Long.parseLong(sp.getProperty("WaitTime"));
		videoPath = sp.getProperty("VIDEO_LOC");
		browser = sp.getProperty("Browser");
		word_tc014_01 = sp.getProperty("Word_TC014_01");
		word_tc051_01 = sp.getProperty("Word_TC051_01");
		word_tc051_02 = sp.getProperty("Word_TC051_02");
		word_tc052_01 = sp.getProperty("Word_TC052_01");
		word_tc098_01 = sp.getProperty("Word_TC098_01");
		word_tc098_02 = sp.getProperty("Word_TC098_02");
		testpasswd = sp.getProperty("TESTPWD");

		if (browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
			}
			
		else {
			System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
			//driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			driver = new ChromeDriver(options);
		}
		sp.list(System.out);

		System.out.println("userId 	= " + userId);
		System.out.println("passwd 	= " + passwd);
		System.out.println("waitTime=" + waitTime);
		System.out.println("videoPath = " + videoPath);
		System.out.println("word_tc014_01 = " + word_tc014_01);
		System.out.println("word_tc051_01 = " + word_tc051_01);
		System.out.println("word_tc051_02 = " + word_tc051_02);
		System.out.println("testpasswd=" + testpasswd);

		baseUrl = "http://www.cjmall.com/";
		driver.manage().window().maximize();
	}	

	@Test
	public void TC239() throws Exception {
		try {
			WebDriverWait wait = null;

			driver.manage().window().maximize();

		    int window_num = 0;
		    String mainWindow = driver.getWindowHandle();
		    System.out.println("main Windows ="+mainWindow);

		    Set<String> handles = driver.getWindowHandles();
		    window_num = handles.size();

		    System.out.println("Windows Num ="+window_num);

		    for (String handle : handles) {
		    	System.out.println("windows handles :"+handle);
		    	if (!handle.equals(mainWindow)){
		    		driver.switchTo().window(handle);
		    		System.out.println("Switch Windows");
		    		break;
		    		}
		    	}
			
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

			// //
			// // 2-1. 로그인 창이 뜰 때까지 기다림.
			// // 창이 늦게 떠서 에러로 리턴된 경우가 있었음.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='mid_1']")));

			driver.findElement(By.xpath(".//*[@id='mid_1']")).click();
			driver.findElement(By.xpath(".//*[@id='mid_1']")).clear();
			driver.findElement(By.xpath(".//*[@id='mid_1']")).sendKeys(userId);
			driver.findElement(By.xpath(".//*[@id='mpass']")).clear();
			driver.findElement(By.xpath(".//*[@id='mpass']")).sendKeys(passwd);
			driver.findElement(By.xpath(".//*[@id='login_area']/form/div/div/div[2]/input")).click();

			// //
			// // 3. 로그인이 되고 메인화면이 뜰 때까지 기다림.
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
			
			// 개인정보 변경 페이지 이동 하단 버튼 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sub2_right']/div/form/p/a[1]/img")));
			System.out.println("개인정보 변경 페이지 이동 하단 버튼 뜰 때까지 기다림");
			
			// 이름 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sub2_right']/div/form/div[2]/table/tbody/tr[1]/td")));
			System.out.println("이름 뜰 때까지 기다림");
			
			// 이름값
			String name = driver.findElement(By.xpath(".//*[@id='sub2_right']/div/form/div[2]/table/tbody/tr[1]/td")).getText();
			
			// 개인정보 변경 버튼 클릭
			driver.findElement(By.xpath(".//*[@id='sub2_right']/div/form/p/a[1]/img")).click();
			System.out.println("개인정보 변경 버튼 클릭");
			
			Thread.sleep(3000);
			
			// "~님의 정보가 성공적으로 수정되었습니다." 얼럿 메시지 확인
			assertTrue(closeAlertAndGetItsText().matches(name+"님의 정보가 성공적으로 수정되었습니다."));
			if (acceptNextAlert) {
				System.out.println("[TC_239] success");
				assertTrue(true);
				return;
			} else {
				System.out.println("[TC_239] failure : '~님의 정보가 성공적으로 수정되었습니다.' 얼럿 메시지 불일치");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_239] failure");
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
