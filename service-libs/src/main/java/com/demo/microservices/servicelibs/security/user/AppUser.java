package com.demo.microservices.servicelibs.security.user;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class AppUser {

  private Long id;

  @NonNull
  private String username;

  private String name;

  @NonNull
  private String email;

  private String imageUrl;

  private Boolean active;

  private List<String> roles;
}
