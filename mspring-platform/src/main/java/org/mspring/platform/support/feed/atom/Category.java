/**
 * 
 */
package org.mspring.platform.support.feed.atom;

/**
 * @author Gao Youbo
 * @since May 12, 2012
 */
public final class Category {
    /**
     * Term variable.
     */
    private static final String TERM_VARIABLE = "${term}";
    /**
     * Category element.
     */
    private static final String CATEGORY_ELEMENT = "<category term=\"" + TERM_VARIABLE + "\" />";
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
        return CATEGORY_ELEMENT.replace(TERM_VARIABLE, term);
    }
}
