import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class PoSServiceTest {

    private final PoSService posService = new PoSServiceImpl();


    @Test
    public void shouldReturnPriceForExistingBarcode() throws Throwable {
        // Given
        String expectedPrice = "1234";
        String existingProductNumber = "existing-barcode";

        // When
        String price = posService.purchaseProduct(existingProductNumber);
        // Then
        assertThat(price).isEqualTo(expectedPrice);
    }

    @Test
    public void shouldReturnNotFoundForNonExistingBarcode() {
        // Given
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
        String emptyProductNumber = "";
        //when(posService.purchaseProduct(emptyProductNumber)).thenThrow(BadProductNumberException.class);
        // When
        posService.purchaseProduct(emptyProductNumber);
        fail("did not throw the exception");
    }

    @Test(expected = BadProductNumberException.class)
    public void shouldValidateNullBarcode() throws Throwable {
        // Given
        String nullProductNumber = null;
        //when(posService.purchaseProduct(nullProductNumber)).thenThrow(BadProductNumberException.class);
        // When
        posService.purchaseProduct(null);
        fail("did not throw the exception");
    }


}
