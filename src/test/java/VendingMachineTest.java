import model.Coin;
import model.CoinsRepository;
import model.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.arguments;
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
        vm = new VendingMachine(mock(ProductRepository.class),cr);
        boolean result = vm.checkVendingMachineChangePossibility();
        assertFalse(result);
    }

    @ParameterizedTest
    @MethodSource("valuesProvider")
    public void calculateChangeShouldReturnListOfCoinsForGivenChange(float value, float price, float expected) {
        // Arrange
        DataAccess da = new DataAccess();
        File file = new File("src/main/resources/coins.txt");
        CoinsRepository cr = da.loadCoins(file);
        vm = new VendingMachine(mock(ProductRepository.class),cr);
        Coin coin = new Coin(21.21f,5.00f,value,1);
        // Act
        vm.insertCoin(coin);
        List<Coin> change = vm.calculateChange(price);
        float actual = (float) change.stream().mapToDouble(n->n.getValue()*n.getAmount()).sum();
        // Assert
        assertEquals(expected, actual);
    }

    static Stream<Arguments> valuesProvider() {
        return Stream.of(
                arguments(1.05f, 1.00f, 0.05f),
                arguments(0.75f, 0.50f, 0.25f),
                arguments(0.50f, 0.50f, 0f),
                arguments(0.75f, 0.65f, 0.10f)
        );
    }
}