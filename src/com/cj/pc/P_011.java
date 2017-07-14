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
 * Name : TC_9
 * Scenario : 로그인 > 비밀번호 찾기
 * Assertion : 비밀번호 찾기 Text 체크
 *
 */    
public class P_011 {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  private String ID_1 = null;
  private String P_URL = null;
  
  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  
    SmartProperties sp = SmartProperties.getInstance();
    ID_1 = sp.getProperty("ID_1");
    P_URL = sp.getProperty("P_URL");
    //sp.list(System.out);

	// System.out.println("ID_1 	= " + ID_1);

  }

  @Test
  public void p_011() throws Exception {
	// 로그인 > 비밀번호 찾기
	driver.get(P_URL);
	driver.findElement(By.cssSelector("span.ico")).click();
    driver.findElement(By.xpath(".//*[@id='findPassword']")).click();
    driver.findElement(By.xpath(".//*[@id='memberId']")).clear();
    driver.findElement(By.xpath(".//*[@id='memberId']")).sendKeys(ID_1);
    driver.findElement(By.xpath(".//*[@id='idFindBtn']")).click();
    System.out.println("로그인성공");
    Thread.sleep(3000);
    // 얼럿창 확인
    if ("고객님의 아이디\njsjjoo88확인되었습니다.".equals(driver.findElement(By.xpath("//*[@id='txt_notice']")).getText())) {
        System.out.println("TC_11 Pass");
        assertTrue(true);
        return;
     } else {
        System.out.println("TC_11 Fail");
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
