package nl.derf.HelloRedis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RedisDumpedKeyValue {
  final private String key;
  final private byte[] value;

  @JsonCreator
  RedisDumpedKeyValue(@JsonProperty("key") String key, @JsonProperty("value") byte[] value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  byte[] getValue() {
    return this.value;
  }
}
