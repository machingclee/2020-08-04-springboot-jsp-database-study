package com.springboot.mvc.Interfaces;

import java.util.List;

public interface IDAO<T> {
  public List<T> getAll();

  public void add(T object);

}