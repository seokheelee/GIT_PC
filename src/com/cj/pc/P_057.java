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
 * Name : TC_57
 * Scenario :최근본상품 > 쇼핑찜가기 버튼 선택
 * Assertion : 로그인 버튼 Text 체크
 *
 */

public class P_057 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private String P_URL = null;
	private String PRODUCT = null;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		P_URL = sp.getProperty("P_URL");
		PRODUCT = sp.getProperty("PRODUCT");
	}

	@Test
	public void p_057() throws Exception {
		driver.get(P_URL);

		// 상품진입
		driver.findElement(By.id("srh_keyword")).clear();
		driver.findElement(By.id("srh_keyword")).sendKeys(PRODUCT);
		driver.findElement(By.cssSelector("button._search")).click();
		driver.findElement(By.xpath("//*[@id='lst_cate_result']/li[1]/a")).click();
		Thread.sleep(3000);
		System.out.println("상품진입 성공");
		// 최근본상품 클릭
		driver.findElement(By.xpath("//*[@id='quick_menu']/div[1]/div[1]/ul/li[1]/a")).click();
		System.out.println("찜버튼 클릭 성공");
		// 찜버튼 클릭 일반 .//*[@id='content']/div[2]/div[1]/div[2]/div[2]/div[1]/a
		// 찜버튼 클릭 매진 .//*[@id='content']/div[2]/div[1]/div[2]/div[3]/div[1]/a
		driver.findElement(By.xpath("//*[@id='content']/div[2]/div[1]/div[2]/div[3]/div[1]/a")).click();
		Thread.sleep(3000);
		// alert check
		if ("로그인 후 찜이 가능합니다.".equals(driver.switchTo().alert().getText())) {
			System.out.println("TC_57 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_57 FAIL");
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
