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
 * Name : TC_60
 * Scenario :앱다운로드 버튼 선택
 * Assertion : CJmall 앱다운로드 Text 체크
 *
 */

public class P_060 {
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
	public void p_060() throws Exception {
		driver.get(P_URL);

		// 앱 바로가기 이동
		driver.findElement(By.xpath("//*[@id='quick_menu']/ul/li[2]/a")).click();
		Thread.sleep(3000);
		System.out.println("앱바로가기 이동 성공");
		// CJmall 앱다운로드페이스북트위터 텍스트 확인
		if ("CJmall 앱다운로드".equals(driver.findElement(By.xpath("//*[@id='exhibitionTitle']")).getText())) {
			System.out.println("TC_60 Pass");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_60 Fail");
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
