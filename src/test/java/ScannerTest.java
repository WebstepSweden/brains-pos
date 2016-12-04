import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.only;

@RunWith(MockitoJUnitRunner.class)
public class ScannerTest {
    @Mock
    private Display display;

    @Test
    public void shouldScanAndDisplay() throws Exception {
        // Given

        // When
        new Scanner(display).scan("1234");
        // Then
        Mockito.verify(display, only()).displayPrice(any());

    }
}