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
 * Name : TC_14
 * Scenario : 로그인 > 회원가입 선택
 * Assertion : '회원가입 Text 체크
 * 
 */  
public class P_014 {
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
    //sp.list(System.out);

	// System.out.println("ID_1 	= " + ID_1);

  }

  @Test
  public void p_014() throws Exception {
	// 로그인 > 회원가입 선택
	driver.get(P_URL);
    driver.findElement(By.cssSelector("span.ico")).click();
    driver.findElement(By.xpath(".//*[@id='register']")).click();
    
    Thread.sleep(3000);
    // 얼럿창 확인
    if ("통합 회원가입".equals(driver.findElement(By.xpath(".//*[@id='registerCjOne']")).getText())) {
        System.out.println("TC_14 Pass");
        assertTrue(true);
        return;
     } else {
        System.out.println("TC_14 Fail");
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
