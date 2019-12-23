package guru.springframework.commands;



/**
 * Created by jt on 1/10/17.
 */
public class ListNewsForm {
    private String newsId;
    private String newsDisplayFlg;
    private String newsPopular;
    public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getNewsDisplayFlg() {
		return newsDisplayFlg;
	}
	public void setNewsDisplayFlg(String newsDisplayFlg) {
		this.newsDisplayFlg = newsDisplayFlg;
	}
	public String getNewsPopular() {
		return newsPopular;
	}
	public void setNewsPopular(String newsPopular) {
		this.newsPopular = newsPopular;
	}
	
	
}
