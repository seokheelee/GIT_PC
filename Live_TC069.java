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
 * Date : 2016-11-08 
 * Subject : CJ Mall 운영 
 * Name : TC_069
 * Scenario : 로그인 상태에서 상품을 장바구니에 담을 경우 장바구니에 담긴 상품 열람 여부 질의 팝업노출 확인
 * Assertion : 팝업 메시지 확인
 * Update : 불필요한 코드의 제거 (2016.11.08)
 *
 */

public class Live_TC069 {
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
	public void TC069() throws Exception {
		try {
			WebDriverWait wait = null;

			driver.manage().window().maximize();
			
			// 메인 페이지 요청
			driver.get(baseUrl + "/index_tab1.jsp");
			System.out.println("메인페이지 요청");
			
			// 메인 페이지 뜨고, 로그인 객체가 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span")));
			System.out.println("로그인 객체가 뜰 때까지 기다림");
			
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span")).click();

			// 로그인 창이 뜰 때까지 기다림.
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='mid_1']")));

			driver.findElement(By.xpath(".//*[@id='mid_1']")).click();
			driver.findElement(By.xpath(".//*[@id='mid_1']")).clear();
			driver.findElement(By.xpath(".//*[@id='mid_1']")).sendKeys(userId);
			driver.findElement(By.xpath(".//*[@id='mpass']")).clear();
			driver.findElement(By.xpath(".//*[@id='mpass']")).sendKeys(passwd);
			
			driver.findElement(By.xpath(".//*[@id='login_area']/form/div/div/div[2]/input")).click();

			// 로그인이 되고 메인화면이 뜰 때까지 기다림
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[1]/a/span"), "로그아웃"));
			System.out.println("로그인이 되고 메인화면이 뜰 때까지 기다림");

			// 장바구니 초기화(장바구니 가능 수량 1개인 상품이 있어, 재빌드시 에러 반환하는 경우가 있음)			
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/div[2]/ul/li[4]/a")).click();
			System.out.println("장바구니 클릭");
			
			// 장바구니 객체가 뜰 때까지 기다림
			wait = new WebDriverWait(driver, waitTime);			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='cjm_container']/div/div[2]/form/table/tbody/tr/td")));
			System.out.println("장바구니 객체가 뜰 때까지 기다림");
			
			if ("0원".equals(driver.findElement(By.xpath(".//*[@id='total_ord_amt']")).getText())){
				System.out.println("장바구니에 담긴 상품 없음 메인 화면으로 돌아감");					
				
			} else {
				
				driver.findElement(By.xpath(".//*[@id='cjm_container']/div/div[2]/div[2]/div/button[2]")).click();
				System.out.println("'선택상품 삭제' 버튼 클릭");
				
				Thread.sleep(3000);
				
				driver.switchTo().alert().accept();
				
				Thread.sleep(3000);
			}
			
			// 메인 페이지로 이동			
			driver.findElement(By.xpath(".//*[@id='cjo_wrap']/div[2]/div/div/h1/a")).click();
			System.out.println("메인 페이지로 이동");
						
			// 검색창 뜰 때까지 기다림
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='srch_disp_value']")));
			System.out.println("검색창 뜰 때까지 기다림");

		    
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
			driver.findElement(By.xpath(".//*[@id='list_wrap']/ul/li[1]/a/span[1]/img")).click(); 
			System.out.println("검색된 상품중 임의의 상품 클릭");

			Set<String> allWindows2 = driver.getWindowHandles();
		    for(String curWindow2 : allWindows2){
		        driver.switchTo().window(curWindow2);
		    }

		    // 장바구니 버튼 기다림
			wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='frmContent']/div/div[3]/a[2]/img | .//*[@id='frmContent']/div/div[3]/div/a/img")));
			System.out.println("장바구니 버튼 기다림");
			
			// 장바구니 버튼 클릭
			driver.findElement(By.xpath(".//*[@id='frmContent']/div/div[3]/a[2]/img | .//*[@id='frmContent']/div/div[3]/div/a/img")).click(); 
			System.out.println("장바구니 버튼 클릭");
			
			Thread.sleep(3000);
			
	  		// Alert text 체크
			System.out.println("Alert text is: " +driver.switchTo().alert().getText());
			System.out.println(driver.switchTo().alert().getText());
			
			if ("장바구니에 상품이 담겼습니다.\n지금 확인하시겠습니까?".equals(driver.switchTo().alert().getText())) {
				System.out.println("[TC_069] success");
				assertTrue(true);
				return;
			} else {
		    	System.out.println("[TC_069] failure : 'Alert Text' 불일치");
				assertTrue(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_069] failure");
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
