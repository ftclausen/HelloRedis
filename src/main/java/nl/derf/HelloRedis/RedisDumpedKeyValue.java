package nl.derf.HelloRedis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Arrays;
import java.util.Base64;

public class RedisDumpedKeyValue {
  final private String key;
  final private byte[] value;

  @JsonCreator
  RedisDumpedKeyValue(@JsonProperty("key") String key, @JsonProperty("value") byte[] value) {
    this.key = key;
    this.value = value;
  }

  String getKey() {
    return key;
  }

  byte[] getValue() {
    return this.value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RedisDumpedKeyValue that = (RedisDumpedKeyValue) o;
    return Objects.equal(getKey(), that.getKey()) &&
        Arrays.equals(getValue(), that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getKey(), getValue());
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("key", key)
        .add("value", Base64.getEncoder().encodeToString(value))
        .toString();
  }
}
