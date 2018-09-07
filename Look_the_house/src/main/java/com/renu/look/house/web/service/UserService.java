
package com.renu.look.house.web.service;

import com.renu.look.house.models.User;

public interface UserService {
  void saveUser(User user,String[] roles);
  User findByUsername(String username);
}
