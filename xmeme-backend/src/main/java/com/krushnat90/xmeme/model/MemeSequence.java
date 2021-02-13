package com.krushnat90.xmeme.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Krishnakant Thakur
 *
 */
@Document(collection="MemeIDCounter")
public class MemeSequence {
	
	private String id;
	private Long sequencevalue;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getSeqValue() {
		return sequencevalue;
	}
	public void setSeqValue(Long seqValue) {
		this.sequencevalue = seqValue;
	}
	
	

}
