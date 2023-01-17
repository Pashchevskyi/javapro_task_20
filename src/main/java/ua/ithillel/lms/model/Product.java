package ua.ithillel.lms.model;

import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Product {

  private final long id;

  private final String name;
  private final double price;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return id == product.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public String toString() {
    return "Product info:\nID\tName\tPrice\n"
        + id
        + "\t"
        + name
        + "\t"
        + String.format("%.2f", price)
        + "\n";
  }
}
