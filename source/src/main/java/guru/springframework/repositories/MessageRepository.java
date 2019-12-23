package guru.springframework.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Advisory;
import guru.springframework.domain.Message;

/**
 * Created by jt on 1/10/17.
 */
public interface MessageRepository extends CrudRepository<Message, String>{
	
}
