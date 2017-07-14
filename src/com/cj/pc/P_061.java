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
 * Name : TC_61
 * Scenario : 바로방문ON 버튼 선택
 * Assertion : 바로방문 Text 체크
 *
 */

public class P_061 {
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
	public void p_061() throws Exception {
		driver.get(P_URL);
		
		//바로방문 이동
	    driver.findElement(By.xpath("//*[@id='quick_menu']/ul/li[1]/a")).click();
	    Thread.sleep(3000);
	    System.out.println("바로방문 진입");
	    //바로방문 확인
	    Thread.sleep(3000);
		if ("바로방문".equals(
				driver.findElement(By.xpath("//*[@id='exhibitionTitle']")).getText())) {
			System.out.println("TC_61 Pass");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_61 Fail");
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
