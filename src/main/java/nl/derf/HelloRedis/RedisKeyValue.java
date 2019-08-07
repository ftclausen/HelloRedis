package nl.derf.HelloRedis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RedisKeyValue {
  final private String key;
  final private String value;

  @JsonCreator
  public RedisKeyValue(@JsonProperty("key") String key, @JsonProperty("value") String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return this.value;
  }
}
