/**
 * 
 */
package com.mastertheboss;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mastertheboss.bo.StockBo;
import com.mastertheboss.domain.Stock;

/**
 * @author JXie
 *
 */
public class AppSpring {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    ApplicationContext appContext = new ClassPathXmlApplicationContext("BeanLocations.xml");

    StockBo stockBo = (StockBo) appContext.getBean("stockBo");

    /** insert **/
    /*Stock stock = new Stock();
    stock.setStockCode("7668");
    stock.setStockName("HAIO");
    stockBo.save(stock);*/

    /** select **/
    Stock stock2 = stockBo.findByStockCode("7052");
    System.out.println(stock2);

    /** update **/
    // stock2.setStockName("HAIO-1");
    // stockBo.update(stock2);

    /** delete **/
    // stockBo.delete(stock2);

    System.out.println("Done");
  }

}
