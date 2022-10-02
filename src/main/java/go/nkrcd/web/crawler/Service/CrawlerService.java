package go.nkrcd.web.crawler.Service;

import go.nkrcd.web.main.model.Company;
import go.nkrcd.web.main.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CrawlerService {
    @Autowired
    CompanyRepository companyRepository;

    @Value("${crawler.url}")
    private String URL;

    @Value("${crawler.fin.url}")
    private String finURL;

    @Value("${crawler.address.url}")
    private String addressURL;

    @Value("${crawler.driver}")
    private String driver;

    /*
     * 회사 목록 크롤링
     */
    public boolean list() {
        List<Company> result = new ArrayList<>();

        log.info("####Crawler START####");

        Path path = Paths.get(driver);
        System.setProperty("webdriver.chrome.driver", path.toString());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");   // 팝업 안띄움
        options.addArguments("headless");   // 브라우저 안띄움
        options.addArguments("--disable-gpu");  // gpu 비활성화
        options.addArguments("--blink-settings=imagesEnabled=false");   // 이미지 다운 안받음

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));    // 드라이버가 실행된 후 10초 기다림

        try {
            driver.get(URL);

            webDriverWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.section_group > section"))
                    //cssSelector로 선택한 부분이 로딩횔때까지 기다림
            );

            List<WebElement> contents = driver.findElements(By.cssSelector("div.section_group > section"));
            if (contents.size() > 0) {
                for (WebElement content : contents) {
                    String name = content.findElement(By.cssSelector("div > div > dl.content_col2_3.cominfo > dt > a")).getText().replace("(주)", "").replace("(유)", "");
                    String address = getAddress(name);
                    String logo = content.findElement(By.cssSelector("div > div > div > a > img")).getAttribute("src");
                    result.add(new Company(name, address, logo));
                }
            }

            WebElement pageNextWebElement = driver.findElement(By.cssSelector("a.btn_pgnext"));
            pageNextWebElement.click();

            String prev = URL;
            while (!pageNextWebElement.getAttribute("href").contains(finURL)) {
                System.out.println(driver.getCurrentUrl());

                if(!prev.contains(driver.getCurrentUrl())) {
                    driver.navigate().to(driver.getCurrentUrl());
                    prev = driver.getCurrentUrl();

                    webDriverWait.until(
                            ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.section_group > section"))
                    );

                    contents = driver.findElements(By.cssSelector("div.section_group > section"));
                    if (contents.size() > 0) {
                        for (WebElement content : contents) {
                            String name = content.findElement(By.cssSelector("div > div > dl.content_col2_3.cominfo > dt > a")).getText().replace("(주)", "").replace("(유)", "");
                            String address = getAddress(name);
                            String logo = content.findElement(By.cssSelector("div > div > div > a > img")).getAttribute("src");
                            result.add(new Company(name, address, logo));
                        }
                    }
                }

                pageNextWebElement = driver.findElement(By.cssSelector("a.btn_pgnext"));
                pageNextWebElement.click();
            }

            /*
             * 마지막 페이지 크롤링
             */
            System.out.println(driver.getCurrentUrl());
            driver.navigate().to(driver.getCurrentUrl());
            contents = driver.findElements(By.cssSelector("div.section_group > section"));
            if (contents.size() > 0) {
                for (WebElement content : contents) {
                    String name = content.findElement(By.cssSelector("div > div > dl.content_col2_3.cominfo > dt > a")).getText().replace("(주)", "").replace("(유)", "");
                    String address = getAddress(name);
                    String logo = content.findElement(By.cssSelector("div > div > div > a > img")).getAttribute("src");
                    result.add(new Company(name, address, logo));
                }
            }

        } catch (Exception e) {
            log.error("Crawler ERROR 발생");
            return false;

        } finally {
//            driver.close();   // 현재 탭만 종료
            driver.quit();  // 모든 탭 종료

           log.info("####Crawler END####");

            /*
             * DB에 저장
             */
            for (Company c: result) {
                companyRepository.save(new Company(companyRepository.findCIdNext(), c));
            }
        }
        return true;
    }

    /*
     *  회사 주소 크롤링
     */
    public String getAddress(String str) {
        String address = null;

        log.info("####Address Crawler START####");

        Path path = Paths.get(driver);
        System.setProperty("webdriver.chrome.driver", path.toString());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");   // 팝업 안띄움
//        options.addArguments("headless");   // 브라우저 안띄움
        options.addArguments("--disable-gpu");  // gpu 비활성화
        options.addArguments("--blink-settings=imagesEnabled=false");   // 이미지 다운 안받음

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));    // 드라이버가 실행된 후 10초 기다림

        try {
            driver.get(addressURL+str);

            webDriverWait.until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("#company_info_list > div.content > div:nth-child(1)"))
            );

            WebElement webElement = driver.findElement(By.cssSelector("#company_info_list > div.content > div:nth-child(1)"));
            String name = webElement.findElement(By.cssSelector("h2 > a")).getText().replace("(주)", "").replace("(유)", "");
            if (name.equals(str)) {
                List<WebElement> contents = webElement.findElements(By.cssSelector("div.corp_info > dl"));
                if (contents.size() > 0) {
                    for (WebElement content : contents) {
                        if(content.findElement(By.cssSelector("dt")).getText().equals("기업주소"))
                            address = content.findElement(By.cssSelector("dd")).getText();
                    }
                }
            }
        } catch (Exception e) {
            log.error("Address Crawler ERROR 발생");

        } finally {
            driver.quit();  // 모든 탭 종료
            log.info("####Address Crawler END####");
        }
        return address;
    }
}
