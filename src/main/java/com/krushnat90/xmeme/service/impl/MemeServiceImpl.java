package com.krushnat90.xmeme.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krushnat90.xmeme.model.Meme;
import com.krushnat90.xmeme.repository.MemeCustomRepository;
import com.krushnat90.xmeme.service.MemeService;

@Service
public class MemeServiceImpl implements MemeService {

	private static final Logger log = LoggerFactory.getLogger(MemeServiceImpl.class);

	@Autowired
	MemeCustomRepository memeRepository;

	@Override
	@Transactional
	public Long addMeme(Meme meme) {
		log.debug("Inside Add Service : params  :" + meme.getMemeName() + "|" + meme.getMemeUrl() + "|"
				+ meme.getMemeCaption());
		meme.memeId(memeRepository.getMemeId()).likes(0L).dislikes(0L);
		Long idVal = memeRepository.addMeme(meme);
		log.debug("Exiting Add service : id :" + idVal);
		return idVal;
	}

	@Override
	public Optional<Meme> getMemeById(Long memeId) {
		log.debug("Inside get by ID service");
		return memeRepository.getMemeById(memeId);
	}

	@Override
	public List<Meme> getLatestMemes() {
		log.debug("Inside get latest service");
		return memeRepository.getLatestMemes();
	}

	@Override
	public int updateMemeById(Meme meme) {
		log.debug("Inside update service");
		Optional<Meme> memeOptional = getMemeById(meme.getId());
		if (memeOptional.isPresent()) {
			log.debug("meme found in db");
			Meme memeToUpdate = memeOptional.get();

			memeToUpdate
					.memeUrl(StringUtils.isNotBlank(meme.getMemeUrl()) ? meme.getMemeUrl() : memeToUpdate.getMemeUrl())
					.memeCaption(StringUtils.isNotBlank(meme.getMemeCaption()) ? meme.getMemeCaption()
							: memeToUpdate.getMemeCaption());

			memeRepository.updateMemeById(memeOptional.get());
			return 1;
		}
		log.debug("meme not found in db");
		return 0;
	}

}
