package com.cj.pc;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;

/**
 * 
 * @author 조성주 Date : 2017-06-13
 * Subject : CJ Mall 운영 
 * Name : TC_31
 * Scenario :메인화면 > 장바구니 버튼 선택
 * Assertion : 로그인 버튼 Text 체크
 *
 */

public class P_031 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private String P_URL = null;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		P_URL = sp.getProperty("P_URL");
	}

	@Test
	public void p_031() throws Exception {
		driver.get(P_URL);
		driver.findElement(By.xpath("//*[@id='header']/div[1]/div[5]/ul/li[3]/a")).click();
		System.out.println("장바구니 진입");
		Thread.sleep(3000);
		// 로그인 Text 체크

		if ("로그인".equals(driver.findElement(By.xpath("//*[@id='content']/div[1]/h2")).getText())) {
			System.out.println("TC_31 Pass");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_31 Fail");
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
