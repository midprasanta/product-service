package com.spring.service.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.dto.CreateItemRequest;
import com.spring.dto.UpdateItemRequest;
import com.spring.entity.Item;
import com.spring.entity.ProductItem;
import com.spring.mapper.ProductItemMapper;
import com.spring.repository.ProductItemRepository;
import com.spring.service.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
	
	private final ProductItemRepository productItemRepository;
	private final ProductItemMapper productItemMapper;

	@Override
	public Item create(@NotNull @Valid CreateItemRequest createItemRequest) {
		return productItemMapper.map(
				productItemRepository.save(productItemMapper.map(createItemRequest)));
	}

	@Override
	public Item getOne(@NotNull Long id) {
		return productItemMapper.map(productItemRepository.getOne(id));
	}

	@Override
	public Page<Item> findAll(@NotNull Pageable pageable) {
		return productItemMapper.map(productItemRepository.findAll(pageable));
	}

	@Override
	public Item update(@NotNull Long id, @Valid UpdateItemRequest updateItemRequest) {
		final ProductItem productItem = productItemRepository.getOne(id);
		
		productItemMapper.map(productItem,updateItemRequest);
		
		return productItemMapper.map(productItemRepository.save(productItem));		
	}

	@Override
	public void delete(@NotNull Long id) {
		
		productItemRepository.delete(productItemRepository.getOne(id));		
	}

}
