/**
 * 
 */
package org.mspring.mlog.entity.security;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * @author Gao Youbo
 * @since Apr 18, 2012
 */
@Embeddable
public class AuthorityTreePK implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6671830303801720141L;

    private Authority authority;
    private Tree tree;

    /**
     * 
     */
    public AuthorityTreePK() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param authority
     * @param tree
     */
    public AuthorityTreePK(Authority authority, Tree tree) {
        super();
        this.authority = authority;
        this.tree = tree;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Authority.class)
    @JoinColumn(name = "authority_id")
    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Tree.class)
    @JoinColumn(name = "tree_id")
    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof AuthorityTreePK))
            return false;
        AuthorityTreePK castOther = (AuthorityTreePK) other;

        return ((this.getAuthority() == castOther.getAuthority()) || (this.getAuthority() != null && castOther.getAuthority() != null && this.getAuthority().equals(castOther.getAuthority()))) && ((this.getTree() == castOther.getTree()) || (this.getTree() != null && castOther.getTree() != null && this.getTree().equals(castOther.getTree())));
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (getAuthority() == null ? 0 : this.getAuthority().hashCode());
        result = 37 * result + (getTree() == null ? 0 : this.getTree().hashCode());
        return result;
    }

}
