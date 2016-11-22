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
 * Name : TC_051
 * Scenario : 1차 검색 후 "결과 내 재검색" 시 결과 정상 출력 확인
 * Assertion : "검색결과 ...개" 체크
 * Update : 불필요한 코드의 제거 (2016.11.07)
 *
 */

public class Live_TC051 {
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
	public void TC051() throws Exception {
		try{
			WebDriverWait wait = null;
			
			driver.manage().window().maximize();

			// 메인 페이지 요청
			driver.get(baseUrl + "/index_tab1.jsp");
			System.out.println("메인 페이지 요청");
			
			// 메인 페이지 뜨고, 검색창 객체가 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='srch_disp_value']")));
			System.out.println("메인 페이지 뜨고, 검색창 객체가 뜰 때까지 기다림");
			
			// 검색창 클릭
			driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).click();
			driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).clear();
			// 검색어 "TV" 입력
			driver.findElement(By.xpath(".//*[@id='srch_disp_value']")).sendKeys(word_tc051_01);
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/form/fieldset/div/button")).click();
			System.out.println(word_tc051_01 + "검색");
						
			// 검색결과창 Text
			String v1 = driver.findElement(By.xpath(".//*[@id='search_result_text']")).getText();
			
			// 검색결과창 객체가 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='srch_disp_value']")));
			System.out.println("검색결과창 객체가 뜰 때까지 기다림");
			
			// 검색결과 Text
			if(!"".equals(driver.findElement(By.xpath(".//*[@id='search_result_text']/strong/a")).getText())){
				// 결과 내 재 검색창 클릭
				driver.findElement(By.xpath(".//*[@id='srch_value']")).click();
				driver.findElement(By.xpath(".//*[@id='srch_value']")).clear();
				System.out.println("결과 내 재 검색창 클릭");
				
				// 검색어 "UHD" 입력
				driver.findElement(By.xpath(".//*[@id='srch_value']")).sendKeys(word_tc051_02);
				driver.findElement(By.xpath(".//*[@id='reText']/fieldset/button")).click();
				System.out.println("'UHD' 검색");
				
				// 검색결과창 객체가 뜰 때까지 기다림.
				wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='srch_disp_value']")));
				System.out.println("검색결과창 객체가 뜰 때까지 기다림");
				
				Thread.sleep(3000);
				
				// 재 검색결과창 Text
				String v2 = driver.findElement(By.xpath(".//*[@id='search_result_text']")).getText();
				boolean isExist3 = existElement(driver, By.xpath(".//*[@id='search_result_text']/strong/a[2]"), "재 검색결과창");
				if(isExist3 && v1!=v2){
					// UHD 포함하는지 확인
					if(v2.contains(word_tc051_02)){
						System.out.println("[TC_051] success");
						assertTrue(true);
						return;
					} else {
						System.out.println("[TC_051] failure : 'UHD' Text 포함 불일치");
						assertTrue(false);
					}
				} else {
					System.out.println("[TC_051] failure : 검색어 & 재검색어 일치");
					assertTrue(false);
				}
			} else {
				System.out.println("[TC_051] failure : 검색결과 null...");
				assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_051] failure");
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
