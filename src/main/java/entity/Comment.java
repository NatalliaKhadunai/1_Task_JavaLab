package entity;

import java.sql.Timestamp;

public class Comment {
    private Article article;
    private User user;
    private Timestamp date;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (!getArticle().equals(comment.getArticle())) return false;
        if (!getUser().equals(comment.getUser())) return false;
        return getDate().equals(comment.getDate());

    }

    @Override
    public int hashCode() {
        int result = getArticle().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }
}
