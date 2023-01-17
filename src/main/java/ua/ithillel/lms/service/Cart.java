package ua.ithillel.lms.service;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import ua.ithillel.lms.exception.ProductNotFoundException;
import ua.ithillel.lms.model.Product;
import ua.ithillel.lms.repository.ProductRepository;

public class Cart {

  private final ProductRepository productRepository;
  @Getter
  private final List<Product> products;

  public Cart(ProductRepository productRepository) {
    this.productRepository = productRepository;
    this.products = new ArrayList<>();
  }

  /**
   * Adds Product to Cart and removes it from ProductRepository
   *
   * @param product Product we are going to add
   * @return true if adding of product was successful and false otherwise
   */
  public boolean addProduct(Product product) {
    boolean isInCart = false;
    boolean isOutOfStore = false;
    if (productRepository.getProducts().contains(product)) {
      isInCart = products.add(product);
      isOutOfStore = productRepository.getProducts().remove(product);
    }
    return isInCart && isOutOfStore;
  }

  /**
   * Removes Product from cart and returns it in ProductRepository
   *
   * @param id (long integer) id of Product we are going to remove from cart
   * @return true if removing of Product was successful and false otherwise
   * @throws ProductNotFoundException if Product was not found in Cart
   */
  public boolean removeProduct(long id) throws ProductNotFoundException {
    boolean isOutOfCart;
    boolean isInStore = false;
    Product product = products.stream().filter((p) -> p.getId() == id).findFirst().orElseThrow(
        () -> new ProductNotFoundException(id));
    isOutOfCart = products.remove(product);
    if (!productRepository.getProducts().contains(product)) {
      isInStore = productRepository.getProducts().add(product);
    }
    return isOutOfCart && isInStore;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("Your cart:\nID\tName\tPrice\n");
    for (Product product : products) {
      sb.append(product.getId());
      sb.append("\t");
      sb.append(product.getName());
      sb.append("\t");
      sb.append(String.format("%.2f", product.getPrice()));
      sb.append("\n");
    }
    return sb.toString();
  }
}
