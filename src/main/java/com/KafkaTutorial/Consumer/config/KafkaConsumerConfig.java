package com.KafkaTutorial.Consumer.config;


import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConsumerConfig {

  @Value("bootstrap.servers")
  private String kafkaServerEndpoint;
  @Value("group.id")
  private String kafkaGroupId;
  @Value("sasl.mechanism")
  private String saslMechanism;
  @Value("security.protocol")
  private String securityProtocal;
  @Value("sasl.jaas.config")
  private String saslJaasConfig;
  @Value("key.deserializer")
  private String keyDesrializer;
  @Value("value.deserializer")
  private String valueDesializer;
  @Value("auto.offset.reset")
  private String autoOffsetreset;


  @Bean
  public ConsumerFactory<String,String> consumerFactory(){
    log.info("******************Inside ConsumerFactory************");
    Map<String,Object> prop = new HashMap<>();
    prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaServerEndpoint);
    prop.put(ConsumerConfig.GROUP_ID_CONFIG,kafkaGroupId);
    prop.put("sasl.mechanism", saslMechanism);
    prop.put("security.protocol", securityProtocal);
    prop.put("sasl.jaas.config", saslJaasConfig);
    prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDesrializer);
    prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDesializer);
    prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetreset);

    return new DefaultKafkaConsumerFactory<>(prop);

  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListner(){
    log.info("***********Inside kafkaListner**************");

    ConcurrentKafkaListenerContainerFactory<String, String> obj =
        new ConcurrentKafkaListenerContainerFactory<>();
    obj.setConsumerFactory(consumerFactory());

    return obj;
  }

}
