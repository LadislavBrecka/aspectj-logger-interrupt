package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    logger.info("Executing main function");

    Car car = new Car("FOCUS", "Ford");
    logger.info("I bought a car: \n{}", car);

  }
}
