package com.matheusResio.workshopmongo.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheusResio.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	@Query("{ 'title': { $regex: ?0, $options: 'i'} }")
	List<Post> searchTitle(String text);
	
	
	
	@Query("{ $and: [ "
			+ "{ date: {$gte: ?1} }, "
			+ "{ date: { $lte: ?2} } , "
			+ "{ $or: [ "
			+ "{ 'title': { $regex: ?0, $options: 'i' } }, "
			+ "{ 'body': { $regex: ?0, $options: 'i' } }, "
			+ "{ 'comments.text': { $regex: ?0, $options: 'i' } }"
			+ " ] }"
			+ " ] }")
	List<Post> fullSearch(String text,
						@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
						Instant minDate, 
						@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
						Instant maxDate);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
}
