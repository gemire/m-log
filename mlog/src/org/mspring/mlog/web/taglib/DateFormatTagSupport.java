/**
 * @since 2011-7-9
 * www.mspring.org
 * @author Gao Youbo
 */
package org.mspring.mlog.web.taglib;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.mspring.platform.utils.DateUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author Gao Youbo
 */
public class DateFormatTagSupport extends BaseTagSupport {
	
	/**
     * 
     */
    private static final long serialVersionUID = 178263764889331651L;
    
    private String valuesatck;
	private String partten = "yyyy-MM-dd";
	public String getValuesatck() {
		return this.valuesatck;
	}
	public void setValuesatck(String valuesatck) {
		this.valuesatck = valuesatck;
	}
	public String getPartten() {
		return this.partten;
	}
	public void setPartten(String partten) {
		this.partten = partten;
	}

	@Override
	public int doEndTag() throws JspException {
		Object dateobj = ActionContext.getContext().getValueStack().findValue(valuesatck);
		String value = "";
		if (dateobj instanceof Date) {
			value = DateUtils.format((Date) dateobj, partten);
		}
		
		JspWriter out = pageContext.getOut();
		try {
			out.print(value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
