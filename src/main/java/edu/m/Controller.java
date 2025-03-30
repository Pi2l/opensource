package edu.m;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

  @GetMapping("/admin")
  public String getAdmin() {
    return "Admin";
  }

  @GetMapping("/user")
  public String getUser() {
    return "User";
  }

  @GetMapping("/guest")
  public String getGuest() {
    return "Guest";
  }
}
