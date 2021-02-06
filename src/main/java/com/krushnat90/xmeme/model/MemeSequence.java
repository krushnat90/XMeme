package com.krushnat90.xmeme.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="MemeIDCounter")
public class MemeSequence {
	
	private String id;
	private String sequencevalue;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSeqValue() {
		return sequencevalue;
	}
	public void setSeqValue(String seqValue) {
		this.sequencevalue = seqValue;
	}
	
	

}
