package org.example.reactive.flux;

import java.util.Random;

public final class Utils {

  private static final Random RANDOM = new Random();

  private Utils() {
  }

  public static String randomString(int length) {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    StringBuilder buffer = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      int randomLimitedInt = leftLimit + (int)
          (RANDOM.nextFloat() * (rightLimit - leftLimit + 1));
      buffer.append((char) randomLimitedInt);
    }
    return buffer.toString();
  }
}
