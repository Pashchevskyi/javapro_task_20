package ua.ithillel.lms.repository;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.ithillel.lms.exception.ProductNotFoundException;
import ua.ithillel.lms.model.Product;

@RequiredArgsConstructor
public class ProductRepository {

  @Getter
  private final List<Product> products;

  /**
   * Returns Product instance by transferred id or throws exception if Product has NOT been found
   *
   * @param id Product ID (long integer)
   * @return Product if it has been found
   * @throws ProductNotFoundException if Product with id has NOT been found
   */
  public Product getProductById(long id) throws ProductNotFoundException {
    return products.stream().filter((product) -> product.getId() == id).findFirst().orElseThrow(
        () -> new ProductNotFoundException(id));
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("Assortment:\nID\tName\tPrice\n");
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
