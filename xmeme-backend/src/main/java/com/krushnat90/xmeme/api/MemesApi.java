/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.24).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.krushnat90.xmeme.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.krushnat90.xmeme.model.Meme;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author Krishnakant Thakur
 *
 */
public interface MemesApi {

    @Operation(summary = "Add new Meme", description = "", tags={ "XMemeMain" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Meme Added successfully", content = @Content(schema = @Schema(implementation = Long.class))),
        
        @ApiResponse(responseCode = "405", description = "Invalid input"),
        @ApiResponse(responseCode = "409", description = "Conflicting with existing URL")})
    @RequestMapping(value = "/memes",
    	consumes = { "application/json" }, 
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<ObjectNode> addMeme(@NotNull @Valid @RequestBody Meme meme);


    @Operation(summary = "Retrieve latest Memes", description = "This endpoint will retrieve latest 100 memes", tags={ "XMemeMain" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Memes retrieved successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Meme.class)))) })
    @RequestMapping(value = "/memes",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Meme>> findLatestMemes();


    @Operation(summary = "Fetch a meme using its unique ID", description = "Returns a single Meme.", tags={ "XMemeMain" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Meme retrieved successfully", content = @Content(schema = @Schema(implementation = Meme.class))),
        
        @ApiResponse(responseCode = "404", description = "There is no Meme present for the given Meme ID") })
    @RequestMapping(value = "/memes/{memeId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Meme> getMemeById(@Parameter(in = ParameterIn.PATH, description = "ID of Meme to return", required=true, schema=@Schema()) @PathVariable("memeId") Long memeId);
    
    @Operation(summary = "Update a meme using its unique ID", description = "Updates a single Meme.", tags={ "XMemeMain" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Meme updated successfully", content = @Content(schema = @Schema(implementation = Meme.class))),
        
        @ApiResponse(responseCode = "404", description = "There is no Meme present for the given Meme ID"),
        @ApiResponse(responseCode = "405", description = "Invalid input"),
        @ApiResponse(responseCode = "409", description = "Conflicting with existing URL")})
    @RequestMapping(value = "/memes/{memeId}",
        consumes = { "application/json" }, 
        method = RequestMethod.PATCH)
    ResponseEntity<Meme> updateMemeById(@Parameter(in = ParameterIn.PATH, description = "ID of Meme to return", required=true, schema=@Schema()) @PathVariable("memeId") Long memeId, @NotNull @Valid @RequestBody Meme meme);
    
    
    @Operation(summary = "Fetch memes using its poster name", description = "Returns a list of Memes.", tags={ "XMemeMain" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Memes retrieved successfully", content = @Content(schema = @Schema(implementation = Meme.class))),
        
        @ApiResponse(responseCode = "404", description = "There is no Meme present for the given name") })
    @RequestMapping(value = "/memes/findByName/{memeName}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Meme>> getMemeByName(@Parameter(in = ParameterIn.PATH, description = "ID of Meme to return", required=true, schema=@Schema()) @PathVariable("memeName") String memeName);

}

