public interface PoSService {
    Integer purchaseProduct(String productNumber) throws Throwable;

    Integer purchaseProducts(String... productIds);
}
