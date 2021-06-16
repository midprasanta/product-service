package com.spring.controller;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.CreateItemDto;
import com.spring.dto.UpdateItemDto;

import com.spring.entity.Item;

import com.spring.mapper.ItemApiMapper;
import com.spring.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = ItemController.ENDPOINT, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ItemController {

	public static final String ENDPOINT = "/items";
	public static final String ENDPOINT_BY_ID = "/{id}";

	private final ItemService service;
	private final ItemApiMapper mapper;

	@ResponseStatus(code = CREATED)
	@PostMapping
	public Item create(@RequestBody CreateItemDto createItemDto) {
		return service.create(mapper.map(createItemDto));
	}

	@GetMapping(value = ENDPOINT_BY_ID)
	public Item get(@PathVariable Long id) {
		return service.getOne(id);
	}

	@GetMapping
	public Page<Item> find(@PageableDefault(sort = "id") Pageable pageable) {
		return service.findAll(pageable);
	}

	@PutMapping(value = ENDPOINT_BY_ID)
	public Item update(@PathVariable Long id, @RequestBody UpdateItemDto updateItemDto) {
		return service.update(id, mapper.map(updateItemDto));
	}

	@ResponseStatus(value = NO_CONTENT)
	@DeleteMapping(value = ENDPOINT_BY_ID)
	public void delete(@PathVariable Long id) {
		service.delete(id);

	}

}
