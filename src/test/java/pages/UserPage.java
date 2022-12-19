package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class UserPage extends BaseClass {

    By locatorBtnUsuario= By.xpath("//button[@data-testid='user-widget-link']");

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public String obtenerUsuario(){
        return obtenerTexto(esperarAElementoWeb(locatorBtnUsuario));
    }
}
