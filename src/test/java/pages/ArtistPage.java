package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class ArtistPage extends BaseClass {

    By locatorArtista= By.xpath("//span[@data-testid='entityTitle']");

    public ArtistPage(WebDriver driver) {
        super(driver);
    }

    public String obtenerArtista(){
        return obtenerTexto(esperarAElementoWeb(locatorArtista));
    }
}
