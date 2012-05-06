package org.mspring.mlog.web.action.manage.article;

import java.util.ArrayList;
import java.util.List;

import org.mspring.mlog.entity.Article;
import org.mspring.mlog.entity.Category;
import org.mspring.mlog.entity.Tag;
import org.mspring.mlog.util.ParameterUtils;
import org.mspring.mlog.web.action.manage.AbstractManageAction;

public class EditArticleAction extends AbstractManageAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1107647070005411961L;

    private Long id;
    private Article article;
    private String categoryIds;
    private String categoryNames;
    private String tagIds;
    private String tagNames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(String categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }

    public String toEditArticle() {
        article = articleService.findArticleById(id);

        List<Long> categoryIdList = new ArrayList<Long>();
        List<String> categoryNameList = new ArrayList<String>();
        List<Long> tagIdList = new ArrayList<Long>();
        List<String> tagNameList = new ArrayList<String>();

        for (Category cat : article.getCategories()) {
            categoryIdList.add(cat.getId());
            categoryNameList.add(cat.getName());
        }

        for (Tag tag : article.getTags()) {
            tagIdList.add(tag.getId());
            tagNameList.add(tag.getName());
        }

        categoryIds = ParameterUtils.arrayToString(categoryIdList.toArray());
        categoryNames = ParameterUtils.arrayToString(categoryNameList.toArray());
        tagIds = ParameterUtils.arrayToString(tagIdList.toArray());
        tagNames = ParameterUtils.arrayToString(tagNameList.toArray());
        return SUCCESS;
    }

    public String doEditArticle() {
        articleService.updateArticle(article, categoryIds, tagIds);
        return SUCCESS;
    }
}
