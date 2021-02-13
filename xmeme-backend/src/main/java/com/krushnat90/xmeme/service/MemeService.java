package com.krushnat90.xmeme.service;

import java.util.List;
import java.util.Optional;

import com.krushnat90.xmeme.model.Meme;

/**
 * @author Krishnakant Thakur
 * 
 * Logic to parse incoming data and perform database transactions
 * 
 */
public interface MemeService {

	/**
	 * Methods generated new ID and performs Add operation to the database
	 * 
	 * @param meme : object contains name, caption and url of the meme.
	 * @return : ID of the added memes
	 * 
	 */
	Long addMeme(Meme meme);
	
	
	
	/**
	 * Fetches meme from the database based on the ID
	 * 
	 * @param memeId : ID of the meme
	 * @return : Optional object of Meme
	 */
	Optional<Meme> getMemeById(Long memeId);

	
	
	/**
	 * 
	 * Fetches latest 100 memes from the database
	 * 
	 * @return : List of latest memes 
	 */
	List<Meme> getLatestMemes();
	
	
	
	/**
	 * 
	 * Fetches latest 100 memes from the database based on name
	 * 
	 * @return : List of latest memes based on name
	 */
	List<Meme> getLatestMemes(String name);
	
	
	
	/**
	 * 
	 * Updates existing meme with new caption and URL
	 * 
	 * @param meme : Meme object with url and caption to be updated
	 * @return : 1 if the update was successful and 0 if the meme is not present
	 */
	int updateMemeById(Meme meme);
	
	
	
	
	/**
	 * Checks if the mentioned meme url is duplicate.
	 * 
	 * @param meme : Object with name, caption and url of the meme
	 * @return : boolean result whether url is duplicate or not. True if it is duplicate.
	 */
	boolean checkDuplicateMeme(Meme meme);
	
	
	/**
	 * Checks for blank name, url and caption
	 * 
	 * @param meme
	 * @return true if input is invalid otherwise false
	 */
	boolean checkForInvalidInput(Meme meme, String operation);
}
