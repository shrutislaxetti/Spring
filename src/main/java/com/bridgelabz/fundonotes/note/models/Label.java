package com.bridgelabz.fundonotes.note.models;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.elasticsearch.annotations.Document;

import io.swagger.annotations.ApiModelProperty;
@Document(indexName = "label", type = "Label")
//@Document(collection = "label")
public class Label {
	
	@Id
	@ApiModelProperty(hidden=true)
	private String labelId;
	private String labelName;
	@ApiModelProperty(hidden=true)
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
}
