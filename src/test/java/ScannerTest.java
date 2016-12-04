import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ScannerTest {
    @Mock
    private Display display;
    private ImmutableMap<String, Product> products = ImmutableMap.of(
            "1234", new Product("1234", 1234)
            , "1235", new Product("1235", 1235)
    );
    @Spy
    private PoSService catalog = new PoSServiceImpl(products);

    @Test
    public void shouldScan() throws Throwable {
        // Given
        Scanner scanner = new Scanner(display, catalog);
        // When
        scanner.scan("1234");
        // Then
        Mockito.verify(catalog, times(1)).findProduct("1234");
        Mockito.verify(display, times(1)).displayPrice(1234);

    }
    @Test
    public void shouldScanMultiple() throws Exception {
        // Given

        // When
        new Scanner(display, catalog).scan("1234\n1235\n\n");
        // Then
        Mockito.verify(display, times(1)).displayPrice(1234);
        Mockito.verify(display, times(1)).displayPrice(1235);
        Mockito.verifyNoMoreInteractions(display);

    }

    @Test
    public void shouldCheckoutAndDisplayPrice() throws Exception {
        // Given
        Scanner scanner = new Scanner(display, catalog);

        // When
        scanner.scan("1234");
        Integer totalAmount = scanner.checkOut();

        // Then
        assertThat(totalAmount).isEqualTo(1234);

    }
}