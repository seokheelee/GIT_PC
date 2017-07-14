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
 * @author 조성주
 * Date : 2017-06-13
 * Subject : CJ Mall 운영 
 * Name : TC_62
 * Scenario : 임의의 상품 무통장 입금 으로 결제하기
 * Assertion : 결제완료 Text 체크
 *
 */ 

public class P_062 {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private String ID_1 = null;
	private String PW_1 = null;
	private String PRODUCT = null;
	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		baseUrl = "http://display.cjmall.com/p/homeTab/main?hmtabMenuId=000002&rPIC=Oclock&pic=GNBA|bi";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		ID_1 = sp.getProperty("ID_1");
		PW_1 = sp.getProperty("PW_1");
		PRODUCT = sp.getProperty("PRODUCT");
	}

	@Test
	public void p_062() throws Exception {
		// 로그인
		driver.get(baseUrl + "/p/homeTab/main?hmtabMenuId=000002&rPIC=Oclock");
		driver.findElement(By.cssSelector("span.ico")).click();
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
		driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_1);
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		System.out.println("로그인 성공");
		Thread.sleep(3000);
		// 상품진입
		driver.findElement(By.id("srh_keyword")).clear();
		driver.findElement(By.id("srh_keyword")).sendKeys(PRODUCT);
		driver.findElement(By.cssSelector("button._search")).click();
		driver.findElement(By.cssSelector("a.link_product > span.img")).click();
		driver.findElement(By.linkText("바로구매")).click();
		System.out.println("상품진입 성공");
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='order_payment']/fieldset/div[4]/span/label")).click();
		driver.findElement(By.xpath(".//*[@id='_buy']")).click();
		System.out.println("바로구매 클릭 성공");
		Thread.sleep(3000);
		// text check
		if ("주문완료".equals(driver.findElement(By.xpath(".//*[@id='content']/div/h2[1]")).getText())) {
			System.out.println("TC_62 PASS");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_62 FAIL");
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