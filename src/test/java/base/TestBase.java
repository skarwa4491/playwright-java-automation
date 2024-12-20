package base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.LoginPage;
import utils.Actions;
import utils.ConfigManager;

import java.util.List;

@Slf4j
public class TestBase extends ConfigManager {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    public LoginPage loginPage;

    @BeforeSuite(alwaysRun = true)
    public void setConfig() {
        setTestData();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() throws Exception {
        this.launchBrowser();
        loginPage = new LoginPage(this.page);
        this.navigate();
    }

    public void launchBrowser() throws Exception {

        if (this.playwright == null) {
            log.info("Initializing playwright");
            this.playwright = Playwright.create();
        }

        boolean headless = Boolean.valueOf(get("headless"));
        String browserType = get("browser");
        switch (browserType.toLowerCase()) {
            case "chromium":
                this.browser = this.playwright.chromium().launch(
                        new BrowserType
                                .LaunchOptions()
                                .setHeadless(headless)
                                .setArgs(List.of("--start-maximized"))
                );
                break;
            case "chrome":
                this.browser = this.playwright.chromium().launch(
                        new BrowserType
                                .LaunchOptions()
                                .setChannel("chrome")
                                .setHeadless(headless)
                                .setArgs(List.of("--start-maximized"))
                );
                break;
            case "firefox":
                this.browser = this.playwright.firefox().launch(
                        new BrowserType
                                .LaunchOptions()
                                .setChannel("chrome")
                                .setHeadless(headless)
                                .setArgs(List.of("--start-maximized"))
                );
                break;
            default:
                throw new Exception("Browser not supported");
        }
        log.info(String.format("launched browser %s ",browser));
        this.context = this.browser.newContext(
                new Browser
                        .NewContextOptions()
                        .setViewportSize(null)
        );
        this.page = this.context.newPage();
        this.page.setDefaultTimeout(60000); // 1 min
        this.page.setDefaultNavigationTimeout(60000); // 1 min
    }

    public void navigate() throws InterruptedException {
        String url = get("url");
        log.info("navigating to "+url);
        this.page.navigate(url);
        this.page.waitForLoadState(LoadState.LOAD);
    }
}
