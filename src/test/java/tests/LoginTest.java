package tests;

import base.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import org.testng.internal.MethodInheritance;
import pages.HomePage;

import java.lang.reflect.Method;

@Slf4j
public class LoginTest extends TestBase {
    HomePage homePage;
    @Test
    public void testLogin(Method method ) throws InterruptedException {
        log.info("performing login test");
        homePage = loginPage.perFormLogin();
        log.info("login successfull");
        log.info("current title is "+homePage.getTitle());
    }
}
