/**
 * 
 */
package org.mspring.mlog.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mspring.mlog.entity.Catalog;

/**
 * @author Gao Youbo
 * @since 2013-2-8
 * @description
 * @TODO
 */
public class CatalogUtils {
    
    /**
     * 获取根节点
     * @param catalogs
     * @return
     */
    public static List<Catalog> getRootList(List<Catalog> catalogs) {
        List<Catalog> rootCatalogs = new ArrayList<Catalog>();
        for (Catalog catalog : catalogs) {
            if (catalog.getParent() == null || catalog.getParent().getId() == null || Long.valueOf("0").equals(catalog.getParent().getId())) {
                rootCatalogs.add(catalog);
            }
        }
        return rootCatalogs;
    }

    /**
     * 根据根节点获取
     * @param parent
     * @param catalogs
     * @return
     */
    public static List<Catalog> getChildList(Catalog parent, List<Catalog> catalogs) {
        List<Catalog> childs = new ArrayList<Catalog>();
        for (Catalog catalog : catalogs) {
            if (catalog.getParent() != null && parent.getId().equals(catalog.getParent().getId())) {
                childs.add(catalog);
            }
        }
        return childs;
    }

    /**
     * 获取树形
     * @param catalogs
     * @return
     */
    public static List<Catalog> getTreeList(List<Catalog> catalogs) {
        List<Catalog> tree = new ArrayList<Catalog>();
        List<Catalog> roots = getRootList(catalogs);
        Collections.sort(roots);
        for (Catalog catalog : roots) {
            catalog.setDeep(1);
            getTreeByParent(catalog, catalogs, tree);
        }
        return tree;
    }

    /**
     * 获取一个节点下的树形
     * @param parent
     * @param catalogs
     * @param tree
     * @return
     */
    public static List<Catalog> getTreeByParent(Catalog parent, List<Catalog> catalogs, List<Catalog> tree) {
        List<Catalog> one = new ArrayList<Catalog>();
        // if (!one.contains(parent)) {
        tree.add(parent);
        // }
        List<Catalog> childs = getChildList(parent, catalogs);
        Collections.sort(childs);
        if (childs != null && childs.size() > 0) {
            parent.setHasChild(true);
            for (Catalog catalog : childs) {
                catalog.setDeep(parent.getDeep() + 1);
                List<Catalog> c = getTreeByParent(catalog, catalogs, tree);
                tree.addAll(c);
            }
        }
        return one;
    }

    public static void main(String[] args) {
        List<Catalog> catalogs = new ArrayList<Catalog>();
        // catalogs.add(new Catalog(new Long(1), "Java", null));
        // catalogs.add(new Catalog(new Long(2), ".NET", null));
        // catalogs.add(new Catalog(new Long(3), "JavaScript", null));
        // catalogs.add(new Catalog(new Long(4), "Android", null));
        //
        // catalogs.add(new Catalog(new Long(5), "Spring", new Long(1)));
        // catalogs.add(new Catalog(new Long(6), "Struts", new Long(1)));
        // catalogs.add(new Catalog(new Long(7), "Hibernate", new Long(1)));
        // catalogs.add(new Catalog(new Long(8), "webwork", new Long(1)));
        // catalogs.add(new Catalog(new Long(9), "Lucene", new Long(1)));
        // catalogs.add(new Catalog(new Long(10), "JBPM", new Long(1)));
        //
        // catalogs.add(new Catalog(new Long(11), "ASP.NET", new Long(2)));
        // catalogs.add(new Catalog(new Long(12), "WinForm", new Long(2)));
        // catalogs.add(new Catalog(new Long(13), "WinPhone7", new Long(2)));
        //
        // catalogs.add(new Catalog(new Long(14), "JQuery", new Long(3)));
        // catalogs.add(new Catalog(new Long(15), "Dojo", new Long(3)));
        // catalogs.add(new Catalog(new Long(16), "JQueryUI", new Long(3)));
        // catalogs.add(new Catalog(new Long(17), "ExtJS", new Long(3)));
        //
        // catalogs.add(new Catalog(new Long(18), "Android1.6", new Long(4)));
        // catalogs.add(new Catalog(new Long(19), "Android2.2", new Long(4)));
        // catalogs.add(new Catalog(new Long(20), "Android2.3", new Long(4)));
        // catalogs.add(new Catalog(new Long(21), "Android4.1", new Long(4)));
        catalogs.add(new Catalog(new Long(89), "Java", null));
        catalogs.add(new Catalog(new Long(2), ".NET", null));
        catalogs.add(new Catalog(new Long(78), "JavaScript", null));
        catalogs.add(new Catalog(new Long(73), "Android", null));

        catalogs.add(new Catalog(new Long(5), "Spring", new Long(89)));
        catalogs.add(new Catalog(new Long(6), "Struts", new Long(89)));
        catalogs.add(new Catalog(new Long(7), "Hibernate", new Long(89)));
        catalogs.add(new Catalog(new Long(8), "webwork", new Long(89)));
        catalogs.add(new Catalog(new Long(9), "Lucene", new Long(89)));
        catalogs.add(new Catalog(new Long(10), "JBPM", new Long(89)));

        catalogs.add(new Catalog(new Long(11), "ASP.NET", new Long(2)));
        catalogs.add(new Catalog(new Long(12), "WinForm", new Long(2)));
        catalogs.add(new Catalog(new Long(13), "WinPhone7", new Long(2)));

        catalogs.add(new Catalog(new Long(14), "JQuery", new Long(78)));
        catalogs.add(new Catalog(new Long(15), "Dojo", new Long(78)));
        catalogs.add(new Catalog(new Long(16), "JQueryUI", new Long(78)));
        catalogs.add(new Catalog(new Long(17), "ExtJS", new Long(78)));

        catalogs.add(new Catalog(new Long(18), "Android1.6", new Long(73)));
        catalogs.add(new Catalog(new Long(19), "Android2.2", new Long(73)));
        catalogs.add(new Catalog(new Long(20), "Android2.78", new Long(73)));
        catalogs.add(new Catalog(new Long(21), "Android4.89", new Long(73)));

        catalogs.add(new Catalog(new Long(22), "Spring MVC", new Long(5)));
        catalogs.add(new Catalog(new Long(23), "Spring Template", new Long(5)));
        catalogs.add(new Catalog(new Long(24), "Spring Aop", new Long(5)));

        List<Catalog> tree = getTreeList(catalogs);
        for (Catalog catalog : tree) {
            for (int i = 0; i < catalog.getDeep(); i++) {
                System.out.print("*");
            }
            System.out.println(catalog.getName());
        }

    }
}
