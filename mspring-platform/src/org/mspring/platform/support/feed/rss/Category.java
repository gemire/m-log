/**
 * 
 */
package org.mspring.platform.support.feed.rss;

/**
 * @author Gao Youbo
 * @since May 12, 2012
 */
public final class Category {
    /**
     * Category element.
     */
    private static final String CATEGORY_ELEMENT = "<category>${term}</category>";
    /**
     * Term.
     */
    private String term;

    /**
     * Gets the term.
     * 
     * @return term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Sets the term with the specified term.
     * 
     * @param term
     *            the specified term
     */
    public void setTerm(final String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return CATEGORY_ELEMENT.replace("${term}", term);
    }
}
