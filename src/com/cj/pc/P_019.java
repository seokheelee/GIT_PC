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
 * Date : 2017-06-12
 * Subject : CJ Mall 운영 
 * Name : TC_19
 * Scenario : 부당고객 ID 로그인 하기 > 임의의 상품상세 > 구매하기
 * Assertion : 상담원에게 주문가능 방법을 확인하시기 바랍니다(CJmall 고객센터 1644-2525) Text 체크
 *
 */ 
public class P_019 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private String ID_4 = null;
	private String PW_4 = null;
	private String P_URL = null;
	
	 
	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		SmartProperties sp = SmartProperties.getInstance();
		ID_4 = sp.getProperty("ID_4");
		PW_4 = sp.getProperty("PW_4");
		P_URL = sp.getProperty("P_URL");
		// sp.list(System.out);

		// System.out.println("ID_1 = " + ID_1);

	}

	@Test
	public void p_019() throws Exception {
		// 로그인 > 부당회원
		driver.get(P_URL);
		driver.findElement(By.cssSelector("span.ico")).click();
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
	    driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_4);
	    driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
	    driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_4);
	    driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		System.out.println("로그인");
		Thread.sleep(3000);
	    driver.findElement(By.id("srh_keyword")).clear();
	    driver.findElement(By.id("srh_keyword")).sendKeys("가습기");
	    driver.findElement(By.cssSelector("button._search")).click();
	    driver.findElement(By.cssSelector("a.link_product > span.img")).click();
	    driver.findElement(By.linkText("바로구매")).click();
	    System.out.println("상품진입");
		
		Thread.sleep(3000);
		// 얼럿창 확인
		System.out.println(driver.switchTo().alert().getText());
		if ("상담원에게 주문가능 방법을 확인하시기 바랍니다(CJmall 고객센터 1644-2525)".equals(driver.switchTo().alert().getText())) {
	        System.out.println("TC_19 PASS");
	        assertTrue(true);
	        return;
	     } else {
	         System.out.println("TC_19 FAIL");
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
