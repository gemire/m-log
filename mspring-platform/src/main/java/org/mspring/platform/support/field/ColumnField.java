package org.mspring.platform.support.field;

import org.apache.commons.lang3.StringUtils;

/**
 * @author gaoyb(www.mspring.org)
 * @since Mar 6, 2011
 * org.mspring.core.model.field
 */
public class ColumnField implements Field {
	
	private String id;
	private String name;
	private boolean sortable = true;
	
	/**
	 * 非可排序字段
	 */
	public static final Field NULLFIELD = new ColumnField("", "", false);
	
	/**
	 * Default sortable
	 */
	public ColumnField() {
		this("", "", true);
	}
	
	/**
	 * Default sortable
	 * @param id must be consistent with hql's query string
	 * @param name usually in chinese
	 */
	public ColumnField(String id, String name) {
		this(id, name, true);
	}
	
	public ColumnField(String id, String name, boolean sortable) {
		this.id = StringUtils.isBlank(id) ? "" : id;
		this.name = StringUtils.isEmpty(name) ? "" : name;
		this.sortable = sortable;
	}
	

	/* (non-Javadoc)
	 * @see org.mspring.core.model.field.Field#getId()
	 */
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/* (non-Javadoc)
	 * @see org.mspring.core.model.field.Field#getName()
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	/* (non-Javadoc)
	 * @see org.mspring.core.model.field.Field#isSortable()
	 */
	public boolean isSortable() {
		// TODO Auto-generated method stub
		return sortable;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("ColumnField[id=").append(id)
			.append(", name=").append(name)
			.append(", sortable").append(sortable)
			.append("]").toString();
	}

}
