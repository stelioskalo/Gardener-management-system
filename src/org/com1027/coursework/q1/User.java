/*
 * User.java
 */
package org.com1027.coursework.q1;

import java.util.regex.Pattern;

public class User {

  private String name = null;

  /**
   * Public constructor that gives us the ability to create User objects and also consists of validation checks in case of inavlid inputs.
   * @param name
   */
  public User(String name) {
    super();
    if (name != null && Pattern.matches("[a-zA-z]+", name) == true) {
      this.name = name;
    }
    else {
      throw new IllegalArgumentException("The name can only consist letters");
    }
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name.substring(0, 1) + name.replaceAll(name.substring(0, name.length() - 1), "***");
  }

}
