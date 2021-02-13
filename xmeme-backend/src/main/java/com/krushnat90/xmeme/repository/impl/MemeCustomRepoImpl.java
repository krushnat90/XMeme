package com.krushnat90.xmeme.repository.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.krushnat90.xmeme.common.Constants;
import com.krushnat90.xmeme.model.Meme;
import com.krushnat90.xmeme.model.MemeSequence;
import com.krushnat90.xmeme.repository.MemeCustomRepository;
import com.krushnat90.xmeme.repository.MemeRepository;

/**
 * @author Krishnakant Thakur
 *
 */
@Repository
public class MemeCustomRepoImpl implements MemeCustomRepository {

	private static final Logger log = LoggerFactory.getLogger(MemeCustomRepoImpl.class);
	
	@Autowired
	MemeRepository memeJPARepo;

	@Autowired
	MongoTemplate mongoTemplate;

	
	@Override
	public Long getMemeId() {

		Long seqVal = mongoTemplate
				.findAndModify(new Query(Criteria.where(Constants.SEQEUNCE_ID).is(Constants.SEQEUNCE_ID_VAL)),
						new Update().inc(Constants.SEQEUNCE_VAL_COL, Constants.SEQEUNCE_VAL),
						new FindAndModifyOptions().returnNew(true), MemeSequence.class)
				.getSeqValue();
		log.debug("Retrieved index value : "+seqVal);
		return seqVal;
	}

	@Override
	public Long addMeme(Meme meme) {
		log.debug("Inside Add Repo");
		return memeJPARepo.save(meme).getId();
	}

	@Override
	public List<Meme> getLatestMemes() {
		log.debug("Inside get latest Repo");
		List<Meme> memes = mongoTemplate.find(new Query().with(new Sort(Sort.Direction.DESC, "_id")).limit(Constants.LIMIT_VAL), Meme.class);
		log.debug("Retreived "+memes.size()+" memes");
		return memes;
	}

	@Override
	public Optional<Meme> getMemeById(Long id) {
		log.debug("Inside getMemeById");
		return memeJPARepo.findById(id);
	}

	@Override
	public void updateMemeById(Meme meme) {
		mongoTemplate.save(meme);
	}

	@Override
	public Long checkDuplicateMeme(Meme meme) {
		Long occur = 0L;
		occur = mongoTemplate.count(new Query(Criteria.where(Constants.url).is(meme.getMemeUrl())),Meme.class);
		return occur;
	}

	@Override
	public List<Meme> getLatestMemes(String name) {
		log.debug("Inside get latest Repo by name");
		List<Meme> memes = mongoTemplate.find(new Query(Criteria.where(Constants.name).is(name)).with(new Sort(Sort.Direction.DESC, "_id")).limit(Constants.LIMIT_VAL), Meme.class);
		log.debug("Retreived "+memes.size()+" memes");
		return memes;
	}

}
