package com.krushnat90.xmeme.service;

import java.util.List;
import java.util.Optional;

import com.krushnat90.xmeme.model.Meme;

public interface MemeService {

	Long addMeme(Meme meme);

	Optional<Meme> getMemeById(String memeId);

	List<Meme> getLatestMemes();

}
