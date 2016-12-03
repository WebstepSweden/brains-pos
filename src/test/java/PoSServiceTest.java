import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.Test;

public class PoSServiceTest {

    private final PoSService posService = new PoSServiceImpl();


    @Test
    public void shouldReturnPriceForExistingBarcode() throws Throwable {
        // Given
        String expectedPrice = "1234";
        String existingProductNumber = "existing-barcode";
      //  when(posService.purchaseProduct(existingProductNumber)).thenReturn(expectedPrice);
        // When
        String price = posService.purchaseProduct(existingProductNumber);
        // Then
        assertThat(price).isEqualTo(expectedPrice);
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldReturnNotFoundForNonExistingBarcode() throws Throwable {
        // Given
        //when(posService.purchaseProduct("non-existing-barcode")).thenThrow(ProductNotFoundException.class);

        // When
        posService.purchaseProduct("non-existing-barcode");
        fail("did not throw the exception");
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
