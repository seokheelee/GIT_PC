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
 * Name : TC_58
 * Scenario :최근본상품 > 쇼핑찜가기 버튼 선택 > ID / PW 입력 > 로그인
 * Assertion : 쇼핑찜 Text 체크
 *
 */

public class P_059 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private String ID_1 = null;
	private String PW_1 = null;
	private String P_URL = null;
	private String PRODUCT = null;
	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		ID_1 = sp.getProperty("ID_1");
		PW_1 = sp.getProperty("PW_1");
		P_URL = sp.getProperty("P_URL");
		PRODUCT = sp.getProperty("PRODUCT");
	}

	@Test
	public void p_059() throws Exception {
		driver.get(P_URL);

		// 상품진입
		driver.findElement(By.id("srh_keyword")).clear();
		driver.findElement(By.id("srh_keyword")).sendKeys(PRODUCT);
		driver.findElement(By.cssSelector("button._search")).click();
		driver.findElement(By.xpath("//*[@id='lst_cate_result']/li[1]/a")).click();
		System.out.println("상품진입 성공");
		// 최근본상품 클릭
		driver.findElement(By.xpath("//*[@id='quick_menu']/div[1]/div[1]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id='content']/div[2]/div[1]/div[2]/div[3]/div[1]/a")).click();
		Thread.sleep(3000);
		System.out.println("최근본상품 진입 성공");
		// alert check
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);
		System.out.println("얼럿 닫기 성공");

		// 로그인
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
		driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_1);
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		Thread.sleep(3000);
		System.out.println("로그인 성공");
		Thread.sleep(3000);
		// 일반 찜버튼 //*[@id="content"]/div[2]/div[1]/div[2]/div[2]/div[1]/a
		// 매진일 경우 찜 버튼 //*[@id="content"]/div[2]/div[1]/div[2]/div[3]/div[1]/a
		if ("찜".equals(
				driver.findElement(By.xpath("//*[@id='content']/div[2]/div[1]/div[2]/div[3]/div[1]/a")).getText())) {
			System.out.println("TC_59 Pass");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='go_myzone']")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='aside']/div/ul[1]/li[3]/ul/li[1]/a")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id='content']/div/div[1]/div[2]/ul/li/button/span")).click();
			Thread.sleep(5000);
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_59 Fail");
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