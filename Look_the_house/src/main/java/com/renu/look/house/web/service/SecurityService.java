package com.renu.look.house.web.service;

public interface SecurityService {
  String findLoggedInUsername();
  void autologin(String username, String password);
}
