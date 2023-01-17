package ua.ithillel.lms.exception;

public class ProductNotFoundException extends Exception {

  public ProductNotFoundException(long id) {
    super("Product with ID#" + id + " has not been found");
  }
}
