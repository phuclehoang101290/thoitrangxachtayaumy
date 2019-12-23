package guru.springframework.services;

import guru.springframework.domain.Message;
import guru.springframework.repositories.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class MessageServiceImpl implements MessageService {

	private final MongoTemplate mongoTemplate;
	private MessageRepository messageRepository;

	@Autowired
	public MessageServiceImpl(MongoTemplate mongoTemplate, MessageRepository messageRepository) {
		this.mongoTemplate = mongoTemplate;
		this.messageRepository = messageRepository;
	}

	@Override
	public List<Message> listAll() {
		List<Message> messages = new ArrayList<Message>();
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "createDate"));
		messages = mongoTemplate.find(query, Message.class);

		// newRepository.findAll().forEach(news::add); // fun with Java 8
		return messages;
	}

	@Override
	public Message getById(String id) {
		return mongoTemplate.findById(id, Message.class);
		// return newRepository.findById(id).orElse(null);
	}

	@Override
	public Message saveOrUpdate(Message message) {
		messageRepository.save(message);
		return message;
	}

}
