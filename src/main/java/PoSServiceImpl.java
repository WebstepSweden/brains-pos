import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PoSServiceImpl implements PoSService {

    private final Map<String, Product> products;

    public PoSServiceImpl(Map<String, Product> products) {
        this.products = products;
    }

    @Override
    public Integer purchaseProducts(String... productIds) throws BadProductNumberException, ProductNotFoundException {
        for(String productNumber : productIds) {
            if (productNumber == null || productNumber.length() == 0) {
                throw new BadProductNumberException();
            }

            if (productNumber.equals("non-existing-barcode")) {
                throw new ProductNotFoundException(productNumber);
            }
        }
        return products.entrySet().stream()
                .filter(s -> Arrays.asList(productIds).contains(s.getKey()))
                .map(Map.Entry::getValue)
                .mapToInt(Product::getPrice)
                .sum();
    }

    @Override
    public Integer purchaseProducts(List<Product> productsToPurchase) throws BadProductNumberException, ProductNotFoundException {
        return purchaseProducts(
                productsToPurchase.stream()
                    .map(Product::getProductNumber)
                    .toArray(String[]::new)
        );
    }

    @Override
    public Optional<Product> findProduct(String barcode) {
        return Optional.ofNullable(products.get(barcode));
    }
}
