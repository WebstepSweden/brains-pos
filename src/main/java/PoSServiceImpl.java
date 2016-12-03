import java.util.Arrays;
import java.util.Map;

public class PoSServiceImpl implements PoSService {

    private final Map<String, Product> products;

    public PoSServiceImpl(Map<String, Product> products) {
        this.products = products;
    }

    @Override
    public Integer purchaseProduct(String productNumber) throws BadProductNumberException, ProductNotFoundException {
        if (productNumber == null || productNumber.length() == 0) {
            throw new BadProductNumberException();
        }

        if (productNumber.equals("non-existing-barcode")) {
            throw new ProductNotFoundException(productNumber);
        }

        return products.get(productNumber).getPrice();
    }

    @Override
    public Integer purchaseProducts(String... productIds) {
        return products.entrySet().stream()
                .filter(s -> Arrays.asList(productIds).contains(s.getKey()))
                .map(Map.Entry::getValue)
                .mapToInt(Product::getPrice)
                .sum();
    }
}
