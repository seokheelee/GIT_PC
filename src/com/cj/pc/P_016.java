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
 * Name : TC_16
 * Scenario : 간편회원 ID 로그인 하기
 * Assertion : PC : 로그아웃 Text 체크
 * 
 */    
public class P_016 {
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
    //sp.list(System.out);

	// System.out.println("ID_1 	= " + ID_1);

  }

  @Test
  public void p_016() throws Exception {
	// 로그인 > 통합회원
		driver.get(P_URL);
		driver.findElement(By.cssSelector("span.ico")).click();
		driver.findElement(By.xpath("//*[@id='id_input']")).clear();
		driver.findElement(By.xpath("//*[@id='id_input']")).sendKeys(ID_1);
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		System.out.println("로그인 완료");
    Thread.sleep(3000);
    // 얼럿창 확인
    if (" 로그아웃".equals(driver.findElement(By.xpath("//*[@id='header']/div[1]/div[5]/ul/li[1]")).getText())) {
        System.out.println("TC_16 Pass");
        assertTrue(true);
        return;
     } else {
        System.out.println("TC_16 Fail");
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
