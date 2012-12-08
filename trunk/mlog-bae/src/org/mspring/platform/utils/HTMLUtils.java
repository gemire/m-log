package org.mspring.platform.utils;

/**
 * @since Jun 25, 2011 www.mspring.org
 * @author gaoyoubo
 */
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.NodeList;

public class HTMLUtils {

    private static final String ENC_UTF_8 = "UTF-8";
    private static final String ENC_8859_1 = "8859_1";

    protected static final int MAX_LENGTH = 300;
    /**
     * We use arrays of char in the lookup table because it is faster appending
     * this to a StringBuffer than appending a String
     */
    protected static final char[][] _stringChars = new char[MAX_LENGTH][];
    static {
        // Initialize the mapping table
        initMapping();
    }

    /**
     * 过滤掉html标记
     * 
     * @param content
     *            内容
     * @return 处理后的字符串。
     */
    public static String getHtmlText(String html) {
        if (html != null) {
            StringBuffer str = new StringBuffer();
            try {
                Parser parser = Parser.createParser(html, ENC_UTF_8);
                // 注册自定义的新结点解析器,这是必要的...
                // factory(parser);
                NodeList nodelist = parser.extractAllNodesThatMatch(new NodeFilter() {
                    public boolean accept(Node node) {
                        if (node instanceof TextNode)
                            return true;
                        return false;
                    }
                });
                for (int i = 0; i < nodelist.size(); i++) {
                    TextNode t = (TextNode) nodelist.elementAt(i);
                    str.append(t.toPlainTextString());
                }
                return str.toString();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Remove occurences of html, defined as any text between the characters
     * "&lt;" and "&gt;". Replace any HTML tags with a space.
     */
    public static String removeHTML(String str) {
        return removeHTML(str, true);
    }

    /**
     * Remove occurences of html, defined as any text between the characters
     * "&lt;" and "&gt;". Optionally replace HTML tags with a space.
     * 
     * @param str
     * @param addSpace
     * @return
     */
    public static String removeHTML(String str, boolean addSpace) {
        if (str == null)
            return "";
        StringBuffer ret = new StringBuffer(str.length());
        int start = 0;
        int beginTag = str.indexOf("<");
        int endTag = 0;
        if (beginTag == -1)
            return str;

        while (beginTag >= start) {
            if (beginTag > 0) {
                ret.append(str.substring(start, beginTag));

                // replace each tag with a space (looks better)
                if (addSpace)
                    ret.append(" ");
            }
            endTag = str.indexOf(">", beginTag);

            // if endTag found move "cursor" forward
            if (endTag > -1) {
                start = endTag + 1;
                beginTag = str.indexOf("<", start);
            }
            // if no endTag found, get rest of str and break
            else {
                ret.append(str.substring(beginTag));
                break;
            }
        }
        // append everything after the last endTag
        if (endTag > -1 && endTag + 1 < str.length()) {
            ret.append(str.substring(endTag + 1));
        }
        return ret.toString().trim();
    }

    private final static NodeFilter nfilter = new NodeFilter() {
        public boolean accept(Node arg0) {
            return true;
        }
    };

    /**
     * 生成预览内容
     * 
     * @param html
     * @param max_count
     * @return
     */
    public static String preview(String html, int max_count) {
        String content = getHtmlText(html);
        if (content.length() <= max_count) {
            return html;
        }
        return content.substring(0, max_count - 1);
        
//        if (html.length() <= max_count * 1.1)
//            return html;
//        Parser parser = new Parser();
//        StringBuffer prvContent = new StringBuffer();
//        try {
//            parser.setEncoding(ENC_UTF_8);
//            parser.setInputHTML(html);
//            NodeList nodes = parser.extractAllNodesThatMatch(nfilter);
//            Node node = null;
//            for (int i = 0; i < nodes.size(); i++) {
//                if (prvContent.length() >= max_count) {
//                    if (node instanceof TagNode) {
//                        TagNode tmp_node = (TagNode) node;
//                        boolean isEnd = tmp_node.isEndTag();
//                        if (!isEnd) {
//                            prvContent.setLength(prvContent.length() - tmp_node.getText().length() - 2);
//                        }
//                    }
//                    // 补齐所有未关闭的标签
//                    Node parent = node;
//                    // System.out.println("current node is .
//                    // "+parent.getText());
//                    do {
//                        parent = parent.getParent();
//                        // System.out.println("parent = "+parent);
//                        if (parent == null)
//                            break;
//                        if (!(parent instanceof TagNode))
//                            continue;
//                        // System.out.println("Parent node is no ended.
//                        // "+parent.getText());
//                        prvContent.append(((TagNode) parent).getEndTag().toHtml());
//                    } while (true);
//                    break;
//                }
//                node = nodes.elementAt(i);
//                if (node instanceof TagNode) {
//                    TagNode tag = (TagNode) node;
//                    prvContent.append('<');
//                    prvContent.append(tag.getText());
//                    prvContent.append('>');
//                    // System.out.println("TAG: " + '<'+tag.getText()+'>');
//                } else if (node instanceof TextNode) {
//                    int space = max_count - prvContent.length();
//                    if (space > 10) {
//                        TextNode text = (TextNode) node;
//                        if (text.getText().length() < 10)
//                            prvContent.append(text.getText());
//                        else
//                            prvContent.append(StringUtils.abbreviate(text.getText(), max_count - prvContent.length()));
//                        // System.out.println("TEXT: " + text.getText());
//                    }
//                }
//            }
//            return prvContent.toString();
//        } catch (ParserException e) {
//            e.printStackTrace();
//        } finally {
//            parser = null;
//        }
//        return html;
    }

    /**
     * Autoformat.
     */
    public static final String autoformat(String s) {
        String ret = StringUtils.replace(s, "\n", "<br />");
        return ret;
    }

    /**
     * Call escapeHTML(s, false)
     */
    public static final String escapeHTML(String s) {
        return escapeHTML(s, false);
    }

    /**
     * Escape HTML.
     * 
     * @param s
     *            string to be escaped
     * @param escapeEmpty
     *            if true, then empty string will be escaped.
     */
    public static final String escapeHTML(String s, boolean escapeEmpty) {
        if (s == null)
            return null;
        int len = s.length();
        if (len == 0) {
            return s;
        }
        if (!escapeEmpty) {
            String trimmed = s.trim();
            if ((trimmed.length() == 0) || ("\"\"").equals(trimmed)) {
                return s;
            }
        }
        int i = 0;
        // First loop through String and check if escaping is needed at all
        // No buffers are copied at this time
        do {
            int index = s.charAt(i);
            if (index >= MAX_LENGTH) {
                if (index != 0x20AC) { // If not euro symbol
                    continue;
                }
                break;
            } else if (_stringChars[index] != null) {
                break;
            }
        } while (++i < len);
        // If the check went to the end with no escaping then i should be == len
        // now
        // otherwise we must continue escaping for real
        if (i == len) {
            return s;
        }
        // We found a character to escape and broke out at position i
        // Now copy all characters before that to StringBuffer sb
        // Since a char[] will be used for copying we might as well get
        // a complete copy of it so that we can use array indexing instead of
        // charAt
        StringBuffer sb = new StringBuffer(len + 40);
        char[] chars = new char[len];
        // Copy all chars from the String s to the chars buffer
        s.getChars(0, len, chars, 0);
        // Append the first i characters that we have checked to the resulting
        // StringBuffer
        sb.append(chars, 0, i);
        int last = i;
        char[] subst;
        for (; i < len; i++) {
            char c = chars[i];
            int index = c;
            if (index < MAX_LENGTH) {
                subst = _stringChars[index];
                // It is faster to append a char[] than a String which is why we
                // use this
                if (subst != null) {
                    if (i > last) {
                        sb.append(chars, last, i - last);
                    }
                    sb.append(subst);
                    last = i + 1;
                }
            }
            // Check if it is the euro symbol. This could be changed to check in
            // a second lookup
            // table in case one wants to convert more characters in that area
            else if (index == 0x20AC) {
                if (i > last) {
                    sb.append(chars, last, i - last);
                }
                sb.append("&euro;");
                last = i + 1;
            }
        }
        if (i > last) {
            sb.append(chars, last, i - last);
        }
        return sb.toString();
    }

    /**
     * encode the url
     * 
     * @param url
     * @return
     */
    public static String encodeURL(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    protected static void addMapping(int c, String txt, String[] strings) {
        strings[c] = txt;
    }

    protected static void initMapping() {
        String[] strings = new String[MAX_LENGTH];
        addMapping(0x22, "&quot;", strings);
        addMapping(0x26, "&amp;", strings);
        addMapping(0x3c, "&lt;", strings);
        addMapping(0x3e, "&gt;", strings);
        addMapping(0xa1, "&iexcl;", strings);
        addMapping(0xa2, "&cent;", strings);
        addMapping(0xa3, "&pound;", strings);
        addMapping(0xa9, "&copy;", strings);
        addMapping(0xae, "&reg;", strings);
        addMapping(0xbf, "&iquest;", strings);
        addMapping(0xc0, "&Agrave;", strings);
        addMapping(0xc1, "&Aacute;", strings);
        addMapping(0xc2, "&Acirc;", strings);
        addMapping(0xc3, "&Atilde;", strings);
        addMapping(0xc4, "&Auml;", strings);
        addMapping(0xc5, "&Aring;", strings);
        addMapping(0xc6, "&AElig;", strings);
        addMapping(0xc7, "&Ccedil;", strings);
        addMapping(0xc8, "&Egrave;", strings);
        addMapping(0xc9, "&Eacute;", strings);
        addMapping(0xca, "&Ecirc;", strings);
        addMapping(0xcb, "&Euml;", strings);
        addMapping(0xcc, "&Igrave;", strings);
        addMapping(0xcd, "&Iacute;", strings);
        addMapping(0xce, "&Icirc;", strings);
        addMapping(0xcf, "&Iuml;", strings);
        addMapping(0xd0, "&ETH;", strings);
        addMapping(0xd1, "&Ntilde;", strings);
        addMapping(0xd2, "&Ograve;", strings);
        addMapping(0xd3, "&Oacute;", strings);
        addMapping(0xd4, "&Ocirc;", strings);
        addMapping(0xd5, "&Otilde;", strings);
        addMapping(0xd6, "&Ouml;", strings);
        addMapping(0xd7, "&times;", strings); //
        addMapping(0xd8, "&Oslash;", strings); //
        addMapping(0xd9, "&Ugrave;", strings); //
        addMapping(0xda, "&Uacute;", strings); //
        addMapping(0xdb, "&Ucirc;", strings); //
        addMapping(0xdc, "&Uuml;", strings); //
        addMapping(0xdd, "&Yacute;", strings); //
        addMapping(0xde, "&THORN;", strings); //
        addMapping(0xdf, "&szlig;", strings); //
        addMapping(0xe0, "&agrave;", strings); //
        addMapping(0xe1, "&aacute;", strings); //
        addMapping(0xe2, "&acirc;", strings); //
        addMapping(0xe3, "&atilde;", strings); //
        addMapping(0xe4, "&auml;", strings);
        addMapping(0xe5, "&aring;", strings);
        addMapping(0xe6, "&aelig;", strings); //
        addMapping(0xe7, "&ccedil;", strings); //
        addMapping(0xe8, "&egrave;", strings); //
        addMapping(0xe9, "&eacute;", strings); //
        addMapping(0xea, "&ecirc;", strings); //
        addMapping(0xeb, "&euml;", strings); //
        addMapping(0xec, "&igrave;", strings); //
        addMapping(0xed, "&iacute;", strings); //
        addMapping(0xee, "&icirc;", strings); //
        addMapping(0xef, "&iuml;", strings); //
        addMapping(0xf0, "&eth;", strings); //
        addMapping(0xf1, "&ntilde;", strings);
        addMapping(0xf2, "&ograve;", strings);
        addMapping(0xf3, "&oacute;", strings);
        addMapping(0xf4, "&ocirc;", strings);
        addMapping(0xf5, "&otilde;", strings);
        addMapping(0xf6, "&ouml;", strings); //
        addMapping(0xf7, "&divide;", strings); //
        addMapping(0xf8, "&oslash;", strings); //
        addMapping(0xf9, "&ugrave;", strings); //
        addMapping(0xfa, "&uacute;", strings); //
        addMapping(0xfb, "&ucirc;", strings); //
        addMapping(0xfc, "&uuml;", strings); //
        addMapping(0xfd, "&yacute;", strings); //
        addMapping(0xfe, "&thorn;", strings); //
        addMapping(0xff, "&yuml;", strings); //
        for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            if (str != null) {
                _stringChars[i] = str.toCharArray();
            }
        }
    }

    public static void main(String[] args) {
        String html = "<FONT CLASS=\"FrameItemFont\"><A HREF=\"org/htmlparser/lexer/package-frame.html\" target=\"packageFrame\">org.htmlparser.lexer</A></FONT><BR><FONT CLASS=\"FrameItemFont\"><A HREF=\"org/htmlparser/lexerapplications/tabby/package-frame.html\" target=\"packageFrame\">org.htmlparser.lexerapplications.tabby</A></FONT><BR><FONT CLASS=\"FrameItemFont\"><A HREF=\"org/htmlparser/lexerapplications/thumbelina/package-frame.html\" target=\"packageFrame\">org.htmlparser.lexerapplications.thumbelina</A></FONT><BR><FONT CLASS=\"FrameItemFont\"><A HREF=\"org/htmlparser/nodes/package-frame.html\" target=\"packageFrame\">org.htmlparser.nodes</A></FONT>";
        int pre_length = 150;
        String preview = preview(html, pre_length);
        System.out.println(html);
        System.out.println(html.substring(0, pre_length));
        System.out.println(preview);
    }
}
