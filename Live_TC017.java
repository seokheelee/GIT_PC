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
 * Date : 2016-11-07
 * Subject : CJ Mall 운영 
 * Name : TC_017
 * Scenario : 전체보기 클릭 후 세부 카테고리 항목 선택시 해당 카테고리 화면 이동 확인
 * Assertion : 각 카테고리별 항목 URL 또는 Text 체크 (각 페이지 이동이 다른 패턴이므로 세부 확인 필요)
 * Update : 불필요한 코드의 제거 (2016.11.07)
 *
 */

public class Live_TC017 {
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
	public void TC017() throws Exception {
		try{
			WebDriverWait wait = null;
			
			driver.manage().window().maximize();
			
			// 메인 페이지 요청
			driver.get(baseUrl + "/index_tab1.jsp");
			System.out.println("메인 페이지 요청");
			
			// 메인 페이지 뜨고, 전체보기 객체가 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/a")));
			System.out.println("메인 페이지 뜨고, 전체보기 객체가 뜰 때까지 기다림");
			
			// 전체보기 클릭 -1
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/a")).click();
			System.out.println("전체보기 클릭");
			
			Thread.sleep(3000);
			
			// 현대백화점 클릭
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/div/ul/li[1]/dl/dd[1]/a")).click();
			String n1 = driver.findElement(By.xpath(".//*[@id='cjm_container']/div[1]/div[1]/div[2]/h1/a")).getText();
			System.out.println("현대백화점 클릭");
			
			// 현대백화점 페이지 뜨고, 현대백화점 객체가 뜰 때까지 기다림
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjm_container']/div[1]/div[1]/div[2]/h1/a")));
			System.out.println("현대백화점 페이지 뜨고, 현대백화점 객체가 뜰 때까지 기다림");
						
			// 전체보기 클릭 -2
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/a")).click();
			System.out.println("전체보기 클릭");
			
			Thread.sleep(3000);
			
			// 롯데백화점 클릭
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/div/ul/li[1]/dl/dd[2]/a")).click();
			String n2 = driver.findElement(By.xpath(".//*[@id='cjm_container']/div[1]/div[1]/div[2]/h1/a")).getText();
			System.out.println("롯데백화점");
			
			// 롯데백화점 페이지 뜨고, 롯데백화점 객체가 뜰 때까지 기다림
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjm_container']/div[1]/div[1]/div[2]/h1/a")));
			System.out.println("롯데백화점 페이지 뜨고, 롯데백화점 객체가 뜰 때까지 기다림");	
			
			// '현대백화점' & '롯데백화점' Text 값 비교
			if("현대백화점".equals(n1)&&"롯데백화점".equals(n2)){
				System.out.println("[TC_017] success");
				assertTrue(true);
				return;
			} else {
				System.out.println("[TC_017] failure : '현대백화점' & '롯데백화점' Text 값 불일치");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_017] failure");
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
