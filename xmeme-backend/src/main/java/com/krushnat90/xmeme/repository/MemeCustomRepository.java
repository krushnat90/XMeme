package com.krushnat90.xmeme.repository;

import java.util.List;
import java.util.Optional;

import com.krushnat90.xmeme.model.Meme;

/**
 * @author Krishnakant Thakur
 *
 */
public interface MemeCustomRepository {
	
	/**
	 * Get new unique ID from database
	 * 
	 * @return : ID for the new meme
	 */
	Long getMemeId();
	
	
	/**
	 * Persist meme to the database
	 * 
	 * @param meme
	 * @return : ID of the new added meme
	 */
	Long addMeme(Meme meme);
	
	
	/**
	 * Get latest memes from database
	 * 
	 * @return : List of latest memes
	 */
	List<Meme> getLatestMemes();
	
	/**
	 * Get latest memes from database based on memer name
	 * 
	 * @return : List of latest memes of poster
	 */
	List<Meme> getLatestMemes(String name);
	
	
	/**
	 * Get meme from database based on ID
	 * 
	 * @param id
	 * @return : Meme object
	 */
	Optional<Meme> getMemeById(Long id);
	
	
	/**
	 * Persists meme object to database
	 * 
	 * @param meme
	 */
	void updateMemeById(Meme meme);
	
	
	/**
	 * Queries database to check duplicate meme
	 * 
	 * @param meme
	 * @return
	 */
	Long checkDuplicateMeme(Meme meme);
}
