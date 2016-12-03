import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class PoSServiceTest {

    @Test
    public void shouldReturnPriceForExistingBarcode() throws Throwable {
        // Given
        Integer expectedPrice = 1234;
        String existingProductNumber = "existing-barcode";
        Product expectedProduct = new Product(existingProductNumber, expectedPrice);
        final PoSService posService = new PoSServiceImpl(Collections.singletonMap(expectedProduct.getProductNumber(), expectedProduct));

        // When
        Integer price = posService.purchaseProduct(existingProductNumber);
        // Then
        assertThat(price).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldReturnNotFoundForNonExistingBarcode() {
        // Given
        final PoSService posService = new PoSServiceImpl(Collections.emptyMap());

        // When
        Throwable expectedException = catchThrowable(() -> posService.purchaseProduct("non-existing-barcode"));
        assertThat(expectedException)
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage(String.format("Product not found for %s!", "non-existing-barcode"));
        ;
    }

    @Test(expected = BadProductNumberException.class)
    public void shouldValidateEmptyBarcode() throws Throwable {
        // Given
        final PoSService posService = new PoSServiceImpl(Collections.emptyMap());

        // When
        posService.purchaseProduct("");
        fail("did not throw the exception");
    }

    @Test(expected = BadProductNumberException.class)
    public void shouldValidateNullBarcode() throws Throwable {
        // Given
        final PoSService posService = new PoSServiceImpl(Collections.emptyMap());

        // When
        posService.purchaseProduct(null);
        fail("did not throw the exception");
    }


    @Test
    public void shouldPurchaseMultipleProducts() throws Exception {
        // Given
        Product product1 = new Product("1234", 1234);
        Product product2 = new Product("2345", 2345);
        final PoSService posService = new PoSServiceImpl(ImmutableMap.of(product1.getProductNumber(), product1, product2.getProductNumber(), product2));

        // When
        int totalPrice = posService.purchaseProducts(product1.getProductNumber(), product2.getProductNumber());
        // Then
        assertThat(totalPrice).isEqualTo(3579);

    }
}
