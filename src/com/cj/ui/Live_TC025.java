package com.cj.ui;

import org.junit.*;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.util.SmartProperties;

/**
 * 
 * @author SeokheeLee Date : 2016-11-07 Subject : CJ Mall 운영 Name : TC_025
 *         Scenario : TV쇼핑 상단 카테고리 중 "방송 알리미" 카테고리 선택시 방송 알리미 화면 이동 확인 Assertion
 *         : "TV쇼핑>방송알리미" Text 체크 Update : 불필요한 코드의 제거 (2016.11.07)
 *
 */

public class Live_TC025 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String ID_1 = null;
	private String ID_2 = null;
	private String ID_3 = null;
	private String ID_4 = null;
	private String ID_5 = null;
	private String PW_1 = null;
	private String PW_2 = null;
	private String PW_3 = null;
	private String PW_4 = null;
	private String PW_5 = null;
	private String P_URL = null;
	private String M_URL = null;
	private String NAME = null;
	private String BIRTH = null;
	private long waitTime = 50;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		ID_1 = sp.getProperty("ID_1");
		ID_2 = sp.getProperty("ID_2");
		ID_3 = sp.getProperty("ID_3");
		ID_4 = sp.getProperty("ID_4");
		ID_5 = sp.getProperty("ID_5");
		PW_1 = sp.getProperty("PW_1");
		PW_2 = sp.getProperty("PW_2");
		PW_3 = sp.getProperty("PW_3");
		PW_4 = sp.getProperty("PW_4");
		PW_5 = sp.getProperty("PW_5");
		P_URL = sp.getProperty("P_URL");
		M_URL = sp.getProperty("M_URL");
		NAME = sp.getProperty("NAME");
		BIRTH = sp.getProperty("BIRTH");
	}

	@Test
	public void TC025() throws Exception {
		try {
			WebDriverWait wait = null;

			// 메인 페이지 요청
			driver.get(P_URL);
			System.out.println("메인 페이지 요청");
			Thread.sleep(3000);

			boolean isExist = false;
			isExist = existElement(driver, By.xpath(".//*[@id='popup_spot']/div/div/div/div[2]/div[2]/button"), "");
			if (isExist) {
				
				driver.findElement(By.xpath(".//*[@id='popup_spot']/div/div/div/div[2]/div[2]/button")).click();
				System.out.println("닫기 버튼");
			}
			Thread.sleep(3000);
			System.out.println("그냥 종료");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[TC_025] failure");
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
