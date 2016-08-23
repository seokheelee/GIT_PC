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
 * Name : TC_241
 * Scenario : 로그인 > 마이존 > 회원정보 > 비밀번호 변경 > 비밀번호 입력후 확인 클릭시 정보변경 가능
 * Assertion : 비밀번호 변경후 재로그인하여 확인 체크
 *
 */

public class Live_TC241 {
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
	public void TC241() throws Exception {
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
			
			if(window_num > 0){
			Set<String> allWindows1 = driver.getWindowHandles();
		    for(String curWindow1 : allWindows1){
		        driver.switchTo().window(curWindow1);
		    }
	  		Point hoverItem = driver.findElement(By.xpath(".//*[@id='pClose']")).getLocation();
	  		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
		  	System.out.println("테스트");
		  	
		  	Thread.sleep(3000);
		  			  	
		    //driver.close();
		  	driver.findElement(By.xpath(".//*[@id='pClose']")).click();
		    Thread.sleep(3000);

		    Set<String> allWindows0 = driver.getWindowHandles();
		    for(String curWindow0 : allWindows0){
		        driver.switchTo().window(curWindow0);
		    }
		    
		    Thread.sleep(3000);
			}
			
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
			
			//////////////// 비밀번호 수정 rollback //////////////////////
			
			
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
