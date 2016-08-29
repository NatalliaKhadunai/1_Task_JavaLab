package entity;

import java.util.Comparator;

/**
 * Created by Natallia_Khadunai on 8/26/2016.
 */
public class ArticleCommentComparator implements Comparator<Article> {
    public int compare(Article o1, Article o2) {
        if (o1.getCommentList().size() > o2.getCommentList().size()) return -1;
        if (o1.getCommentList().size() < o2.getCommentList().size()) return 1;
        else return 0;
    }
}
