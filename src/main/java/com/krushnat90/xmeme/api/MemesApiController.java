package com.krushnat90.xmeme.api;

import com.krushnat90.xmeme.model.Meme;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-02-05T16:33:47.740Z[GMT]")
@RestController
public class MemesApiController implements MemesApi {

    private static final Logger log = LoggerFactory.getLogger(MemesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public MemesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Long> addMeme(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Name of the Meme poster" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @Parameter(in = ParameterIn.QUERY, description = "URL of the meme image" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "url", required = true) String url,@NotNull @Parameter(in = ParameterIn.QUERY, description = "caption for the meme" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "caption", required = true) String caption) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Long>(objectMapper.readValue("0", Long.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Long>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Long>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Meme>> findLatestMemes() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Meme>>(objectMapper.readValue("[ {\n  \"memeUrl\" : \"memeUrl\",\n  \"memeName\" : \"memeName\",\n  \"memeId\" : 0,\n  \"dislikes\" : 1,\n  \"memeCaption\" : \"memeCaption\",\n  \"likes\" : 6\n}, {\n  \"memeUrl\" : \"memeUrl\",\n  \"memeName\" : \"memeName\",\n  \"memeId\" : 0,\n  \"dislikes\" : 1,\n  \"memeCaption\" : \"memeCaption\",\n  \"likes\" : 6\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Meme>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Meme>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Meme> getMemeById(@Parameter(in = ParameterIn.PATH, description = "ID of Meme to return", required=true, schema=@Schema()) @PathVariable("memeId") Long memeId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Meme>(objectMapper.readValue("{\n  \"memeUrl\" : \"memeUrl\",\n  \"memeName\" : \"memeName\",\n  \"memeId\" : 0,\n  \"dislikes\" : 1,\n  \"memeCaption\" : \"memeCaption\",\n  \"likes\" : 6\n}", Meme.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Meme>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Meme>(HttpStatus.NOT_IMPLEMENTED);
    }

}
