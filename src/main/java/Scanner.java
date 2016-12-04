import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scanner {

    private final Display display;
    private final PoSService catalog;
    private List<Product> shoppingCart;

    public Scanner(Display display, PoSService catalog) {
        this.display = display;
        this.catalog = catalog;
        this.shoppingCart = new ArrayList<>();
    }

    public void scan(String productNumberString) throws BadProductNumberException, ProductNotFoundException {
        for (StringTokenizer stringTokenizer = new StringTokenizer(productNumberString, "\n"); stringTokenizer.hasMoreTokens(); ) {
            this.catalog.findProduct(stringTokenizer.nextToken())
            .ifPresent(
                    product -> {
                        this.shoppingCart.add(product);
                        this.display.displayPrice(product.getPrice());
                    }
            );
        }

    }

    public Integer checkOut() throws BadProductNumberException, ProductNotFoundException {
        return catalog.purchaseProducts(shoppingCart);
    }
}
