package pages;

import com.microsoft.playwright.Page;
import utils.Actions;
import utils.ConfigManager;

public class HomePage {

    public String homePageIcon = ".slds-icon-waffle";
    public Page page;
    ConfigManager configManager;
    Actions actions;

    public HomePage(Page page){
        this.page = page;
        this.configManager = new ConfigManager();
        this.actions = new Actions(this.page);
    }

    public String getTitle(){
        return this.page.title();
    }


}
