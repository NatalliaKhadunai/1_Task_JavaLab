package entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class Article {
    private int id;
    private String mainTitle;
    private String shortTitle;
    private String content;
    private Timestamp publishDate;
    private String mainPhoto;
    private Set<Author> authorSet;
    private Set<Tag> tagSet;
    private List<Comment> commentList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        if (mainTitle.length() <= 230) this.mainTitle = mainTitle;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        if (shortTitle.length() <= 50) this.shortTitle = shortTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content.length() <= 5000) this.content = content;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public Set<Author> getAuthorSet() {
        return authorSet;
    }

    public void setAuthorSet(Set<Author> authorSet) {
        this.authorSet = authorSet;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;

        if (!getMainTitle().equals(article.getMainTitle())) return false;
        if (!getShortTitle().equals(article.getShortTitle())) return false;
        if (!getContent().equals(article.getContent())) return false;
        if (!getPublishDate().equals(article.getPublishDate())) return false;
        if (getMainPhoto() != null ? !getMainPhoto().equals(article.getMainPhoto()) : article.getMainPhoto() != null)
            return false;
        if (!getAuthorSet().equals(article.getAuthorSet())) return false;
        if (getTagSet() != null ? !getTagSet().equals(article.getTagSet()) : article.getTagSet() != null) return false;
        return !(getCommentList() != null ? !getCommentList().equals(article.getCommentList()) : article.getCommentList() != null);

    }

    @Override
    public int hashCode() {
        int result = getMainTitle().hashCode();
        result = 31 * result + getShortTitle().hashCode();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + getPublishDate().hashCode();
        result = 31 * result + (getMainPhoto() != null ? getMainPhoto().hashCode() : 0);
        result = 31 * result + getAuthorSet().hashCode();
        result = 31 * result + (getTagSet() != null ? getTagSet().hashCode() : 0);
        result = 31 * result + (getCommentList() != null ? getCommentList().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", mainTitle='" + mainTitle + '\'' +
                ", shortTitle='" + shortTitle + '\'' +
                ", publishDate=" + publishDate +
                ", mainPhoto='" + mainPhoto + '\'' +
                ", authorSet=" + authorSet +
                ", tagSet=" + tagSet +
                ", commentList=" + commentList +
                '}';
    }
}
