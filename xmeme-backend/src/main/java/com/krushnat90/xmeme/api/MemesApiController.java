package com.krushnat90.xmeme.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.krushnat90.xmeme.common.Constants;
import com.krushnat90.xmeme.model.Meme;
import com.krushnat90.xmeme.service.MemeService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Krishnakant Thakur
 *
 */
@RestController
public class MemesApiController implements MemesApi {

	private static final Logger log = LoggerFactory.getLogger(MemesApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	MemeService memeService;

	@Autowired
	public MemesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ObjectNode> addMeme(@NotNull @RequestBody Meme meme) {

		try {
			
			//check for invalid input
			if(memeService.checkForInvalidInput(meme, Constants.ADD)){
				log.error("Meme input is invalid " + meme.toString());
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			// check duplicate
			if (memeService.checkDuplicateMeme(meme)) {
				log.error("Meme already present for " + meme.getMemeUrl());
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}

			// add to database of not duplicate
			Long id = memeService.addMeme(meme);
			return new ResponseEntity<ObjectNode>(objectMapper.createObjectNode().put(Constants.RESPONSE_ID, id),
					HttpStatus.OK);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<ObjectNode>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<List<Meme>> findLatestMemes() {
		try {
			return new ResponseEntity<List<Meme>>(memeService.getLatestMemes(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<List<Meme>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<Meme> getMemeById(
			@Parameter(in = ParameterIn.PATH, description = "ID of Meme to return", required = true, schema = @Schema()) @PathVariable("memeId") Long memeId) {
		try {
			Optional<Meme> meme = memeService.getMemeById(memeId);
			if (meme.isPresent())
				return new ResponseEntity<Meme>(meme.get(), HttpStatus.OK);
			else
				return new ResponseEntity<Meme>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error("Couldn't serialize response for content type application/json", e);
			return new ResponseEntity<Meme>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	public ResponseEntity<Meme> updateMemeById(
			@Parameter(in = ParameterIn.PATH, description = "ID of Meme to return", required = true, schema = @Schema()) @PathVariable("memeId") Long memeId,
			@NotNull @RequestBody Meme meme) {
		meme.memeId(memeId);
		
		//check for invalid input
		if(memeService.checkForInvalidInput(meme, Constants.UPDATE)){
			log.error("Meme input is invalid " + meme.toString());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		//update
		int updateReponse = memeService.updateMemeById(meme);
		if (updateReponse == 1) {
			return new ResponseEntity<Meme>(HttpStatus.OK);
		}
		else if(updateReponse == 2){
			return new ResponseEntity<Meme>(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<Meme>(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ResponseEntity<List<Meme>> getMemeByName(String memeName) {
		if(StringUtils.isBlank(memeName)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<Meme> memeList = memeService.getLatestMemes(memeName);
		if(memeList.size() > 0)
			return new ResponseEntity<List<Meme>>(memeList, HttpStatus.OK);
			
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
