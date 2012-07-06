/**
 * 
 */
package org.mspring.platform.web.feed.rss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @author Gao Youbo
 * @since May 12, 2012
 */
public final class Channel {
    /**
     * Title.
     */
    private String title;
    /**
     * Link.
     */
    private String link;
    /**
     * Atom link.
     */
    private String atomLink;
    /**
     * Description.
     */
    private String description;
    /**
     * Generator.
     */
    private String generator;
    /**
     * Last build date.
     */
    private Date lastBuildDate;
    /**
     * Language.
     */
    private String language;
    /**
     * Items.
     */
    private List<Item> items = new ArrayList<Item>();
    /**
     * Time zone id.
     */
    public static final String TIME_ZONE_ID = "Asia/Shanghai";
    /**
     * Start.
     */
    private static final String START = "<?xml version='1.0' encoding='UTF-8'?><rss version=\"2.0\" " + "xmlns:atom=\"http://www.w3.org/2005/Atom\"><channel>";
    /**
     * End.
     */
    private static final String END = "</channel></rss>";
    /**
     * Start title element.
     */
    private static final String START_TITLE_ELEMENT = "<title>";
    /**
     * End title element.
     */
    private static final String END_TITLE_ELEMENT = "</title>";
    /**
     * Start link element.
     */
    private static final String START_LINK_ELEMENT = "<link>";
    /**
     * Atom link variable.
     */
    private static final String ATOM_LINK_VARIABLE = "${atomLink}";
    /**
     * End link element.
     */
    private static final String END_LINK_ELEMENT = "</link>";
    /**
     * Atom link element.
     */
    private static final String ATOM_LINK_ELEMENT = "<atom:link href=\"" + ATOM_LINK_VARIABLE + "\" rel=\"self\" type=\"application/rss+xml\" />";
    /**
     * Start description element.
     */
    private static final String START_DESCRIPTION_ELEMENT = "<description>";
    /**
     * End description element.
     */
    private static final String END_DESCRIPTION_ELEMENT = "</description>";
    /**
     * Start generator element.
     */
    private static final String START_GENERATOR_ELEMENT = "<generator>";
    /**
     * End generator element.
     */
    private static final String END_GENERATOR_ELEMENT = "</generator>";
    /**
     * Start language element.
     */
    private static final String START_LANGUAGE_ELEMENT = "<language>";
    /**
     * End language element.
     */
    private static final String END_LANGUAGE_ELEMENT = "</language>";
    /**
     * Start last build date element.
     */
    private static final String START_LAST_BUILD_DATE_ELEMENT = "<lastBuildDate>";
    /**
     * End last build date element.
     */
    private static final String END_LAST_BUILD_DATE_ELEMENT = "</lastBuildDate>";

    /**
     * Sets the atom link with the specified atom link.
     * 
     * @param atomLink
     *            the specified atom link
     */
    public void setAtomLink(final String atomLink) {
        this.atomLink = atomLink;
    }

    /**
     * Gets the atom link.
     * 
     * @return atom link
     */
    public String getAtomLink() {
        return atomLink;
    }

    /**
     * Gets the last build date.
     * 
     * @return last build date
     */
    public Date getLastBuildDate() {
        return lastBuildDate;
    }

    /**
     * Sets the last build date with the specified last build date.
     * 
     * @param lastBuildDate
     *            the specified last build date
     */
    public void setLastBuildDate(final Date lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    /**
     * Gets generator.
     * 
     * @return generator
     */
    public String getGenerator() {
        return generator;
    }

    /**
     * Sets the generator with the specified generator.
     * 
     * @param generator
     *            the specified generator
     */
    public void setGenerator(final String generator) {
        this.generator = generator;
    }

    /**
     * Gets the link.
     * 
     * @return link
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the link with the specified link.
     * 
     * @param link
     *            the specified link
     */
    public void setLink(final String link) {
        this.link = link;
    }

    /**
     * Gets the title.
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title with the specified title.
     * 
     * @param title
     *            the specified title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Adds the specified item.
     * 
     * @param item
     *            the specified item
     */
    public void addItem(final Item item) {
        items.add(item);
    }

    /**
     * Gets the description.
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description with the specified description.
     * 
     * @param description
     *            the specified description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets the language.
     * 
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language with the specified language.
     * 
     * @param language
     *            the specified language
     */
    public void setLanguage(final String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(START);

        stringBuilder.append(START_TITLE_ELEMENT);
        stringBuilder.append(title);
        stringBuilder.append(END_TITLE_ELEMENT);

        stringBuilder.append(START_LINK_ELEMENT);
        stringBuilder.append(link);
        stringBuilder.append(END_LINK_ELEMENT);

        stringBuilder.append(ATOM_LINK_ELEMENT.replace(ATOM_LINK_VARIABLE, atomLink));

        stringBuilder.append(START_DESCRIPTION_ELEMENT);
        stringBuilder.append(description);
        stringBuilder.append(END_DESCRIPTION_ELEMENT);

        stringBuilder.append(START_GENERATOR_ELEMENT);
        stringBuilder.append(generator);
        stringBuilder.append(END_GENERATOR_ELEMENT);

        stringBuilder.append(START_LAST_BUILD_DATE_ELEMENT);
        stringBuilder.append(DateFormatUtils.SMTP_DATETIME_FORMAT.format(lastBuildDate));
        stringBuilder.append(END_LAST_BUILD_DATE_ELEMENT);

        stringBuilder.append(START_LANGUAGE_ELEMENT);
        stringBuilder.append(language);
        stringBuilder.append(END_LANGUAGE_ELEMENT);

        for (final Item item : items) {
            stringBuilder.append(item.toString());
        }

        stringBuilder.append(END);

        return stringBuilder.toString();
    }
}
