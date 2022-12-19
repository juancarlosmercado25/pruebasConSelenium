package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class LoginPage extends BaseClass {

    By locatorTxtUsuario = By.xpath("//input[@id='login-username']");
    By locatorTxtContrasena = By.xpath("//input[@id='login-password']");
    By locatorBtnIniciarSession= By.xpath("//button[@id='login-button']");
    By locatorBtnFacebookSession = By.xpath("//button[@data-testid='facebook-login']");
    By locatorBtnSessionGoogle= By.xpath("//button[@data-testid='google-login']");
    By locatorLblCotrasenaUsuarioFallido = By.xpath("//span[contains(text(),'Nombre de usuario o contraseña incorrectos.')]");


    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void iniciarSession(String correo, String contrasena){
        agregarTexto(esperarAElementoWeb(locatorTxtUsuario),correo);
        agregarTexto(esperarAElementoWeb(locatorTxtContrasena), contrasena);
        click(esperarAElementoWeb(locatorBtnIniciarSession));
    }

    public void irAFacebookSession(){
        click(esperarAElementoWeb(locatorBtnFacebookSession));
    }

    public void irAGoogleSession(){
        click(esperarAElementoWeb(locatorBtnSessionGoogle));
    }

    public String mensajeContrasenaUsuarioFallido(){
        return obtenerTexto(esperarAElementoWeb(locatorLblCotrasenaUsuarioFallido));
    }
}
