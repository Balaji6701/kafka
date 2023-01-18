package com.cts.approval.audit;

import java.time.LocalDateTime;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Auditable<T> {

	protected LocalDateTime createdAt;
	protected T createdBy;
	protected LocalDateTime lastModifiedAt;
	protected T lastModifiedBy;
}
