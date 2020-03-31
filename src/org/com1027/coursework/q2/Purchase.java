/**
 * Purchase.java
 */
package org.com1027.coursework.q2;

public class Purchase {

  private User buyer             = null;
  private int  quantityPurchased = 0;
  
  public Purchase(User buyer, int quantityPurchased) {
    super();
    this.buyer = buyer;
    if (quantityPurchased < 0) {
      throw new IllegalArgumentException("The quantity purchased cannot be less than 0");
    }
    else {
      this.quantityPurchased = quantityPurchased;
    }
  }

  public User getBuyer() {
    return this.buyer;
  }

  public int getQuantityPurchased() {
    return this.quantityPurchased;
  }

  @Override
  public String toString() {
    return buyer.getName().substring(0, 1)
        + buyer.getName().replaceAll(buyer.getName().substring(0, buyer.getName().length() - 1), "***") + " bought "
        + getQuantityPurchased();
  }

}
