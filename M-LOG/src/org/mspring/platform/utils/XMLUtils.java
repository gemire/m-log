/**
 * Feb 20, 201112:13:31 PM
 * gblog-core
 * org.gaoyoubo.core.util
 * XMLUtils.java
 * @author gaoyb
 */
package org.mspring.platform.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author gaoyb
 * 
 *         TODO XML工具类
 * 
 */
public class XMLUtils {
    /**
     * 得到节点中的文本
     * 
     * @param parent
     *            父节点
     * @param childTagName
     *            子节点名称
     * @return
     */
    public static String getContainedText(Node parent, String childTagName) {
        Node tag;
        try {
            tag = ((Element) parent).getElementsByTagName(childTagName).item(0);
            String text = getElementText((Element) tag);
            return text;
        }
        catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * 获取节点中的文本
     * 
     * @param element
     * @return
     */
    public static String getElementText(Element element) {
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node c = nodeList.item(i);
            if (c instanceof Text) return ((Text) c).getData().trim();
        }
        return null;
    }

    public static final Document newDocument() {
        return createDocumentBuilder().newDocument();
    }

    public static final Document newDocument(String rootElementName) {
        Document doc = newDocument();
        doc.appendChild(doc.createElement(rootElementName));
        return doc;
    }

    public static final Document parse(InputSource in) {
        try {
            return createDocumentBuilder().parse(in);
        }
        catch (SAXException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public static final Document parse(InputStream is) {
        try {
            return createDocumentBuilder().parse(is);
        }
        catch (SAXException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public static final Document parse(Reader in) {
        return parse(new InputSource(in));
    }

    public static final Document parse(File file) {
        try {
            return createDocumentBuilder().parse(file);
        }
        catch (SAXException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public static final Document parse(URL url) {
        return parse(new InputSource(url.toString()));
    }

    public static final Document parse(String fileName) {
        InputStream is = ClassLoaderUtils.getResourceAsStream(fileName, XMLUtils.class);
        try {
            Document localDocument = parse(is);
            return localDocument;
        }
        finally {
            try {
                is.close();
            }
            catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public static Element parseToElement(File file) {
        return parse(file).getDocumentElement();
    }

    public static Element parseToElement(String xmlStr) {
        return parse(xmlStr).getDocumentElement();
    }

    public static String getName(Element element) {
        return element.getTagName();
    }

    public static List<Element> getChildElements(Element element) {
        List childEles = new ArrayList();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (1 == node.getNodeType()) {
                childEles.add((Element) node);
            }
        }
        return childEles;
    }

    public static Element getChildElement(Element element, String tagName) {
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if ((1 == node.getNodeType()) && (tagName.equals(node.getNodeName()))) {
                return (Element) node;
            }
        }
        return null;
    }

    public static List<Element> getChildElements(Element element, String tagName) {
        List childEles = new ArrayList();
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if ((1 != node.getNodeType()) || (!tagName.equals(node.getNodeName()))) continue;
            childEles.add((Element) node);
        }

        return childEles;
    }

    /**
     * 通过xpath表达式解析某个xml节点。
     * 
     * @param obj
     *            要被解析的xml节点对象。
     * @param xPath
     *            xpath表达式。
     * @param qName
     *            被解析的目标类型。
     * @return 返回解析后的对象。
     * @throws XPathExpressionException
     *             如果不能计算 expression。
     * 
     * @exception RuntimeException
     *                创建默认对象模型的 XPathFactory 遇到故障时。
     * @exception NullPointerException
     *                如果xPath为空时抛出时异常。
     */
    private static Object parseByXpath(final Object obj, final String xPath, QName qName) throws NullPointerException, RuntimeException, XPathExpressionException {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath path = xpathFactory.newXPath();
        return path.evaluate(xPath, obj, qName);
    }

    /**
     * 通过XPath表达式获取单个节点。
     * 
     * @param obj
     *            要被解析的對象。
     * @param xPath
     *            XPath表达式。
     * @return 返回获取到的节点。
     * 
     * @throws XPathExpressionException
     *             如果不能计算 expression。
     * 
     * @exception RuntimeException
     *                创建默认对象模型的 XPathFactory 遇到故障时。
     * @exception NullPointerException
     *                如果xPath为空时抛出时异常。
     */
    public static Node parseForNode(final Object obj, final String xPath) throws NullPointerException, RuntimeException, XPathExpressionException {
        return (Node) XMLUtils.parseByXpath(obj, xPath, XPathConstants.NODE);
    }

    /**
     * 通过XPath表达式获取某个xml节点的字符串值。
     * 
     * @param obj
     *            要被解析的對象。
     * @param xPath
     *            XPath表达式。
     * @return 返回获取到的节点的字符串值。
     * 
     * @throws XPathExpressionException
     *             如果不能计算 expression。
     * 
     * @exception RuntimeException
     *                创建默认对象模型的 XPathFactory 遇到故障时。
     * @exception NullPointerException
     *                如果xPath为空时抛出时异常。
     */
    public static String parseForString(final Object obj, final String xPath) throws NullPointerException, RuntimeException, XPathExpressionException {
        return (String) XMLUtils.parseByXpath(obj, xPath, XPathConstants.STRING);
    }

    /**
     * 通过XPath表达式获取某个xml节点的布尔值。
     * 
     * @param obj
     *            要被解析的對象。
     * @param xPath
     *            XPath表达式。
     * @return 返回获取到的节点的布尔值。
     * 
     * @throws XPathExpressionException
     *             如果不能计算 expression。
     * 
     * @exception RuntimeException
     *                创建默认对象模型的 XPathFactory 遇到故障时。
     * @exception NullPointerException
     *                如果xPath为空时抛出时异常。
     */
    public static Boolean parseForBoolean(final Object obj, final String xPath) throws NullPointerException, RuntimeException, XPathExpressionException {
        return (Boolean) XMLUtils.parseByXpath(obj, xPath, XPathConstants.BOOLEAN);
    }

    /**
     * 通过XPath表达式获取Node列表。
     * 
     * @param obj
     *            要被解析的對象。
     * @param xPath
     *            XPath表达式。
     * @return 返回获取到的Node列表。
     * 
     * @throws XPathExpressionException
     *             如果不能计算 expression。
     * 
     * @exception RuntimeException
     *                创建默认对象模型的 XPathFactory 遇到故障时。
     * @exception NullPointerException
     *                如果xPath为空时抛出时异常。
     */
    public static List<Node> parseForNodeList(final Object obj, final String xPath) throws NullPointerException, XPathExpressionException, RuntimeException  {
        List<Node> lists = new ArrayList<Node>();
        NodeList nList = (NodeList) XMLUtils.parseByXpath(obj, xPath, XPathConstants.NODESET);
        if (nList != null) {
            for (int i = 0; i < nList.getLength(); i++) {
                lists.add(nList.item(i));
            }
        }
        return lists;
    }

    public static String getAttribute(Element element, String attrName) {
        return element.getAttribute(attrName);
    }

    public static Map<String, String> getAttributes(Element element) {
        Map attributes = new HashMap();
        NamedNodeMap attrMap = element.getAttributes();
        for (int i = 0; i < attrMap.getLength(); i++) {
            Node attr = attrMap.item(i);
            if (attr.getNodeType() == 2) {
                String name = ((Attr) attr).getName();
                String value = ((Attr) attr).getValue();
                attributes.put(name, value);
            }
        }
        return attributes;
    }

    public static String getValue(Element element) {
        return element.getTextContent();
    }

    /**
     * 使用新节点替换原来的旧节点。
     * 
     * @param oldNode
     *            要被替换的旧节点。
     * @param newNode
     * 
     *            替换后的新节点。
     * @exception DOMException
     *                如果此节点为不允许
     *                newNode节点类型的子节点的类型；或者如果要放入的节点为此节点的一个祖先或此节点本身；或者如果此节点为
     *                Document 类型且替换操作的结果将第二个 DocumentType 或 Element 添加到
     *                Document 上。 WRONG_DOCUMENT_ERR: 如果 newChild
     *                是从不同的文档创建的，不是从创建此节点的文档创建的，则引发此异常。
     *                NO_MODIFICATION_ALLOWED_ERR: 如果此节点或新节点的父节点为只读的，则引发此异常。
     *                NOT_FOUND_ERR: 如果 oldChild 不是此节点的子节点，则引发此异常。
     *                NOT_SUPPORTED_ERR: 如果此节点为 Document 类型，则如果 DOM 实现不支持替换
     *                DocumentType 子节点或 Element 子节点，则可能引发此异常。
     */
    public static void replaceNode(Node oldNode, Node newNode) {
        if ((oldNode != null) && (newNode != null)) {
            oldNode.getParentNode().replaceChild(newNode, oldNode);
        }
    }

    /**
     * 将Document输出到指定的文件中。
     * 
     * @param fileName
     *            文件名。
     * @param node
     *            要保存的对象。
     * @param encoding
     *            保存的编码。
     * @throws FileNotFoundException
     *             指定的文件名不存在时，抛出此异常。
     * @throws TransformerException
     *             如果转换过程中发生不可恢复的错误时，抛出此异常。
     */
    public static void saveXml(final String fileName, final Node node, String encoding) throws FileNotFoundException, TransformerException {
        XMLUtils.writeXml(new FileOutputStream(fileName), node, encoding);
    }

    /**
     * 将Document输出成字符串的形式。
     * 
     * @param node
     *            Node对象。
     * @param encoding
     *            字符串的编码。
     * @return 返回输出成的字符串。
     * @throws TransformerException
     *             如果转换过程中发生不可恢复的错误时，抛出此异常。
     * @throws UnsupportedEncodingException
     *             指定的字符串编码不支持时，抛出此异常。
     */
    public static String nodeToString(Node node, String encoding) throws TransformerException, UnsupportedEncodingException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XMLUtils.writeXml(outputStream, node, encoding);
        return outputStream.toString(encoding);
    }

    public static Boolean hasAttribute(Element element, String attrName) {
        return Boolean.valueOf(element.hasAttribute(attrName));
    }

    /**
     * 判断某attribute的值是否为attrValue
     * 
     * @param element
     * @param attrName
     * @param attrValue
     * @return
     */
    public static Boolean hasAttributeValue(Element element, String attrName, String attrValue) {
        if ((element.hasAttribute(attrName)) && (attrValue.equals(getAttribute(element, attrName)))) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    /**
     * 将指定的Node写到指定的OutputStream流中。
     * 
     * @param encoding
     *            编码。
     * @param os
     *            OutputStream流。
     * @param node
     *            Node节点。
     * @throws TransformerException
     *             如果转换过程中发生不可恢复的错误时，抛出此异常。
     */
    private static void writeXml(OutputStream os, Node node, String encoding) throws TransformerException {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
        DOMSource source = new DOMSource();
        source.setNode(node);
        StreamResult result = new StreamResult();
        result.setOutputStream(os);
        transformer.transform(source, result);
    }

    private static final DocumentBuilder createDocumentBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        return builder;
    }
}
