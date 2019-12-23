package guru.springframework.converters;

import guru.springframework.commands.NewsForm;
import guru.springframework.domain.News;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class PluginFormToPlugin implements Converter<NewsForm, News> {

    @Override
    public News convert(NewsForm newsForm) {
    	News news = new News();
        if (newsForm.getId() != null  && !StringUtils.isEmpty(newsForm.getId())) {
        	news.setId(new ObjectId(newsForm.getId()));
        }
        news.setTitle(newsForm.getTitle());
        news.setSummary(newsForm.getSummary());
        news.setUrlImage(newsForm.getUrlImage());
        news.setDetail(newsForm.getDetail());
        news.setDisplayFlg(true);
        return news;
    }
}
