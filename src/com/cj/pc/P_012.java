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
 * Name : TC_12
 * Scenario : 로그인 > 비밀번호 찾기 > 아이디 잘못 입력 > 조회 버튼 선택
 * Assertion : '정확한 아이디를 입력해주십시오.
 * 
 */  
public class P_012 {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();
  private String P_URL = null;
  private String Text = "정확한 아이디를 입력해주십시오. CJmall약관동의를 하지 않은 CJ ONE 아이디일 경우, CJ ONE 홈페이지에서 비밀번호 찾기를 해주십시오.";
    
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
  public void p_012() throws Exception {
	// 로그인 > 비밀번호 찾기 > 아이디 잘못 입력 > 조회 버튼 선택
	driver.get(P_URL);
    driver.findElement(By.cssSelector("span.ico")).click();
    driver.findElement(By.xpath(".//*[@id='findPassword']")).click();
    driver.findElement(By.xpath(".//*[@id='memberId']")).clear();
    driver.findElement(By.xpath(".//*[@id='memberId']")).sendKeys("sjfsdjf14");
    driver.findElement(By.xpath(".//*[@id='idFindBtn']")).click();
    System.out.println("잘못된 아이디 입력후 조회 클릭 성공");
    Thread.sleep(3000);
    // 얼럿창 확인
    System.out.println(driver.switchTo().alert().getText());
    if (Text.equals(driver.switchTo().alert().getText())) {
        System.out.println("TC_12 Pass");
        assertTrue(true);
        return;
     } else {
        System.out.println("TC_12 Fail");
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
