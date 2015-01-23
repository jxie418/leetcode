package com.jing.xie.hb;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jing.xie.bean.Person;
/**
@RestController
@RequestMapping("/newperson")**/
@Controller
public class PersonController {
  @Autowired
  private IPersonService personService;

  @RequestMapping(value="/person",method=RequestMethod.GET)
  @Produces(MediaType.APPLICATION_JSON)
  public @ResponseBody Person getPersonDetail(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
    Person p = personService.getPersonDetail(id);
    return p;
  }
  
  @RequestMapping("/personjson")
  @Produces(MediaType.APPLICATION_JSON)
  public String getPersonJson(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
    return "{'friends': ['Michael', 'Tom', 'Daniel', 'John', 'Nick']}";
  }
}
