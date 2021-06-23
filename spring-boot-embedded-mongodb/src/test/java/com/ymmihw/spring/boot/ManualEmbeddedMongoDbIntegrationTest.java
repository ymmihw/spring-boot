package com.ymmihw.spring.boot;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Feature;
import de.flapdoodle.embed.mongo.distribution.IFeatureAwareVersion;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

public class ManualEmbeddedMongoDbIntegrationTest {
  private MongodExecutable mongodExecutable;
  private MongoTemplate mongoTemplate;

  @AfterEach
  void clean() {
    mongodExecutable.stop();
  }

  @BeforeEach
  void setup() throws Exception {
    String ip = "localhost";
    int port = 27017;

    MongodStarter starter = MongodStarter.getDefaultInstance();
    ImmutableMongodConfig mongodConfig =
        MongodConfig.builder()
            .version(
                (new IFeatureAwareVersion() {
                  @Override
                  public String asInDownloadPath() {
                    return "3.2.2";
                  }

                  @Override
                  public boolean enabled(Feature feature) {
                    EnumSet<Feature> features =
                        Feature.asSet(Feature.SYNC_DELAY, Feature.STORAGE_ENGINE);
                    return features.contains(feature);
                  }

                  @Override
                  public EnumSet<Feature> getFeatures() {
                    return null;
                  }
                }))
            .net(new Net(ip, port, Network.localhostIsIPv6()))
            .build();
    mongodExecutable = starter.prepare(mongodConfig);
    mongodExecutable.start();
    mongoTemplate = new MongoTemplate(MongoClients.create("mongodb://" + ip + ":" + port), "test");
  }

  @DisplayName("Given object When save object using MongoDB template Then object can be found")
  @Test
  void test() throws Exception {
    // given
    DBObject objectToSave = BasicDBObjectBuilder.start().add("key", "value").get();

    // when
    mongoTemplate.save(objectToSave, "collection");

    // then
    assertThat(mongoTemplate.findAll(DBObject.class, "collection"))
        .extracting("key")
        .containsOnly("value");
  }
}
