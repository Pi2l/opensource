package edu.m.item;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

  private final ItemRepository itemRepository;

  @PostConstruct
  public void init() {
    itemRepository.deleteAll();
    itemRepository.save(new Item("1", "Item 1", "Description 1"));
    itemRepository.save(new Item("2", "Item 2", "Description 2"));
    itemRepository.save(new Item("3", "Item 3", "Description 3"));
  }

  public Item get(String id) {
    return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
  }

  public List<Item> getAll() {
    return itemRepository.findAll();
  }

  public Item create(Item item) {
    return itemRepository.save(item);
  }

  public Item update(Item item) {
    itemRepository.findById(item.getId())
            .orElseThrow(() -> new RuntimeException("Item not found"));
    return itemRepository.save(item);
  }

  public void delete(Item item) {
    itemRepository.findById(item.getId())
            .orElseThrow(() -> new RuntimeException("Item not found"));
    itemRepository.delete(item);
  }
}
