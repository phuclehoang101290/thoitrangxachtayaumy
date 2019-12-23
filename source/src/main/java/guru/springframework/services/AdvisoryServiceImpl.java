package guru.springframework.services;

import guru.springframework.domain.Advisory;
import guru.springframework.repositories.AdvisoryRepository;
import guru.springframework.repositories.NewsRepository;

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
public class AdvisoryServiceImpl implements AdvisoryService {

	private final MongoTemplate mongoTemplate;
	private AdvisoryRepository advisoryRepository;

	@Autowired
	public AdvisoryServiceImpl(MongoTemplate mongoTemplate, AdvisoryRepository advisoryRepository) {
		this.mongoTemplate = mongoTemplate;
		this.advisoryRepository = advisoryRepository;
	}

	@Override
	public List<Advisory> listAll() {
		List<Advisory> advisorys = new ArrayList<Advisory>();
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "createDate"));
		advisorys = mongoTemplate.find(query, Advisory.class);

		// newRepository.findAll().forEach(news::add); // fun with Java 8
		return advisorys;
	}

	@Override
	public Advisory getById(String id) {
		return mongoTemplate.findById(id, Advisory.class);
		// return newRepository.findById(id).orElse(null);
	}

	@Override
	public Advisory saveOrUpdate(Advisory advisory) {
		advisoryRepository.save(advisory);
		return advisory;
	}

}
