package com.ahumada.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CacheInfo {

	private String etag;
	private Instant expiresAt;
	private String lastModified;
}
