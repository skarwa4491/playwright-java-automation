package pages;

import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import utils.Actions;
import utils.ConfigManager;

@Slf4j
public class LoginPage {

    public String userNameField = "#username";
    public String passwordFiled = "#password";
    public String loginButton = "#Login";

    Page page;
    ConfigManager configManager;
    Actions actions;

    public LoginPage(Page page){
        this.page = page;
        this.configManager = new ConfigManager();
        this.actions = new Actions(this.page);
    }

    public HomePage perFormLogin() throws InterruptedException {
        String userName = configManager.get("username");
        String password = configManager.get("password");
        actions.type(this.userNameField, userName);
        log.info("typed username: "+ userName);
        actions.type(this.passwordFiled, password);
        log.info("typed password: "+ password);
        actions.doClick(this.loginButton);
        HomePage homePage = new HomePage(this.page);
        this.actions.waitForElement(".slds-icon-waffle" );
        return homePage;
    }
}
