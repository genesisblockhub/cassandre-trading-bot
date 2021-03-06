package tech.cassandre.trading.bot.test.configuration.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.cassandre.trading.bot.test.util.junit.configuration.Configuration;
import tech.cassandre.trading.bot.test.util.junit.configuration.Property;
import tech.cassandre.trading.bot.test.util.strategies.TestableCassandreStrategy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DisplayName("Strategy configuration - Trade & position services")
@Configuration({
        @Property(key = "TEST_NAME", value = "Strategy configuration - Trade & position services")
})
public class CassandreStrategyServicesTest {

    @Autowired
    private TestableCassandreStrategy strategy;

    @Test
    @DisplayName("Check that trade service is present")
    public void checkTradeService() {
        assertNotNull(strategy.getTradeService());
    }

    @Test
    @DisplayName("Check that position service is present")
    public void checkPositionService() {
        assertNotNull(strategy.getPositionService());
    }

}
