package guru.springframework.services;

import guru.springframework.commands.NewsForm;
import guru.springframework.commands.PluginForm;
import guru.springframework.domain.News;

import java.util.List;
import java.util.Map;

/**
 * Created by jt on 1/10/17.
 */
public interface NewsService {

    List<News> listAll(int pageNumber, int pageSize);
    
    List<News> listAllHome(int pageNumber, int pageSize);
    
    List<News> listPopular();

    News getById(String id);

    News saveOrUpdate(News product);

    void delete(String id);

    News saveOrUpdateNewsForm(NewsForm newsForm, boolean isAdd);
    
    Map<String, News> findBeforeAfterNews(String id);
    
    long getNumberPage(int numberNews);
    
    PluginForm saveOrUpdatePlugin(String id);
}
