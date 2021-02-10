package com.krushnat90.xmeme.service;

import java.util.List;
import java.util.Optional;

import com.krushnat90.xmeme.model.Meme;

public interface MemeService {

	Long addMeme(Meme meme);

	Optional<Meme> getMemeById(Long memeId);

	List<Meme> getLatestMemes();
	
	int updateMemeById(Meme meme);
}
