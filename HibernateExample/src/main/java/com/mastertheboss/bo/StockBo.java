/**
 * 
 */
package com.mastertheboss.bo;

import com.mastertheboss.domain.Stock;

/**
 * @author JXie
 *
 */
public interface StockBo {
  void save(Stock stock);

  void update(Stock stock);

  void delete(Stock stock);

  Stock findByStockCode(String stockCode);
}
