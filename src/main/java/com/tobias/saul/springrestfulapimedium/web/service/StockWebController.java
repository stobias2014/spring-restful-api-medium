package com.tobias.saul.springrestfulapimedium.web.service;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tobias.saul.springrestfulapimedium.entity.Stock;
import com.tobias.saul.springrestfulapimedium.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockWebController {
	
	private final StockService stockService;
	
	public StockWebController(StockService stockService) {
		this.stockService = stockService;
	}
	
	@GetMapping
	public List<Stock> getAllStocks() {
		return stockService.getAllStocks();
	}
	
	@GetMapping("/{stockId}")
	public Stock getStock(@PathVariable("stockId") Long stockId) {
		return stockService.getStock(stockId);
	}
	
	@PostMapping
	public Stock createStock(@RequestBody Stock stock) {
		return stockService.addStock(stock);
	}
	
	@PutMapping
	public Stock updateStock(@RequestBody Stock stock) {
		return stockService.updateStock(stock);
	}
	
	@DeleteMapping("/{stockId}")
	public void deleteStock(@PathVariable("stockId") Long stockId) {
		stockService.deleteStock(stockId);
	}

}
