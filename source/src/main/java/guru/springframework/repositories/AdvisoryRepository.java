package guru.springframework.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Advisory;

/**
 * Created by jt on 1/10/17.
 */
public interface AdvisoryRepository extends CrudRepository<Advisory, String>{
	
}
