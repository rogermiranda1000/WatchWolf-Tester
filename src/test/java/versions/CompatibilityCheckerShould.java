package versions;

import dev.watchwolf.tester.AbstractTest;
import dev.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

@ExtendWith(CompatibilityCheckerShould.class)
public class CompatibilityCheckerShould extends AbstractTest {

    @Override
    public String getConfigFile() {
        return "src/test/java/generic/resources/config.yaml";
    }

    @ParameterizedTest
    @ArgumentsSource(CompatibilityCheckerShould.class)
    public void getVersionsFromAllFramework(TesterConnector connector) throws Exception {
        System.out.println("Servers manager version: " + connector.getServersManagerVersion());
        System.out.println("Server version: " + connector.server.getVersion());

        System.out.println("Clients manager version: " + connector.getClientsManagerVersion());
        System.out.println("Client version: " + connector.getClientPetition(0).getVersion());
    }
}
