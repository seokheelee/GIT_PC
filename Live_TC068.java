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
 * Name : TC_068
 * Scenario : 비로그인 > 바로구매 버튼 기능 확인
 * Assertion : "로그인" 텍스트 확인 
 * Update : 불필요한 코드의 제거 (2016.11.07)
 *
 */

public class Live_TC068 {
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
	private String Word_tc069_01 = null;

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
		Word_tc069_01 = sp.getProperty("Word_TC069_01");
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
		System.out.println("Word_tc069_01 = " + Word_tc069_01);
		System.out.println("testpasswd=" + testpasswd);

		baseUrl = "http://www.cjmall.com/";
		driver.manage().window().maximize();
	}	

	@Test
	public void TC068() throws Exception {
		try {
			WebDriverWait wait = null;

			driver.manage().window().maximize();

			// 메인 페이지 요청
			driver.get(baseUrl + "/index_tab1.jsp");
			System.out.println("메인페이지 요청");

			// 검색버튼 뜰 때까지 기다림
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/form/fieldset/div/button")));
			System.out.println("검색버튼 뜰 때까지 기다림");
			
			// 검색창 클릭
			driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).click();
			System.out.println("검색창 클릭");
			
			driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).clear();
			
			// 검색어 "냉장고" 입력
			driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).sendKeys(Word_tc069_01);
			System.out.println("검색어 'LG 디오스 냉장고' 입력");

			// 검색 버튼 클릭			
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/form/fieldset/div/button")).click();
			System.out.println("검색 버튼 클릭");

			// 검색상품 기다림 
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='list_wrap']/ul/li[1]/a/span[1]/img")));
			System.out.println("검색상품 기다림");
			
			// 검색된 상품중 임의의 상품 클릭
			driver.findElement(By.xpath(".//*[@id='list_wrap']/ul/li[3]/a/span[1]/img")).click(); 
			System.out.println("검색된 상품중 임의의 상품 클릭");

			Set<String> allWindows2 = driver.getWindowHandles();
		    for(String curWindow2 : allWindows2){
		        driver.switchTo().window(curWindow2);
		    }
		    
			// 바로구매 버튼 기다림
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='btnBuyNow']")));
			System.out.println("바로구매 버튼 기다림");
			
			Thread.sleep(3000);
			
			// 바로구매 버튼 클릭
			driver.findElement(By.xpath(".//*[@id='btnBuyNow']")).click(); 
			System.out.println("바로구매 버튼 클릭");

			Thread.sleep(3000);
			
			// 로그인 화면 기다림
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='login_area']/form/h2/img")));
			System.out.println("로그인 화면 기다림");
		    
			// "로그인" Text 체크
			if ("".equals(driver.findElement(By.xpath(".//*[@id='login_area']/form/h2/img")).getText())) {
				System.out.println("[TC_068] success");
				assertTrue(true);
				return;
			} else {
				System.out.println("[TC_068] failure : '로그인' Text 불일치");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_068] failure");
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
