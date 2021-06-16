package com.spring.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import com.spring.dto.CreateItemRequest;
import com.spring.dto.UpdateItemRequest;
import com.spring.entity.Item;

@Validated
public interface ItemService {

	Item create(@NotNull @Valid CreateItemRequest createItemRequest);

	Item getOne(@NotNull Long id);

	Page<Item> findAll(@NotNull Pageable pageable);

	Item update(@NotNull Long id, @Valid UpdateItemRequest updateItemRequest);

	void delete(@NotNull Long id);

}
