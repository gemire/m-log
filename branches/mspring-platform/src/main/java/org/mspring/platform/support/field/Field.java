package org.mspring.platform.support.field;

/**
 * A field is an attribute of a persistence POJO
 * 
 * @author gaoyb(www.mspring.org)
 * @since Mar 6, 2011
 * org.mspring.core.model.field
 */
public interface Field {
	/**
	 * The model field key e.g user.email of User(have email attribute)
	 * This id must be consistent with the HQL element when used with PaginationSupport
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * descrition, usually in chinese 
	 * @return
	 */
	public String getName();
	
	/**
	 * 是否可排序
	 * @return
	 */
	public boolean isSortable();
}
