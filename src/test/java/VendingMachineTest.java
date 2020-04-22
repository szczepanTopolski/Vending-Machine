import model.Coin;
import model.CoinsRepository;
import model.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Coin coin = new Coin(30,40,50,60);
        List<Coin> expected = Arrays.asList(coin);
        // Act
        vm.insertCoin(coin);
        List<Coin> actual = vm.customerCoins.getCoins();
        // Assert
        assertEquals(expected.toString(),actual.toString());
    }

    @Test
    public void checkVMChangePossibilityShouldReturnFalseIfChangeIsImpossible() {
        //TODO LOAD EMPTY COINS REPO AND TRY BUY PRODUCT AND SO ON
        boolean result = vm.checkVendingMachineChangePossibility();
        assertFalse(result);
    }

}