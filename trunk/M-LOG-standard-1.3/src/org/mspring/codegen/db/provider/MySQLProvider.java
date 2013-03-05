/**
 * 
 */
package org.mspring.codegen.db.provider;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gao Youbo
 * @since 2013-3-5
 * @description
 * @TODO
 */
public class MySQLProvider extends DbProvider {

    /**
     * @param conn
     */
    public MySQLProvider(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Map<String, String> doGetTableComments() {
        // TODO Auto-generated method stub
        return new HashMap<String, String>();
    }

    @Override
    protected Map<String, String> doGetColumnComments(String tableName) {
        // TODO Auto-generated method stub
        return new HashMap<String, String>();
    }

}
