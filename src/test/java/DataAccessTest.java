import model.Coin;
import model.CoinsRepository;
import model.Product;
import model.ProductRepository;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessTest {

    DataAccess da = new DataAccess();

    @Test
    public void loadProductsShouldRaiseInvalidArgumentExceptionWhenFileDoesNotExist() {
        File invalid = new File("Invalid");
        assertThrows(IllegalArgumentException.class, ()->{da.loadProducts(invalid);});
    }

    @Test
    public void loadCoinsShouldRaiseInvalidArgumentExceptionWhenFileDoesNotExist() {
        File invalid = new File("Invalid");
        assertThrows(IllegalArgumentException.class, ()->{da.loadCoins(invalid);});
    }

    @Test
    public void loadProductsShouldReturnRepositoryWithProperLoadedProduct() {
        // Arrange
        Product expected = new Product("chips",15,0.50f);
        File file = new File("src/test/resources/productTest.txt");
        // Act
        ProductRepository pr = da.loadProducts(file);
        // Assert
        assertNotNull(pr.getProducts().stream()
        .filter(n-> n.getName().equals(expected.getName())
            && n.getPrice() == expected.getPrice()
            && n.getAmount() == expected.getAmount())
        .findFirst().get());
    }

    @Test
    public void loadProductsShouldReturnRepositoryWithProperLoadedCoin() {
        // Arrange
        Coin expected = new Coin(21.21f,5.00f,0.05f,0);
        File file = new File("src/test/resources/coinsTest.txt");
        // Act
        CoinsRepository cr = da.loadCoins(file);
        // Assert
        assertNotNull(cr.getCoins().stream()
                .filter(n-> n.getDiameter() == (expected.getDiameter())
                        && n.getWeight() == expected.getWeight()
                        && n.getAmount() == expected.getAmount()
                        && n.getValue() == expected.getValue())
                .findFirst().get());
    }
}