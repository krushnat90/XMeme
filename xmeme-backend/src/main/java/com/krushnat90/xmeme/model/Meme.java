package com.krushnat90.xmeme.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Meme
 */
@Validated
	
@Document(collection = "MemeMaster")
public class Meme {

	@Id
	@JsonProperty("memeId")
	private Long id;

	/*@JsonProperty("memeId")
	private Long memeId = null;*/

	@JsonProperty("memeName")
	private String memeName = null;

	@JsonProperty("memeUrl")
	private String memeUrl = null;

	@JsonProperty("memeCaption")
	private String memeCaption = null;

	@JsonProperty("likes")
	private Long likes = null;

	@JsonProperty("dislikes")
	private Long dislikes = null;

	public Meme memeId(Long memeId) {
		this.id = memeId;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get memeId
	 * 
	 * @return memeId
	 **/
	
	
	
	@Schema(description = "")

	/*public Long getMemeId() {
		return memeId;
	}

	public void setMemeId(Long memeId) {
		this.memeId = memeId;
	}*/

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

	public Meme likes(Long likes) {
		this.likes = likes;
		return this;
	}

	/**
	 * Get likes
	 * 
	 * @return likes
	 **/
	@Schema(description = "")

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public Meme dislikes(Long dislikes) {
		this.dislikes = dislikes;
		return this;
	}

	/**
	 * Get dislikes
	 * 
	 * @return dislikes
	 **/
	@Schema(description = "")

	public Long getDislikes() {
		return dislikes;
	}

	public void setDislikes(Long dislikes) {
		this.dislikes = dislikes;
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
				&& Objects.equals(this.memeUrl, meme.memeUrl) && Objects.equals(this.memeCaption, meme.memeCaption)
				&& Objects.equals(this.likes, meme.likes) && Objects.equals(this.dislikes, meme.dislikes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, memeName, memeUrl, memeCaption, likes, dislikes);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Meme {\n");

		sb.append("    memeId: ").append(toIndentedString(id)).append("\n");
		sb.append("    memeName: ").append(toIndentedString(memeName)).append("\n");
		sb.append("    memeUrl: ").append(toIndentedString(memeUrl)).append("\n");
		sb.append("    memeCaption: ").append(toIndentedString(memeCaption)).append("\n");
		sb.append("    likes: ").append(toIndentedString(likes)).append("\n");
		sb.append("    dislikes: ").append(toIndentedString(dislikes)).append("\n");
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
