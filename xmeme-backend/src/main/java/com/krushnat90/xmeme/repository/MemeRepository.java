package com.krushnat90.xmeme.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.krushnat90.xmeme.model.Meme;

/**
 * @author Krishnakant Thakur
 *
 */
@Repository
public interface MemeRepository extends MongoRepository<Meme, Long> {

}
