package guru.springframework.converters;

import guru.springframework.commands.NewsForm;
import guru.springframework.domain.News;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class NewsToNewsForm implements Converter<News, NewsForm> {
    @Override
    public NewsForm convert(News news) {
    	NewsForm newsForm = new NewsForm();
    	newsForm.setId(news.getId().toHexString());
    	newsForm.setDetail(news.getDetail());
    	newsForm.setSummary(news.getSummary());
    	newsForm.setTitle(news.getTitle());
    	newsForm.setUrlImage(news.getUrlImage());
        return newsForm;
    }
}
