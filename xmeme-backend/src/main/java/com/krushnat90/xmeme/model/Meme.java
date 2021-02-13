package com.krushnat90.xmeme.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Krishnakant Thakur
 *
 */
@Validated

@Document(collection = "MemeMaster")
public class Meme {

	@Id
	@JsonProperty("id")
	private Long id;

	/*
	 * @JsonProperty("memeId") private Long memeId = null;
	 */

	@JsonProperty("name")
	private String memeName = null;

	@JsonProperty("url")
	private String memeUrl = null;

	@JsonProperty("caption")
	private String memeCaption = null;

	public Meme memeId(Long memeId) {
		this.id = memeId;
		return this;
	}

	/**
	 * Get memeId
	 * 
	 * @return memeId
	 **/
	@Schema(description = "meme id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Meme memeName(String memeName) {
		this.memeName = memeName;
		return this;
	}

	/**
	 * Meme name
	 * 
	 * @return memeName
	 **/
	@Schema(description = "Meme name")

	public String getMemeName() {
		return memeName;
	}

	public void setMemeName(String memeName) {
		this.memeName = memeName;
	}

	public Meme memeUrl(String memeUrl) {
		this.memeUrl = memeUrl;
		return this;
	}

	/**
	 * Meme URL
	 * 
	 * @return memeUrl
	 **/
	@Schema(description = "Meme URL")

	public String getMemeUrl() {
		return memeUrl;
	}

	public void setMemeUrl(String memeUrl) {
		this.memeUrl = memeUrl;
	}

	public Meme memeCaption(String memeCaption) {
		this.memeCaption = memeCaption;
		return this;
	}

	/**
	 * Meme caption
	 * 
	 * @return memeCaption
	 **/
	@Schema(description = "Meme caption")

	public String getMemeCaption() {
		return memeCaption;
	}

	public void setMemeCaption(String memeCaption) {
		this.memeCaption = memeCaption;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Meme meme = (Meme) o;
		return Objects.equals(this.id, meme.id) && Objects.equals(this.memeName, meme.memeName)
				&& Objects.equals(this.memeUrl, meme.memeUrl) && Objects.equals(this.memeCaption, meme.memeCaption);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, memeName, memeUrl, memeCaption);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Meme {\n");

		sb.append("    memeId: ").append(toIndentedString(id)).append("\n");
		sb.append("    memeName: ").append(toIndentedString(memeName)).append("\n");
		sb.append("    memeUrl: ").append(toIndentedString(memeUrl)).append("\n");
		sb.append("    memeCaption: ").append(toIndentedString(memeCaption)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
