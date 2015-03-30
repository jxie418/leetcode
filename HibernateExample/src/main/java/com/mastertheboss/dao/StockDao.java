package com.mastertheboss.dao;

import com.mastertheboss.domain.Stock;

public interface StockDao {
  void save(Stock stock);

  void update(Stock stock);

  void delete(Stock stock);

  Stock findByStockCode(String stockCode);
}
