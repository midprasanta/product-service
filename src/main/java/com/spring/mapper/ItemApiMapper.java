package com.spring.mapper;

import org.springframework.stereotype.Component;

import com.spring.dto.CreateItemDto;
import com.spring.dto.CreateItemRequest;
import com.spring.dto.UpdateItemDto;
import com.spring.dto.UpdateItemRequest;

@Component
public class ItemApiMapper {

	public CreateItemRequest map(CreateItemDto createItemDto) {
		return CreateItemRequest.builder().title(createItemDto.getTitle()).build();

	}

	public UpdateItemRequest map(UpdateItemDto updateItemDto) {
		return UpdateItemRequest.builder().title(updateItemDto.getTitle()).build();

	}

}
