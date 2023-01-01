package ru.team2.skud.config.logger;

import dev.miku.r2dbc.mysql.ConnectionContext;
import org.springframework.context.annotation.Configuration;
import reactor.util.Logger;
import reactor.util.Loggers;

@Configuration
public class QueryLogger {

    private static final Logger QUERY_LOGGER = Loggers.getLogger(QueryLogger.class);

    public static void logQuery(ConnectionContext context, String query) {
        QUERY_LOGGER.debug(query);
    }
}