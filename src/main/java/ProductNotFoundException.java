public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String productNumber) {
        super(String.format("Product not found for %s!", productNumber));
    }
}
