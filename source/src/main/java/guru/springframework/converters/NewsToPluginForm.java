package guru.springframework.converters;

import guru.springframework.commands.PluginForm;
import guru.springframework.domain.News;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 1/10/17.
 */
@Component
public class NewsToPluginForm implements Converter<News, PluginForm> {
    @Override
    public PluginForm convert(News news) {
    	PluginForm pluginForm = new PluginForm();
    	pluginForm.setId(news.getId().toHexString());
    	pluginForm.setUrlLike(news.getUrlLike());
    	pluginForm.setUrlComment(news.getUrlComment());
    	pluginForm.setTitle(news.getTitle());
        return pluginForm;
    }
}
