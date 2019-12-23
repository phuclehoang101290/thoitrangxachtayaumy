package guru.springframework.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.News;

/**
 * Created by jt on 1/10/17.
 */
public interface NewsRepository extends CrudRepository<News, String>{
	
}
