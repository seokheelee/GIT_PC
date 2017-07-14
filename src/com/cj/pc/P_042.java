package com.cj.pc;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cj.util.SmartProperties;

/**
 * 
 * @author 조성주 Date : 2017-06-13
 * Subject : CJ Mall 운영 
 * Name : TC_42
 * Scenario :TV 쇼핑 > 편성표 > 방송알림 신청 버튼 선택
 * Assertion : 로그인 버튼 Text 체크
 *
 */

public class P_042 {
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
	public void p_042() throws Exception {
		driver.get(P_URL);

		// TV쇼핑 진입
		driver.findElement(By.xpath(".//*[@id='header']/div[1]/div[4]/div[1]/ul/li[1]/a")).click();
		System.out.println("TV쇼핑 진입 성공");
		// 편성표 진입
		driver.findElement(By.xpath(".//*[@id='liveMenu']/li[2]/a")).click();
		System.out.println("편성표 진입 성공");
		// 편성표 진입
		driver.findElement(By.xpath(".//*[@id='scheduleDate']/li[7]/a/span[1]")).click();
		Thread.sleep(3000);
		System.out.println("상단 메뉴 다음날 클릭 성공");
		// 스크롤 내리기
		WebElement searchBtn = driver.findElement(By.xpath("//*[@id='quick_menu']/ul/li[2]/a"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		// 상품 진입
		boolean isExist1 = false;
		boolean isExist2 = false;
		boolean isExist3 = false;
			
		// 대표상품 가격란에 '상담신청상품' 체크 
		isExist1 = existElement(driver, By.xpath(".//*[@id='scheduleItem']/ul[1]/li/div/div/span"), "단일 상담신청 상품");
		isExist2 = existElement(driver, By.xpath(".//*[@id='scheduleItem']/ul[1]/li[1]/div/div/span"), "복수 상담신청 상품");
		if (isExist1 && driver.findElement(By.xpath(".//*[@id='scheduleItem']/ul[1]/li/div/div/span")).getText().equals("상담신청상품")) {
			System.out.println("상담신청상품 입니다. (단일)");
			assertTrue(true);
			return;
		}
		else{
			// 상담신청 체크 (복수상품)
			// 대표상품 가격란에 '상담신청상품' 체크
			if (isExist2 && driver.findElement(By.xpath(".//*[@id='scheduleItem']/ul[1]/li[1]/div/div/span")).getText()
					.equals("상담신청상품")) {
				System.out.println("상담신청상품 입니다. (복수)");
				assertTrue(true);
				return;
			}
			// 상담신청 상품이 아닐경우 else 진행
			else {
				System.out.println("상담신청상품 아닙니다. 계속 진행");
				Thread.sleep(3000);
			}
			
		}
		
		// 상품진입
		isExist3 = existElement(driver, By.xpath(".//*[@id='scheduleItem']/ul[1]/li/a[1]"), "상품진입");
		if (isExist3) {
			//복수상품일경우 (보통 이 옵션으로 모두 선택됨)
			driver.findElement(By.xpath(".//*[@id='scheduleItem']/ul[1]/li[1]/a[1]")).click();
			System.out.println("대표상품 선택 (복수상품)");
			Thread.sleep(3000);
		} else {
			//단일상품일경우
			driver.findElement(By.xpath(".//*[@id='scheduleItem']/ul[1]/li/a[1]")).click();
			System.out.println("대표상품 선택 (단일상품)");
			Thread.sleep(3000);
		}
		
		System.out.println("상품진입 성공");
		driver.findElement(By.xpath("//*[@id='content']/div[2]/div[1]/div[2]/div[1]/div/a")).click();
		System.out.println("방송알림 클릭 성공");
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		System.out.println("얼럿 확인");
		// 로그인 텍스트 확인
		if ("로그인".equals(driver.findElement(By.xpath("//*[@id='content']/div[1]/h2")).getText())) {
			System.out.println("TC_42 Pass");
			assertTrue(true);
			return;
		} else {
			System.out.println("TC_42 Fail");
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