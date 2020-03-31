/**
 * Product.java
 */
package org.com1027.coursework.q2;

public abstract class Product {

  private int    productId   = 0;
  private String productName = null;

  public abstract String displayHistory();

  public abstract String displayUserInfoForProduct();

  public abstract double getCurrentPrice();

  /**
   * constructor used in order to  be able to create Product objects, contains validation.
   * @param productId
   * @param productName
   */
  public Product(int productId, String productName) {
    if (productName == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    else {
      this.productName = productName;
    }
    if (productId < 0) {
      throw new IllegalArgumentException("the product ID cannot be a negative number");
    }
    else {
      this.productId = productId;
    }

  }

  public int getProductId() {
    return this.productId;
  }

  public String getProductName() {
    return this.productName;
  }

  public abstract boolean isProductSold();

  @Override
  public String toString() {
    return productId + " - " + productName;
  }

}
