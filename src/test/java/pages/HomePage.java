package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class HomePage extends BaseClass {

    By locatorBtnResgistrarte = By.xpath("//button[contains(text(),'Registrarte')]");
    By locatorBtnIniciarSession= By.xpath("//button[@data-testid='login-button']");
    By locatorBtnBuscar= By.xpath("//span[contains(text(),'Buscar')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void irAResgistrarte(){
        click(esperarAElementoWeb(locatorBtnResgistrarte));
    }

    public void irAIniciarSession(){
        click(esperarAElementoWeb(locatorBtnIniciarSession));
    }

    public void irABuscar(){
        click(esperarAElementoWeb(locatorBtnBuscar));
    }
}
