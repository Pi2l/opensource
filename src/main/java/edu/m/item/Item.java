package edu.m.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item extends Auditable {

  @Id
  private String id;
  private String name;
  private String description;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Item item = (Item) o;
      return getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getId());
    }

    @Override
    public String toString() {
      return "Item{" +
              "id='" + id + '\'' +
              ", name='" + name + '\'' +
              ", description='" + description + '\'' +
              '}';
    }
}
