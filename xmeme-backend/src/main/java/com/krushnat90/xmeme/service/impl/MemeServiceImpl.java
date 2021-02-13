package com.krushnat90.xmeme.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krushnat90.xmeme.common.Constants;
import com.krushnat90.xmeme.model.Meme;
import com.krushnat90.xmeme.repository.MemeCustomRepository;
import com.krushnat90.xmeme.service.MemeService;

/**
 * @author Krishnakant Thakur
 *
 */
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

		// generate new ID
		meme.memeId(memeRepository.getMemeId());

		// perform add
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

		// check if meme is present with ID
		Optional<Meme> memeOptional = getMemeById(meme.getId());

		// perform update if present
		if (memeOptional.isPresent()) {
			log.debug("meme found in db");
			Meme memeToUpdate = memeOptional.get();

			// check if conflict is there
			if (StringUtils.isNotBlank(meme.getMemeUrl())
					&& !(meme.getMemeUrl().equalsIgnoreCase(memeToUpdate.getMemeUrl()))) {
				memeToUpdate.memeUrl(meme.getMemeUrl());
				if (checkDuplicateMeme(memeToUpdate))
					return 2;
			}

			// if no conflict then perform the update
			memeToUpdate
					.memeUrl(StringUtils.isNotBlank(meme.getMemeUrl()) ? meme.getMemeUrl() : memeToUpdate.getMemeUrl())
					.memeCaption(StringUtils.isNotBlank(meme.getMemeCaption()) ? meme.getMemeCaption()
							: memeToUpdate.getMemeCaption());

			memeRepository.updateMemeById(memeOptional.get());
			return 1;
		}

		// return 0 if not present
		log.debug("meme not found in db");
		return 0;
	}

	@Override
	public boolean checkDuplicateMeme(Meme meme) {

		return (memeRepository.checkDuplicateMeme(meme) > 0L);
	}

	@Override
	public boolean checkForInvalidInput(Meme meme, String operation) {

		if (Constants.ADD.equals(operation) && (StringUtils.isBlank(meme.getMemeName())
				|| StringUtils.isBlank(meme.getMemeUrl()) || StringUtils.isBlank(meme.getMemeCaption()))) {
			return true;
		}

		if (Constants.UPDATE.equals(operation) && ((meme.getId() == null || meme.getId() == 0L)
				|| (StringUtils.isBlank(meme.getMemeUrl()) && StringUtils.isBlank(meme.getMemeCaption())))) {
			return true;
		}

		return false;
	}

	@Override
	public List<Meme> getLatestMemes(String name) {
		log.debug("Inside get latest service");
		return memeRepository.getLatestMemes(name);
	}

}
