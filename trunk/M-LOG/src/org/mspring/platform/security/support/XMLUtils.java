package org.mspring.platform.security.support;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XMLUtils {
    public static String getContainedText(Node parent, String childTagName) {
        try {
            Node tag = ((Element) parent).getElementsByTagName(childTagName).item(0);
            String text = getElementText((Element) tag);
            return text;
        } catch (Exception e) {
        }
        return null;
    }

    public static String getElementText(Element element) {
        NodeList nl = element.getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            Node c = nl.item(i);
            if ((c instanceof Text)) {
                return ((Text) c).getData().trim();
            }
        }

        return null;
    }
}