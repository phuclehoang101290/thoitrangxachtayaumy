package guru.springframework.services;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductFormToProduct;
import guru.springframework.domain.Product;
import guru.springframework.domain.RegisterEmail;
import guru.springframework.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class HomeServiceImpl implements HomeService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public HomeServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

	@Override
	public List<RegisterEmail> listAll() {
		// TODO Auto-generated method stub
		return mongoTemplate.findAll(RegisterEmail.class);
	}

	@Override
	public RegisterEmail getById(String id) {
		// TODO Auto-generated method stub
		return mongoTemplate.findById(id, RegisterEmail.class);
	}

	@Override
	public RegisterEmail saveOrUpdate(RegisterEmail email) {
		// TODO Auto-generated method stub
		return mongoTemplate.save(email);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		mongoTemplate.remove(mongoTemplate.findById(id, RegisterEmail.class));
	}


}
