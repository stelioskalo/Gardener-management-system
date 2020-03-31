/**
 * Bid.java
 */
package org.com1027.coursework.q3;

public class Bid {

  private double bidValue = 0;
  private User   buyer    = null;

  public Bid(double bidValue, User buyer) {
    super();
    this.bidValue = bidValue;
    this.buyer = buyer;

  }

  public double getBidValue() {
    return this.bidValue;
  }

  public User getBuyer() {
    return this.buyer;
  }

  @Override
  public String toString() {
    return "Bid [bidValue=" + bidValue + ", buyer=" + buyer + "]";
  }

}
