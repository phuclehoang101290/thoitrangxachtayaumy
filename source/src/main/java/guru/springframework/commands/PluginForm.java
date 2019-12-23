package guru.springframework.commands;

/**
 * Created by jt on 1/10/17.
 */
public class PluginForm {
    private String id;
    private String title;
    private String urlComment;
    private String urlLike;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrlComment() {
		return urlComment;
	}

	public void setUrlComment(String urlComment) {
		this.urlComment = urlComment;
	}

	public String getUrlLike() {
		return urlLike;
	}

	public void setUrlLike(String urlLike) {
		this.urlLike = urlLike;
	}


}
