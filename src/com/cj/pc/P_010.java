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
 * Name : TC_10
 * Scenario : 로그인 > 비밀번호 찾기
 * Assertion : 비밀번호 찾기 Text 체크
 *
 */ 
public class P_010 {
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
  public void p_0010() throws Exception {
	// 로그인 > 비밀번호 찾기
	driver.get(P_URL);
    driver.findElement(By.cssSelector("span.ico")).click();
    Thread.sleep(3000);
    //얼럿창노출
    System.out.println(driver.findElement(By.xpath(".//*[@id='findId']")).getText());
    if ("비밀번호 찾기".equals(driver.findElement(By.xpath(".//*[@id='findPassword']")).getText())) {
        System.out.println("TC_10 Pass");
        assertTrue(true);
        return;
     } else {
        System.out.println("TC_10 Fail");
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
