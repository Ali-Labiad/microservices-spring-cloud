package com.ibm.bootcamp.spring.datalake;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest(properties = {
        "spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer",
        "spring.boot.lazy-initialization=false"
})
@EmbeddedKafka(partitions = 1, bootstrapServersProperty = "spring.kafka.bootstrap-servers")
public abstract class AbstractTest<D, M> {

    static final Random RANDOM = new Random();

    @Autowired
    KafkaTemplate<String, D> kafka;

    private final long id = l();

    static int i() {
        return RANDOM.nextInt();
    }

    static long l() {
        return RANDOM.nextLong();
    }

    static String s() {
        return UUID.randomUUID().toString();
    }

    static float f() {
        return Math.round(RANDOM.nextFloat() * 10000) / 10000.0f;
    }

    abstract M getModel(long id);

    abstract D getDto(long id);

    abstract String topic();

    abstract void assertEquals(D dto, M model);

    abstract JpaRepository<M, Long> repo();

    @Test
    void shouldBeAdded() {
        D dto = getDto(id);
        kafka.send(topic(), "", dto);
        Awaitility.waitAtMost(10, TimeUnit.SECONDS).until(() -> repo().findById(id).isPresent());
        assertEquals(dto, repo().findById(id).get());
    }

    @Test
    void shouldBeUpdated() {
        repo().save(getModel(id));
        D dto = getDto(id);
        kafka.send(topic(), "", dto);
        Awaitility.waitAtMost(10, TimeUnit.SECONDS).until(() -> repo().findById(id).isPresent());
        assertEquals(dto, repo().findById(id).get());
    }

}
