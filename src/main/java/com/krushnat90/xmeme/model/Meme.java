package com.krushnat90.xmeme.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Meme
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-05T16:33:47.740Z[GMT]")


public class Meme   {
  @JsonProperty("memeId")
  private Long memeId = null;

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
    this.memeId = memeId;
    return this;
  }

  /**
   * Get memeId
   * @return memeId
   **/
  @Schema(description = "")
  
    public Long getMemeId() {
    return memeId;
  }

  public void setMemeId(Long memeId) {
    this.memeId = memeId;
  }

  public Meme memeName(String memeName) {
    this.memeName = memeName;
    return this;
  }

  /**
   * Meme name
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
    return Objects.equals(this.memeId, meme.memeId) &&
        Objects.equals(this.memeName, meme.memeName) &&
        Objects.equals(this.memeUrl, meme.memeUrl) &&
        Objects.equals(this.memeCaption, meme.memeCaption) &&
        Objects.equals(this.likes, meme.likes) &&
        Objects.equals(this.dislikes, meme.dislikes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(memeId, memeName, memeUrl, memeCaption, likes, dislikes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Meme {\n");
    
    sb.append("    memeId: ").append(toIndentedString(memeId)).append("\n");
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
