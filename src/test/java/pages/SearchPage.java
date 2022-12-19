package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class SearchPage extends BaseClass {

    By locatorTxtBuscador= By.xpath("//input[@data-testid='search-input']");
    By locatorResultadoPrincipal= By.xpath("//div[@data-testid='top-result-card']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void buscar(String artista){
        agregarTexto(esperarAElementoWeb(locatorTxtBuscador), artista);
    }

    public void irAResutladoPrincipalBusqueda(){
        click(esperarAElementoWeb(locatorResultadoPrincipal));
    }
}
