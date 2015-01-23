/**
 * 
 */
package com.jing.xie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jing.xie.hb.TodoDao;
import com.jing.xie.jpa.Todos;

/**
 * @author jxie
 * 
 */
@Controller
public class SpringFirst {

  @Autowired TodoDao todoDaoImpl;
  
  @RequestMapping("/welcome")
  public ModelAndView helloWorld() {
    Todos todos = new Todos(todoDaoImpl.findById());
    String message = "<br><div align='center'>" + todos.size()
        + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is comming from CrunchifyHelloWorld.java **********<br><br>";
    return new ModelAndView("welcome", "message", message);
  }
}
