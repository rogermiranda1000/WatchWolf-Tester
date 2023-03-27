package generic;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.blocks.Blocks;
import dev.watchwolf.entities.entities.Chicken;
import dev.watchwolf.entities.entities.Entity;
import dev.watchwolf.entities.entities.EntityType;
import dev.watchwolf.entities.entities.Zombie;
import dev.watchwolf.entities.items.Item;
import dev.watchwolf.entities.items.ItemType;
import dev.watchwolf.tester.AbstractTest;
import dev.watchwolf.tester.ExtendedClientPetition;
import dev.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EntitiesTester.class)
public class EntitiesTester extends AbstractTest {
    @Override
    public String getConfigFile() {
        return "src/test/java/generic/resources/config.yaml";
    }

    @ParameterizedTest
    @ArgumentsSource(ItemsTester.class)
    public void checkSpawn(TesterConnector connector) throws Exception {
        Position spawnPosition = new Position("world", 100,100,100);

        connector.server.setBlock(spawnPosition, Blocks.AIR);
        connector.server.setBlock(spawnPosition.add(0,1,0), Blocks.AIR);
        connector.server.setBlock(spawnPosition.add(0,-1,0), Blocks.STONE);

        final String uuid = connector.server.spawnEntity(new Zombie(spawnPosition)).getUUID();
        assertTrue(Arrays.stream(connector.server.getEntities(spawnPosition,3)).anyMatch(e -> e.getType().equals(EntityType.ZOMBIE) && e.getUUID().equals(uuid)));
    }

    @ParameterizedTest
    @ArgumentsSource(ItemsTester.class)
    public void checkAttack(TesterConnector connector) throws Exception {
        Position spawnPosition = new Position("world", 200,100,200),
                playerPosition = spawnPosition.add(1,0,0);

        connector.server.setBlock(spawnPosition, Blocks.AIR);
        connector.server.setBlock(spawnPosition.add(0,-1,0), Blocks.STONE);
        connector.server.setBlock(playerPosition, Blocks.AIR);
        connector.server.setBlock(playerPosition.add(0,1,0), Blocks.AIR);
        connector.server.setBlock(playerPosition.add(0,-1,0), Blocks.STONE);

        Item sword = new Item(ItemType.DIAMOND_SWORD);
        String clientName = connector.getClients()[0];
        connector.server.giveItem(clientName, sword);
        ExtendedClientPetition client = connector.getClientPetition(0);
        client.equipItemInHand(sword);
        connector.server.tp(clientName, playerPosition);

        connector.server.spawnEntity(new Chicken(spawnPosition));
        final Entity spawnedChicken = Arrays.stream(connector.server.getEntities(spawnPosition,3)).filter(e -> e.getType().equals(EntityType.CHICKEN)).findFirst().orElseThrow(() -> new RuntimeException("Chicken spawned but not found"));

        Thread.sleep(3000); // let the client update the entities list

        // one diamond hit should be enough to kill a chicken
        // @ref https://minecraft.fandom.com/wiki/Sword
        // @ref https://minecraft.fandom.com/wiki/Chicken
        client.attack(spawnedChicken);

        assertFalse(Arrays.stream(connector.server.getEntities(spawnPosition,3)).anyMatch(e -> e.getUUID().equals(spawnedChicken.getUUID())));
    }
}
