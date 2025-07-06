package com.ahumada.shop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	private boolean success;
	private int status;
	private String message;
	private String developerMessage;
	private String apiVersion;
	private String path;
	private String method;
	private String correlationId;
	private long elapsedTimeMs;
	private Instant timestamp;
	private T data;
	private List<ApiError> errors;
	private Pagination pagination;
	private List<Link> links;
	private List<String> warnings;
	private CacheInfo cache;
}
