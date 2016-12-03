import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

public class PoSTest {

   private final PoSService posService = Mockito.mock(PoSService.class);

   @Test
   public void shouldReturnPriceForExistingBarcode() throws Exception {
      // Given
      String expectedPrice = "1234";
      String existingProductNumber = "existing-barcode";
      when(posService.purchaseProduct(existingProductNumber)).thenReturn(expectedPrice);
      // When
      String price = posService.purchaseProduct(existingProductNumber);
      // Then
      assertThat(price).isEqualTo(expectedPrice);
   }

   @Test(expected = ProductNotFoundException.class)
   public void shouldReturnNotFoundForNonExistingBarcode() throws Exception {
      // Given
      when(posService.purchaseProduct("non-existing-barcode")).thenThrow(ProductNotFoundException.class);

      // When
      posService.purchaseProduct("non-existing-barcode");
      fail("did not throw the exception");
   }

   @Test(expected = BadProductNumberException.class)
   public void shouldValidateEmptyBarcode() throws Exception {
      // Given
      String emptyProductNumber = "";
      when(posService.purchaseProduct(emptyProductNumber)).thenThrow(BadProductNumberException.class);
      // When
      posService.purchaseProduct(emptyProductNumber);
      fail("did not throw the exception");
   }
   @Test(expected = BadProductNumberException.class)
   public void shouldValidateNullBarcode() throws Exception {
      // Given
      String nullProductNumber = null;
      when(posService.purchaseProduct(nullProductNumber)).thenThrow(BadProductNumberException.class);
      // When
      posService.purchaseProduct(null);
      fail("did not throw the exception");
   }


}
