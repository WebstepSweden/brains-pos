import java.util.List;
import java.util.Optional;

public interface PoSService {
    Integer purchaseProducts(String... productIds) throws BadProductNumberException, ProductNotFoundException;
    Integer purchaseProducts(List<Product> productsToPurchase) throws BadProductNumberException, ProductNotFoundException;

    Optional<Product> findProduct(String barcode);
}
