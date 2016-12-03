public class Product {
    public String getProductNumber() {
        return productNumber;
    }

    public Integer getPrice() {
        return price;
    }

    private final String productNumber;
    private final Integer price;

    public Product(String productNumber, Integer price) {
        this.productNumber = productNumber;
        this.price = price;
    }
}
