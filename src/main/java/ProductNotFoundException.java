public class ProductNotFoundException extends Throwable {

    public ProductNotFoundException(String productNumber) {
        super(String.format("Product not found for %s!", productNumber));
    }
}
