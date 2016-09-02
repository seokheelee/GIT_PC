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
 * Date : 2016-02-25
 * Subject : CJ Mall 운영 
 * Name : TC_052
 * Scenario : 최근 검색어 정상노출 확인
 * Assertion : 직전 검색어의 최근검색어 항목 노출 확인
 *
 */

public class Live_TC052 {
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
	public void TC052() throws Exception {
		try{
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
			
			// 검색창 뜰 때까지 기다림. -1
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='srch_disp_value']")));
			System.out.println("검색창 뜰 때까지 기다림");
			
			// 검색창 클릭
			driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).click();
			driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).clear();

			// 검색어 "냉장고" 입력
			driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).sendKeys(word_tc052_01);
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/form/fieldset/div/button")).click();
			System.out.println(word_tc052_01 + "검색");
			
			// 메인화면 이동
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/h1/a")).click();
			System.out.println("메인화면 이동");
			
			// 검색창 뜰 때까지 기다림. -2
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='srch_disp_value']")));
			System.out.println("검색창 뜰 때까지 기다림");
			
			Thread.sleep(10000);

//			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[4]/p[2]/a")).click(); // 검색창 클릭
			// 검색창 클릭
			driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).click();
			System.out.println("검색창 클릭");
			
			// 최근검색어 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='recent_word']")));
			System.out.println("최근검색어 뜰 때까지 기다림");
			
			// 최근 검색어 탭 클릭			
			driver.findElement(By.xpath(".//*[@id='recent_word']")).click();
			System.out.println("최근 검색어 탭 클릭");
			
			// 최근검색어 결과 항목 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='new_search']/ul/li/a[1]")));
			System.out.println("최근검색어 결과 항목 뜰 때까지 기다림");
			
			// 직전 검색어의 최근검색어 항목 노출 확인
			String word = driver.findElement(By.xpath(".//*[@id='new_search']/ul/li/a[1]")).getText();
			if(!"".equals(word)){
				System.out.println("[TC_052] success");
				assertTrue(true);
				return;
			} else {
				System.out.println("[TC_052] failure : 최근검색어 null...");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_052] failure");
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
