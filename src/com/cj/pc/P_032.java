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
 * Name : TC_33
 * Scenario :임의의 상품 > 장바구니 버튼 선택 
 * Assertion : 장바구니 Text 체크
 *
 */

public class P_032 {
	private WebDriver driver;
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
	public void p_032() throws Exception {
		driver.get(P_URL);

		// 임의상품진입
		driver.findElement(By.id("srh_keyword")).clear();
		driver.findElement(By.id("srh_keyword")).sendKeys("가습기");
		driver.findElement(By.cssSelector("button._search")).click();
		System.out.println("임의상품 진입 성공");
		Thread.sleep(3000);
		// 장바구니 담기 클릭
		driver.findElement(By.xpath("//*[@id='lst_cate_result']/li[1]/a/span[1]")).click();
		driver.findElement(By.xpath("//*[@id='content']/div[2]/div[1]/div[2]/div[2]/div[2]/a")).click();
		System.out.println("장바구니 담기 성공");
		Thread.sleep(3000);
		// 얼럿닫기
		Alert alert = driver.switchTo().alert();
		alert.accept();
		System.out.println("얼럿닫기 성공");
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
		driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_1);
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		System.out.println("로그인 성공");
		// 상단 로고 선택
		Thread.sleep(3000);
		// 장바구니 진입
		driver.findElement(By.xpath(".//*[@id='header']/div[1]/div[5]/ul/li[3]/a")).click();
		Thread.sleep(3000);
		System.out.println("장바구니 진입 성공");
		if ("장바구니".equals(driver.findElement(By.xpath("//*[@id='content']/div/h2")).getText())) {
			System.out.println("TC_32 Pass");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_32 Fail");
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
