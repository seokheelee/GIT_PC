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
 * Name : TC_1
 * Scenario : 로그인 버튼 선택 > ID 미입력 / PW 미입력 > 로그인 버튼 선택
 * Assertion : '아이디를 입력해주세요. 알럿 Text 체크
 *
 */  
public class P_001 { 
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
	public void p_001() throws Exception {
		driver.get(P_URL);
		
		boolean isExist = false;
		//팝업닫기
		isExist = existElement(driver, By.xpath(".//*[@id='popup_spot']/div/div/div/div[2]/div[2]/button"), "닫기");
		if (isExist) {
			driver.findElement(By.xpath(".//*[@id='popup_spot']/div/div/div/div[2]/div[2]/button")).click();
			System.out.println("닫기버튼 클릭");
		} else {
			System.out.println("팝업 없음");
		}
		System.out.println("팝업닫기");
		
		// 로그인 버튼 클릭
		driver.findElement(By.xpath(".//*[@id='header']/div[1]/div[5]/ul/li[1]/a/span")).click();
		System.out.println("로그인 버튼 클릭");

		Thread.sleep(3000);

		// 로그인 Text 체크
		if ("로그인".equals(driver.findElement(By.xpath(".//*[@id='content']/div[1]/h2")).getText())) {
			System.out.println("TC_1 Pass");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_1 Fail");
			assertTrue(false);
		}
		System.out.println("텍스트 체크");
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

			return false;
		}
		return true;
	}
	 
}
