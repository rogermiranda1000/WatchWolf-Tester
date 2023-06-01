package timings;

import dev.watchwolf.tester.AbstractTest;
import dev.watchwolf.tester.TesterConnector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

@ExtendWith(TimingsReporterShould.class)
public class TimingsReporterShould extends AbstractTest {
    @Override
    public String getConfigFile() {
        return "src/test/java/timings/resources/config.yaml";
    }

    @ParameterizedTest
    @ArgumentsSource(TimingsReporterShould.class)
    public void sendReport(TesterConnector connector) throws Exception {
        // check if there's files in resources/reports
        // TODO automate
    }
}
