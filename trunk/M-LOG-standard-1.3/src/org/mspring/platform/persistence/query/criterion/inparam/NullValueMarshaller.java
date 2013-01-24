package org.mspring.platform.persistence.query.criterion.inparam;

/**
 * @author Gao Youbo
 * @since Jan 31, 2012
 */
public class NullValueMarshaller implements Marshaller {

    @SuppressWarnings("rawtypes")
    public Object getNamedQueryParamValue(Class paramClass) {
        return null;
    }
    
    /**
     * @return null
     */
    public boolean hasValues() {
        return false;
    }

    public String stringValue() {
        return null;
    }
    
    public String toString() {
        return "NullValueMarshaller";
    }
}

