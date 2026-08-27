package org.skypro.skyshop.description;

import org.skypro.skyshop.search.Searchable;

public class Article implements Searchable {

    private final String title;
    private final String description;

    public Article(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getTypeContent() {
        return "ARTICLE";
    }

    @Override
    public String toString() {
        return title + "\n" + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Article)) {
            return false;
        }
        Article article = (Article) o;
        return title.equals(article.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
