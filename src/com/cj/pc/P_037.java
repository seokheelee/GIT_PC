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
 * Name : TC_37
 * Scenario :임의의 상품상세 > 방송알림신청  버튼 선택
 * Assertion : 방송알림 신청 Text 체크
 *
 */

public class P_037 {
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
	public void p_034() throws Exception {
		driver.get(P_URL);
		
		//로그인
		driver.findElement(By.xpath("//*[@id='header']/div[1]/div[5]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
	    driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_1);
	    driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
	    driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);
	    driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
	    System.out.println("로그인 진입 성공");
	    Thread.sleep(3000);
	    //TV쇼핑 진입
		driver.findElement(By.xpath(".//*[@id='header']/div[1]/div[4]/div[1]/ul/li[1]/a")).click();
		System.out.println("TV쇼핑 진입 성공");
		Thread.sleep(3000);

		boolean isExist1 = false;

		// 상담신청 체크
		isExist1 = existElement(driver, By.xpath(".//*[@id='liveRepItem']/a[2]"), "상담신청");
		if (isExist1 == driver.findElement(By.xpath(".//*[@id='liveRepItem']/a[2]")).getText().equals("상담신청")) {
			System.out.println("상담신청상품 입니다.");
			assertTrue(true);
			return;
		}

		// 상품 진입
		driver.findElement(By.xpath("//*[@id='liveRepItem']/a[1]")).click();
		System.out.println("임의상품 진입 성공");
		Thread.sleep(3000);
		//방송알림신청
		driver.findElement(By.xpath("//*[@id='content']/div[2]/div[1]/div[2]/div[1]/div/a")).click();
		Thread.sleep(3000);
		//System.out.println(driver.findElement(By.xpath("//*[@id='_alarmy_regist_layer']/div/div[1]/h1")).getText());
		// 방송알림 신청 텍스트 확인
		if ("방송알림 신청".equals(
				driver.findElement(By.xpath("//*[@id='_alarmy_regist_layer']/div/div[1]/h1")).getText())) {
			System.out.println("TC_37 Pass");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_37 Fail");
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
