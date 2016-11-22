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
 * Name : TC_016
 * Scenario : 전체보기 클릭시 모든 카테고리 노출 확인
 * Assertion : 각 카테고리별 항목 Text 체크
 * Update : 불필요한 코드의 제거 (2016.11.07)
 * 
 */

public class Live_TC016 {
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
	public void TC016() throws Exception {
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
			
			// 전체보기 클릭
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/a")).click();
			System.out.println("전체보기 클릭");
			
			Thread.sleep(3000);
			
			// 카테고리 항목 Text 값
			String v1 = driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/div/ul/li[1]/div/a[1]")).getText();
			String v2 = driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/div/ul/li[2]/dl/dt")).getText();
			String v3 = driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/div/ul/li[3]/dl/dt")).getText();
			String v4 = driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/div/ul/li[4]/dl/dt")).getText();
			String v5 = driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/div/ul/li[5]/dl/dt")).getText();
			String v6 = driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[3]/div/ul/li[9]/dl/dt")).getText();
			
			
			if("TV쇼핑".equals(v1)&&"패션/언더웨어".equals(v2)&&"잡화/스포츠".equals(v3)&&"뷰티/보석".equals(v4)&&"유아동/도서".equals(v5)&&"여행/보험/서비스".equals(v6)){
				System.out.println("[TC_016] success");
				assertTrue(true);
				return;
			} else {
				System.out.println("[TC_016] failure : 카테고리 값 불일치");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_016] failure");
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
