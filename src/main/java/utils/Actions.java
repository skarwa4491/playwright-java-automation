package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Actions {

    Page page;
    public Actions(Page page){
        this.page = page;
    }

    public boolean doClick(String locator){
        try {
            this.page.locator(locator).click();
            return true;
        } catch (Exception e) {
            log.error("unable to click on locator "+locator);
            return false;
        }
    }

    public boolean type(String locator , String text){
        try {
            this.page.locator(locator).fill(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean waitForElement(String locator , double timeout){
        try {
            page.locator(locator).waitFor(
                    new Locator
                            .WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(timeout));
            return true;
        } catch (Exception e) {
            log.error(String.format("element %s not visibl" , locator));
            return false;
        }
    }

    public boolean waitForElement(String locator){
        return waitForElement(locator , 60000);
    }
}
