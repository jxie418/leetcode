package com.jing.xie.hb;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jing.xie.bean.Person;

@RestController
@RequestMapping("/newperson")
public class PersonController {
  @Autowired
  private IPersonService personService;

  @RequestMapping("/person")
  @Produces("application/json")
  public Person getPersonDetail(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
    Person p = personService.getPersonDetail(id);
    return p;
  }
}
