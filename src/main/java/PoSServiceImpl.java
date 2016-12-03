/**
 * Created by reynir on 3.12.2016.
 */
public class PoSServiceImpl implements PoSService {

    @Override
    public String purchaseProduct(String productNumber) throws BadProductNumberException, ProductNotFoundException {
        if (productNumber == null || productNumber.length() == 0) {
            throw new BadProductNumberException();
        }

        if (productNumber.equals("non-existing-barcode")) {
            throw new ProductNotFoundException(productNumber);
        }

        return "1234";
    }
}
