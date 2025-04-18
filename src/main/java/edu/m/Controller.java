package edu.m;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

  @GetMapping("/admin")
  @PreAuthorize("hasRole('client_admin')")
  public String getAdmin() {
    return "Hello Admin";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('client_user')")
  public String getUser() {
    return "Hello User";
  }

  @GetMapping("/guest")
  public String getGuest() {
    return "Guest";
  }
}
