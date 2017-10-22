package com.projectlarp.app.modules.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @see https://github.com/sahat/satellizer/blob/master/examples/server/java/src/main/java/com/example/helloworld/core/Token.java
 */
public class AuthServiceToken {
  String token;

  public AuthServiceToken(@JsonProperty("token") String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}