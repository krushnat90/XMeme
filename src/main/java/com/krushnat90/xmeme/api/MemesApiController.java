package com.krushnat90.xmeme.api;

import com.krushnat90.xmeme.common.Constants;
import com.krushnat90.xmeme.model.Meme;
import com.krushnat90.xmeme.service.MemeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-05T16:33:47.740Z[GMT]")
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
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	Long id = memeService.addMeme(meme);
                return new ResponseEntity<ObjectNode>(objectMapper.createObjectNode().put(Constants.RESPONSE_ID, id), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<ObjectNode>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<ObjectNode>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Meme>> findLatestMemes() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Meme>>(memeService.getLatestMemes(), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Meme>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Meme>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Meme> getMemeById(@Parameter(in = ParameterIn.PATH, description = "ID of Meme to return", required=true, schema=@Schema()) @PathVariable("memeId") String memeId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	Optional<Meme> meme = memeService.getMemeById(memeId);
            	if(meme.isPresent())
            		return new ResponseEntity<Meme>(meme.get(), HttpStatus.OK);
            	else
            		return new ResponseEntity<Meme>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Meme>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Meme>(HttpStatus.NOT_IMPLEMENTED);
    }

}
