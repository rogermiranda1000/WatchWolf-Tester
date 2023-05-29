package generic;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.blocks.Blocks;
import dev.watchwolf.tester.AbstractTest;
import dev.watchwolf.tester.ExtendedClientPetition;
import dev.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(UserLookAtShould.class) // run the tests with the AbstractTest overridden methods
public class UserLookAtShould extends AbstractTest {
    @Override
    public String getConfigFile() {
        return "src/test/java/generic/resources/config.yaml";
    }

    @ParameterizedTest
    @ArgumentsSource(TesterTester.class)
    public void lookAtTheExactSameSpot(TesterConnector connector) throws Exception {
        ExtendedClientPetition client = connector.getClientPetition(0);

        // all the tests, with [pitch,yaw]
        float [][]tests = new float[][] {
                new float[]{-90, 0}, // top, south
                new float[]{0, 179}, // horizontal, north
                new float[]{0, -179}, // horizontal, north
                new float[]{90,-90}, // bottom, east
                new float[]{20,-70} // a bit down, south-east
        };

        for (float []test : tests) {
            float pitch = test[0],
                    yaw = test[1];

            client.lookAt(pitch, yaw);

            checkCamera(client.getYaw(), client.getPitch(), yaw, pitch);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(TesterTester.class)
    public void moveTheCameraToTheBlock(TesterConnector connector) throws Exception {
        String clientName = connector.getClients()[0];
        ExtendedClientPetition client = connector.getClientPetition(0);

        Position from = new Position("world", -229.5, 4, -197.5);
        // as we're looking at blocks we need to look at its center
        Position []examples = new Position[] {
                new Position("world", -227.5, 4.5, -194.5),
                new Position("world", -229.5, 4.5, -194.5),
                new Position("world", -229.5, 5.5, -194.5),
                new Position("world", -231.5, 4.5, -194.5),
                new Position("world", -229.5, 4.5, -198.5),
                new Position("world", -229.5, 3.5, -197.5),
                new Position("world", -229.5, 6.5, -197.5)
        };
        // all the results, with [yaw,pitch]
        Float [][]expected = new Float[][] {
                new Float[]{-33f, 20f},
                new Float[]{0f, 20f},
                new Float[]{0f, 2f},
                new Float[]{36f, 20f},
                new Float[]{180f, 50f},
                new Float[]{null, 90f},
                new Float[]{null, -90f}
        };

        // prepare the test area (it needs to be a jail)
        connector.server.setBlock(from.add(0,-1,0), Blocks.STONE);
        connector.server.setBlock(from.add(0,2,0), Blocks.STONE);
        connector.server.setBlock(from.add(1,0,0), Blocks.STONE); connector.server.setBlock(from.add(1,1,0), Blocks.STONE);
        connector.server.setBlock(from.add(-1,0,0), Blocks.STONE); connector.server.setBlock(from.add(-1,1,0), Blocks.STONE);
        connector.server.setBlock(from.add(0,0,1), Blocks.STONE); connector.server.setBlock(from.add(0,1,1), Blocks.STONE);
        connector.server.setBlock(from.add(0,0,-1), Blocks.STONE); connector.server.setBlock(from.add(0,1,-1), Blocks.STONE);
        connector.server.setBlock(from, Blocks.AIR);
        connector.server.setBlock(from.add(0,1,0), Blocks.AIR);
        connector.server.tp(clientName, from);
        // we don't need the blocks that we'll test as it's just a concept (the client will look like there's a block in there)

        // let the chunks load
        Thread.sleep(10000);

        for (int i = 0; i < examples.length; i++) {
            client.lookAt(examples[i]);

            float yaw = client.getYaw(),
                    pitch = client.getPitch();
            checkCamera(yaw, pitch, expected[i][0], expected[i][1]);
        }
    }

    private void checkCamera(float yaw, float pitch, Float expectedYaw, Float expectedPitch) throws Exception {
        float ERROR_MARGIN = 10f;

        if (expectedYaw != null && Math.abs(Math.abs(expectedYaw)-180f) <= ERROR_MARGIN) {
            // we're on the border of the yaw; it may be possible that it is -180 instead of +180
            if (Math.abs(Math.abs(yaw)-180f) <= ERROR_MARGIN) yaw = expectedYaw; // succeeded; force it so the comparison it's ok
        }

        assertTrue(Math.abs(yaw - (expectedYaw == null ? yaw : expectedYaw)) <= ERROR_MARGIN
                        && Math.abs(pitch - (expectedPitch == null ? pitch : expectedPitch)) <= ERROR_MARGIN,
                yaw + "/" + pitch + " (expected " + (expectedYaw == null ? yaw : expectedYaw) + "/" + (expectedPitch == null ? pitch : expectedPitch) + ")");
    }
}
