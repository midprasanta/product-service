package com.spring.dto;

import javax.validation.constraints.Size;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateItemRequest {

	@NotNull
	@Size(min = 1, max = 255)
	private String title;

}
