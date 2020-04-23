import model.Coin;
import model.CoinsRepository;
import model.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

class VendingMachineTest {

    CoinsRepository mockCoinsRepository;
    ProductRepository mockProductRepository;
    VendingMachine  vm;

    @BeforeEach
    private void setUp(){
        mockCoinsRepository = mock(CoinsRepository.class);
        mockProductRepository = mock(ProductRepository.class);
        vm = new VendingMachine(mockProductRepository,mockCoinsRepository);
    }

    @Test
    public void insertCoinShouldAddCoinToUserCoins() {
        // Arrange
        Coin coin = new Coin(30,40,50,0);
        List<Coin> expected = Arrays.asList(coin);
        // Act
        vm.insertCoin(coin);
        List<Coin> actual = vm.customerCoins.getCoins();
        // Assert
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    public void checkVMChangePossibilityShouldReturnFalseIfChangeIsImpossible() {
        DataAccess da = new DataAccess();
        File file = new File("src/test/resources/coinsTest.txt");
        // Act
        CoinsRepository cr = da.loadCoins(file);
        VendingMachine vmTemp = new VendingMachine(mock(ProductRepository.class),cr);
        boolean result = vmTemp.checkVendingMachineChangePossibility();
        assertFalse(result);
    }

}