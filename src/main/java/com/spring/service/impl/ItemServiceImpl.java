package com.spring.service.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.spring.config.CacheConfiguration.*;
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
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService{
	
	private final ProductItemRepository productItemRepository;
	private final ProductItemMapper productItemMapper;

	@Override
	@Transactional
	public Item create(@NotNull @Valid CreateItemRequest createItemRequest) {
		return productItemMapper.map(
				productItemRepository.save(productItemMapper.map(createItemRequest)));
	}

	@Override
	@Cacheable(value = ITEM_CACHE)
	public Item getOne(@NotNull Long id) {
		return productItemMapper.map(productItemRepository.getById(id));
	}

	@Override
	public Page<Item> findAll(@NotNull Pageable pageable) {
		return productItemMapper.map(productItemRepository.findAll(pageable));
	}

	@Override
	@Transactional
	@CacheEvict(value = ITEM_CACHE)
	public Item update(@NotNull Long id, @Valid UpdateItemRequest updateItemRequest) {
		final ProductItem productItem = productItemRepository.getById(id);
		
		productItemMapper.map(productItem,updateItemRequest);
		
		return productItemMapper.map(productItemRepository.save(productItem));		
	}

	@Override
	@Transactional
	@CacheEvict(value = ITEM_CACHE)
	public void delete(@NotNull Long id) {
		
		productItemRepository.delete(productItemRepository.getById(id));		
	}

}
