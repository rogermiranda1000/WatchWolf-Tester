import com.rogermiranda1000.watchwolf.entities.items.ItemType;
import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import com.rogermiranda1000.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ItemsTester.class)
public class ItemsTester extends AbstractTest {
    @Override
    public File getConfigFile() {
        return null; // TODO
    }

    /**
     * This test doesn't touch the connector, but validates the enum.
     */
    @ParameterizedTest
    @ArgumentsSource(ItemsTester.class)
    public void checkEnum(TesterConnector connector) {
        assertEquals(ItemType.values()[0], ItemType.AIR); // (by definition) AIR must be the first one
    }
}
