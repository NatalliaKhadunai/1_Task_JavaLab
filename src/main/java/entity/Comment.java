package entity;

import java.sql.Timestamp;

public class Comment {
    private int articleID;
    private String accountLogin;
    private Timestamp date;
    private String content;

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getAccountLogin() {
        return accountLogin;
    }

    public void setAccountLogin(String accountLogin) {
        this.accountLogin = accountLogin;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (getArticleID() != comment.getArticleID()) return false;
        if (!getAccountLogin().equals(comment.getAccountLogin())) return false;
        if (!getDate().equals(comment.getDate())) return false;
        return getContent().equals(comment.getContent());

    }

    @Override
    public int hashCode() {
        int result = getArticleID();
        result = 31 * result + getAccountLogin().hashCode();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getContent().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "articleID=" + articleID +
                ", accountLogin='" + accountLogin + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }
}
