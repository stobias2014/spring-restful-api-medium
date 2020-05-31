package com.tobias.saul.springrestfulapimedium.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobias.saul.springrestfulapimedium.entity.Stock;
import com.tobias.saul.springrestfulapimedium.exception.StockNotFoundException;
import com.tobias.saul.springrestfulapimedium.repository.StockRepository;

@Service
public class StockService {

	private final StockRepository stockRepository;

	@Autowired
	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	public Stock addStock(Stock stock) {
		return stockRepository.save(stock);
	}

	public Stock getStock(Long stockId) {
		return stockRepository.findById(stockId)
				.orElseThrow(() -> new StockNotFoundException("Stock [" + stockId + "] not found."));
	}

	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}

	public Stock updateStock(Stock stock) {
		if (stockRepository.existsById(stock.getStockId())) {
			return stockRepository.save(stock);
		} else {
			throw new StockNotFoundException("Stock [" + stock.getStockId() + "] not found");
		}
	}

	public void deleteStock(Long stockId) {
		if (stockRepository.existsById(stockId)) {
			stockRepository.deleteById(stockId);
		} else {
			throw new StockNotFoundException("Stock [" + stockId + "] not found");
		}
	}
}
