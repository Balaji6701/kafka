package com.balaji.cosmos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalResponse {
	
	public String approvalStatus;
	public String message;
}
