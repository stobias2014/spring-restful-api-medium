package com.tobias.saul.springrestfulapimedium.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.tobias.saul.springrestfulapimedium.entity.Stock;
import com.tobias.saul.springrestfulapimedium.web.service.StockWebController;

@Component
public class StockModelAssembler implements RepresentationModelAssembler<Stock, EntityModel<Stock>>{

	@Override
	public EntityModel<Stock> toModel(Stock stock) {
		
		return EntityModel.of(stock, 
				linkTo(methodOn(StockWebController.class).getStock(stock.getStockId())).withSelfRel(),
				linkTo(methodOn(StockWebController.class).getAllStocks()).withRel("stocks"));
	}

}
