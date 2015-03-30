/**
 * 
 */
package com.mastertheboss.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
/**
 * @author JXie
 *
 */
public abstract class CustomHibernateDaoSupport extends HibernateDaoSupport {
  @Autowired
  public void anyMethodName(SessionFactory sessionFactory) {
    setSessionFactory(sessionFactory);
  }
}
