package tech.cassandre.trading.bot.test.configuration.parameters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import tech.cassandre.trading.bot.CassandreTradingBot;
import tech.cassandre.trading.bot.test.util.junit.configuration.Configuration;
import tech.cassandre.trading.bot.test.util.junit.configuration.Property;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static tech.cassandre.trading.bot.util.parameters.ExchangeParameters.PARAMETER_SECRET;

@DisplayName("Configuration parameters - Invalid credentials")
@Configuration({
		@Property(key = PARAMETER_SECRET, value = "none")
})
public class InvalidCredentialsTest {

	@Test
	@DisplayName("Check error messages")
	public void checkErrorMessages() {
		try {
			SpringApplication application = new SpringApplication(CassandreTradingBot.class);
			application.run();
			fail("Exception not raised");
		} catch (Exception e) {
			assertTrue(e.getMessage().contains("Invalid credentials for kucoin"));
		}
	}

}
