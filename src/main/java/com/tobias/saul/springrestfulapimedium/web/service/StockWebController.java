package com.tobias.saul.springrestfulapimedium.web.service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tobias.saul.springrestfulapimedium.assembler.StockModelAssembler;
import com.tobias.saul.springrestfulapimedium.entity.Stock;
import com.tobias.saul.springrestfulapimedium.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockWebController {

	private final StockService stockService;
	private final StockModelAssembler assembler;

	@Autowired
	public StockWebController(StockService stockService, StockModelAssembler assembler) {
		this.stockService = stockService;
		this.assembler = assembler;
	}

	@GetMapping
	public CollectionModel<EntityModel<Stock>> getAllStocks() {

		List<EntityModel<Stock>> stocks = stockService.getAllStocks().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(stocks, linkTo(methodOn(StockWebController.class).getAllStocks()).withSelfRel());
	}

	@GetMapping("/{stockId}")
	public EntityModel<Stock> getStock(@PathVariable("stockId") Long stockId) {
		Stock stock = stockService.getStock(stockId);
		
		return assembler.toModel(stock); 
	}

	@PostMapping
	public ResponseEntity<?> createStock(@RequestBody Stock stock) {
		
		EntityModel<Stock> stockModel = assembler.toModel(stockService.addStock(stock));
		
		return ResponseEntity.created(stockModel.getRequiredLink(IanaLinkRelations.SELF)
				.toUri()).body(stockModel);
	}

	@PutMapping
	public ResponseEntity<?> updateStock(@RequestBody Stock stock) {
		
		EntityModel<Stock> stockModel = assembler.toModel(stockService.updateStock(stock));
		
		return ResponseEntity.created(stockModel.getRequiredLink(IanaLinkRelations.SELF)
				.toUri()).body(stockModel);
	}

	@DeleteMapping("/{stockId}")
	public ResponseEntity<?> deleteStock(@PathVariable("stockId") Long stockId) {
		stockService.deleteStock(stockId);
		
		return ResponseEntity.noContent().build();
	}

}
