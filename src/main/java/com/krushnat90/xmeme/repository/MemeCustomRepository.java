package com.krushnat90.xmeme.repository;

import java.util.List;
import java.util.Optional;

import com.krushnat90.xmeme.model.Meme;

public interface MemeCustomRepository {
	
	String getMemeId();
	
	String addMeme(Meme meme);
	
	List<Meme> getLatestMemes();
	
	Optional<Meme> getMemeById(String id);
}
