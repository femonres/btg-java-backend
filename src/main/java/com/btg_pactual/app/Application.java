package com.btg_pactual.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.adapter.aws.FunctionInvoker;

@SpringBootApplication
public class Application extends FunctionInvoker {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
