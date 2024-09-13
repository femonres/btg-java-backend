package com.btg_pactual.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionalSpringApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    FunctionalSpringApplication.run(Application.class, args);
  }
}
