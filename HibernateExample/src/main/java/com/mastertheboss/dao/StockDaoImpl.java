/**
 * 
 */
package com.mastertheboss.dao;

/**
 * @author JXie
 *
 */
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mastertheboss.domain.Stock;
import com.mastertheboss.util.CustomHibernateDaoSupport;

@Repository("stockDao")
@Transactional
public class StockDaoImpl extends CustomHibernateDaoSupport implements StockDao {

  public void save(Stock stock) {
    getHibernateTemplate().save(stock);
  }

  public void update(Stock stock) {
    getHibernateTemplate().update(stock);
  }

  public void delete(Stock stock) {
    getHibernateTemplate().delete(stock);
  }

  public Stock findByStockCode(String stockCode) {
    List list = getHibernateTemplate().find("from Stock where stockCode=?", stockCode);
    return (Stock) list.get(0);
  }

}
