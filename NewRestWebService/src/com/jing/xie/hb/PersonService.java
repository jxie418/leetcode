package com.jing.xie.hb;

import org.springframework.stereotype.Component;

import com.jing.xie.bean.Person;

@Component
public class PersonService implements IPersonService {
  @Override
  public Person getPersonDetail(Integer id) {
    Person p = new Person();
    p.setId(id);
    p.setLocation("Varanasi");
    p.setName("Ram");
    return p;
  }
}
