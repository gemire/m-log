/**
 * 
 */
package org.mspring.mlog.entity;

/**
 * @author Gao Youbo
 * @since 2012-11-22
 * @Description
 * @TODO
 */
public class Size {
    private double width;
    private double height;

    /**
     * 
     */
    public Size() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param width
     * @param height
     */
    public Size(double width, double height) {
        super();
        this.width = width;
        this.height = height;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width
     *            the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height
     *            the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

}
