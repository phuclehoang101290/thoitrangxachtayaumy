package guru.springframework.services;

import guru.springframework.commands.NewsForm;
import guru.springframework.commands.PluginForm;
import guru.springframework.converters.NewsFormToNews;
import guru.springframework.converters.NewsToPluginForm;
import guru.springframework.domain.News;
import guru.springframework.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class NewsServiceImpl implements NewsService {

	private NewsRepository newRepository;
	private NewsFormToNews newsFormToNews;
	private final MongoTemplate mongoTemplate;
	private NewsToPluginForm newsToPluginForm;
	
	@Autowired
	Environment env;

	@Autowired
	public NewsServiceImpl(NewsRepository newRepository, NewsFormToNews newsFormToNews, NewsToPluginForm newsToPluginForm, MongoTemplate mongoTemplate) {
		this.newRepository = newRepository;
		this.newsFormToNews = newsFormToNews;
		this.mongoTemplate = mongoTemplate;
		this.newsToPluginForm = newsToPluginForm;
	}

	@Override
	public List<News> listAll(int pageNumber, int pageSize) {
		List<News> news = new ArrayList<News>();
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "createDate"));
		query.skip(pageNumber * pageSize);
		   query.limit(pageSize);
		news = mongoTemplate.find(query, News.class);

		//newRepository.findAll().forEach(news::add); // fun with Java 8
		return news;
	}
	
	@Override
	public List<News> listAllHome(int pageNumber, int pageSize) {
		List<News> news = new ArrayList<News>();
		Query query = new Query();
		query.addCriteria(Criteria.where("displayFlg").is(true));
		query.with(new Sort(Sort.Direction.DESC, "createDate"));
		query.skip(pageNumber * pageSize);
		   query.limit(pageSize);
		news = mongoTemplate.find(query, News.class);

		//newRepository.findAll().forEach(news::add); // fun with Java 8
		return news;
	}

	@Override
	public News getById(String id) {
		return newRepository.findById(id).orElse(null);
	}

	@Override
	public News saveOrUpdate(News news) {
		newRepository.save(news);
		return news;
	}

	@Override
	public void delete(String id) {
		newRepository.deleteById(id);
	}

	@Override
	public News saveOrUpdateNewsForm(NewsForm newsForm, boolean isAdd) {
		News news = newsFormToNews.convert(newsForm);
		if (isAdd) {
			news.setCreateDate(new Date());
			news.setUpdateDate(new Date());
		} else {
			news.setUpdateDate(new Date());
		}	
		News savedNews = saveOrUpdate(news);
		return savedNews;
	}

	@Override
	public Map<String, News> findBeforeAfterNews(String id) {
		Map<String, News> result = new HashMap<String, News>();
		News news = getById(id);
		Query query = new Query();
		query.addCriteria(Criteria.where("createDate").gt(news.getCreateDate()));
		query.limit(1);
		List<News> lstNewsAfter = mongoTemplate.find(query, News.class);
		Query query2 = new Query();
		query2.addCriteria(Criteria.where("createDate").lt(news.getCreateDate()));
		query2.limit(1);
		List<News> lstNewsBefore = mongoTemplate.find(query2, News.class);
		if(lstNewsAfter.size() > 0) {
			result.put("after", lstNewsAfter.get(0));
		}
		if(lstNewsBefore.size() > 0) {
			result.put("before", lstNewsBefore.get(0));
		}
		return result;
	}

	@Override
	public long getNumberPage(int numberNews) {
		Query query = new Query();
		query.addCriteria(Criteria.where("displayFlg").is(true));
		long count = mongoTemplate.count(query, News.class);
		float du = count % numberNews;
		if(du > 0) {
			return count / numberNews + 1;
		}else {
			return count / numberNews;
		}
	}

	@Override
	public PluginForm saveOrUpdatePlugin(String id) {
		News news = getById(id);
		String domain = env.getProperty("domain");
		news.setUrlComment(domain + "/news-detail/" + id);
		news.setUrlLike(domain + "/news-detail/" + id);
		mongoTemplate.save(news);
		return newsToPluginForm.convert(news);
	}

	@Override
	public List<News> listPopular() {
		Query query = new Query();
		query.addCriteria(Criteria.where("popularNews").is(true));
		query.with(new Sort(Sort.Direction.DESC, "createDate"));
		List<News> lstResult = mongoTemplate.find(query, News.class);
		return lstResult;
	}
	
	
}
