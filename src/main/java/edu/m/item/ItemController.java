package edu.m.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping
  public List<Item> getAll() {
    return itemService.getAll();
  }

  @GetMapping("/{id}")
  public Item get(@PathVariable String id) {
    return itemService.get(id);
  }

  @PostMapping
  public Item create(@RequestBody Item item) {
    return itemService.create(item);
  }

  @PutMapping("/{id}")
  public Item update(@PathVariable String id, @RequestBody Item item) {
    item.setId(id);
    return itemService.update(item);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    Item item = itemService.get(id);
    itemService.delete(item);
  }

}
