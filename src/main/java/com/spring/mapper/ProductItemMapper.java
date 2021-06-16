package com.spring.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.spring.dto.CreateItemRequest;
import com.spring.dto.UpdateItemRequest;
import com.spring.entity.Item;
import com.spring.entity.ProductItem;

@Component
public class ProductItemMapper {
	
	public Item map(ProductItem productItem) {
		return Item.builder()
			.id(productItem.getId())
			.title(productItem.getTitle())
			.build();
	}
	
	public ProductItem map(CreateItemRequest createItemRequest) {
		return ProductItem.builder()
						  .title(createItemRequest.getTitle())
						  .build();
	}
	
	public Page<Item> map(Page<ProductItem> page){
		return page.map(this::map);
	}
	
	public void map(ProductItem productItem, UpdateItemRequest updateItemRequest) {
		productItem.setTitle(updateItemRequest.getTitle());
	}

}
