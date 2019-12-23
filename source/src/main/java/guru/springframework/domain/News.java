package guru.springframework.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

/**
 * Created by jt on 1/10/17.
 */
@Document
public class News {
    @Id
    private ObjectId _id;
    private String title;
    private String summary;
    private String detail;
    private String urlImage;
    private String urlLike;
    private String urlComment;
    private boolean displayFlg;
    private boolean popularNews;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date createDate;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date updateDate;    

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isDisplayFlg() {
		return displayFlg;
	}

	public void setDisplayFlg(boolean displayFlg) {
		this.displayFlg = displayFlg;
	}

	public boolean isPopularNews() {
		return popularNews;
	}

	public void setPopularNews(boolean popularNews) {
		this.popularNews = popularNews;
	}

	public String getUrlLike() {
		return urlLike;
	}

	public void setUrlLike(String urlLike) {
		this.urlLike = urlLike;
	}

	public String getUrlComment() {
		return urlComment;
	}

	public void setUrlComment(String urlComment) {
		this.urlComment = urlComment;
	}


    
}
