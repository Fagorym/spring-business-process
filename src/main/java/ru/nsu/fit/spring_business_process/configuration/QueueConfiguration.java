package ru.nsu.fit.spring_business_process.configuration;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import ru.nsu.fit.spring_business_process.queue.QueueType;
import ru.nsu.fit.spring_business_process.queue.base.BaseQueueConsumer;
import ru.yoomoney.tech.dbqueue.config.DatabaseDialect;
import ru.yoomoney.tech.dbqueue.config.QueueService;
import ru.yoomoney.tech.dbqueue.config.QueueShard;
import ru.yoomoney.tech.dbqueue.config.QueueShardId;
import ru.yoomoney.tech.dbqueue.config.QueueTableSchema;
import ru.yoomoney.tech.dbqueue.config.impl.LoggingTaskLifecycleListener;
import ru.yoomoney.tech.dbqueue.config.impl.LoggingThreadLifecycleListener;
import ru.yoomoney.tech.dbqueue.settings.ExtSettings;
import ru.yoomoney.tech.dbqueue.settings.FailRetryType;
import ru.yoomoney.tech.dbqueue.settings.FailureSettings;
import ru.yoomoney.tech.dbqueue.settings.PollSettings;
import ru.yoomoney.tech.dbqueue.settings.ProcessingMode;
import ru.yoomoney.tech.dbqueue.settings.ProcessingSettings;
import ru.yoomoney.tech.dbqueue.settings.QueueConfig;
import ru.yoomoney.tech.dbqueue.settings.QueueId;
import ru.yoomoney.tech.dbqueue.settings.QueueLocation;
import ru.yoomoney.tech.dbqueue.settings.QueueSettings;
import ru.yoomoney.tech.dbqueue.settings.ReenqueueRetryType;
import ru.yoomoney.tech.dbqueue.settings.ReenqueueSettings;
import ru.yoomoney.tech.dbqueue.spring.dao.SpringDatabaseAccessLayer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class QueueConfiguration {
    private static final String TABLE_NAME = "queue_tasks";
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    @Bean
    public QueueShard<SpringDatabaseAccessLayer> queueShard() {
        SpringDatabaseAccessLayer databaseAccessLayer = new SpringDatabaseAccessLayer(
            DatabaseDialect.POSTGRESQL, QueueTableSchema.builder().build(),
            jdbcTemplate,
            transactionTemplate
        );
        return new QueueShard<>(new QueueShardId("main"), databaseAccessLayer);
    }

    @Nonnull
    public static QueueConfig fromType(QueueType queueType) {
        QueueId queueId = new QueueId(queueType.name());
        QueueLocation queueLocation = QueueLocation.builder()
            .withTableName(TABLE_NAME)
            .withQueueId(queueId)
            .build();
        QueueSettings queueSettings = QueueSettings.builder()
            .withProcessingSettings(
                ProcessingSettings.builder()
                    .withProcessingMode(ProcessingMode.SEPARATE_TRANSACTIONS)
                    .withThreadCount(1)
                    .build()
            )
            .withPollSettings(
                PollSettings.builder()
                    .withBetweenTaskTimeout(Duration.ofMillis(100))
                    .withNoTaskTimeout(Duration.ofMillis(100))
                    .withFatalCrashTimeout(Duration.ofSeconds(1))
                    .build()
            )
            .withFailureSettings(
                FailureSettings.builder()
                    .withRetryType(FailRetryType.GEOMETRIC_BACKOFF)
                    .withRetryInterval(Duration.ofMinutes(1))
                    .build()
            )
            .withReenqueueSettings(
                ReenqueueSettings.builder()
                    .withRetryType(ReenqueueRetryType.MANUAL).build()
            )
            .withExtSettings(ExtSettings.builder().withSettings(new LinkedHashMap<>()).build())
            .build();
        return new QueueConfig(queueLocation, queueSettings);
    }

    @Bean
    public QueueService queueService(
        QueueShard<SpringDatabaseAccessLayer> queueShard,
        Collection<BaseQueueConsumer<?>> consumers
    ) {
        QueueService queueService = new QueueService(
            List.of(queueShard),
            new LoggingThreadLifecycleListener(),
            new LoggingTaskLifecycleListener()
        );

        consumers.forEach(
            consumer -> {
                log.info("Registering queue consumer {}", consumer);
                queueService.registerQueue(consumer);
            });

        queueService.start();

        return queueService;
    }

}
