import com.rogermiranda1000.watchwolf.entities.Position;
import com.rogermiranda1000.watchwolf.entities.items.Item;
import com.rogermiranda1000.watchwolf.entities.items.ItemType;
import com.rogermiranda1000.watchwolf.tester.AbstractTest;
import com.rogermiranda1000.watchwolf.tester.ExtendedClientPetition;
import com.rogermiranda1000.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ItemsTester.class)
public class ItemsTester extends AbstractTest {
    @Override
    public String getConfigFile() {
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

    @ParameterizedTest
    @ArgumentsSource(ItemsTester.class)
    public void checkItems(TesterConnector connector) throws Exception {
        String user = connector.getClients()[0];
        ExtendedClientPetition petition = connector.getClientPetition(user);

        for (ItemType type : ItemType.values()) {
            System.out.println("Validating item " + type.name() + "...");
            Item give = new Item(type);
            connector.server.giveItem(user, give);

            ArrayList<Item> expected = new ArrayList<>();
            expected.add(give);
            assertEquals(expected, Arrays.asList(petition.getInventory().getItems()));

            connector.server.runCommand("clear " + user); // clear inventory
        }
    }
}
