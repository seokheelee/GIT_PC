package com.cj.pc;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import com.cj.util.SmartProperties;

/**
 * 
 * @author 조성주 Date : 2017-06-13
 * Subject : CJ Mall 운영 
 * Name : TC_63
 * Scenario : 결제 취소
 * Assertion : 결제취소 확인
 *
 */

public class P_063 {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String ID_1 = null;
	private String PW_1 = null;
	private String P_URL = null;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		ID_1 = sp.getProperty("ID_1");
		PW_1 = sp.getProperty("PW_1");
		P_URL = sp.getProperty("P_URL");
	}

	@Test
	public void p_063() throws Exception {
		driver.get(P_URL);
		driver.findElement(By.xpath(".//*[@id='header']/div[1]/div[5]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
		driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_1);
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		Thread.sleep(3000);
		System.out.println("로그인 성공");
		// 마이존 이동
		driver.findElement(By.xpath(".//*[@id='go_myzone']/span")).click();
		System.out.println("마이존 성공");
		// 취소 클릭
		driver.findElement(By.xpath(".//*[@id='content']/div/div[3]/div/div[2]/div[1]/div/div[2]/div/button")).click();
		Thread.sleep(3000);
		System.out.println("취소 성공");
		driver.findElement(By.xpath(".//*[@id='content']/div/form/fieldset/div[1]/div[2]/span/label")).click();
		new Select(driver.findElement(By.id("cancel_code"))).selectByVisibleText("배송관련 취소");
		driver.findElement(By.id("btn_ordercancel")).click();
		assertTrue(closeAlertAndGetItsText().matches("^선택하신 상품에 대해 주문취소 요청하시겠습니까[\\s\\S]$"));
		Thread.sleep(3000);
		// alert check
		System.out.println(driver.switchTo().alert().getText());
		if ("주문취소가 완료되었습니다.".equals(driver.switchTo().alert().getText())) {
			System.out.println("TC_63 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_63 FAIL");
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

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

}
