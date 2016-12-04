public class Scanner {

    private final Display display;

    public Scanner(Display display) {

        this.display = display;
    }

    public void scan(String productNumber) {
        display.displayPrice(productNumber);
    }
}
