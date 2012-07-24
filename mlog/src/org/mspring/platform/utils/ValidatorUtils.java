/**
 * 
 */
package org.mspring.platform.utils;

/**
 * @author Gao Youbo
 * @since 2012-7-13
 * @Description
 * @TODO
 */
public class ValidatorUtils {

    public static final String AT = "@";
    public static final String NULL = "null";
    public static final String BLANK = "";

    public static boolean equals(String s1, String s2) {
        if ((s1 == null) && (s2 == null)) {
            return true;
        }
        else if ((s1 == null) || (s2 == null)) {
            return false;
        }
        else {
            return s1.equals(s2);
        }
    }

    public static boolean isAddress(String address) {
        if (isNull(address)) {
            return false;
        }

        String[] tokens = address.split(AT);

        if (tokens.length != 2) {
            return false;
        }

        for (int i = 0; i < tokens.length; i++) {
            char[] c = tokens[i].toCharArray();

            for (int j = 0; j < c.length; j++) {
                if (Character.isWhitespace(c[j])) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isChar(char c) {
        return Character.isLetter(c);
    }

    public static boolean isChar(String s) {
        if (isNull(s)) {
            return false;
        }

        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (!isChar(c[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean isDigit(char c) {
        int x = (int) c;

        if ((x >= 48) && (x <= 57)) {
            return true;
        }

        return false;
    }

    public static boolean isDigit(String s) {
        if (isNull(s)) {
            return false;
        }

        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (!isDigit(c[i])) {
                return false;
            }
        }

        return true;
    }

    public static boolean isHex(String s) {
        if (isNull(s)) {
            return false;
        }

        return true;
    }

    public static boolean isHTML(String s) {
        if (isNull(s)) {
            return false;
        }

        if (((s.indexOf("<html>") != -1) || (s.indexOf("<HTML>") != -1)) && ((s.indexOf("</html>") != -1) || (s.indexOf("</HTML>") != -1))) {

            return true;
        }

        return false;
    }

    public static boolean isLUHN(String number) {
        if (number == null) {
            return false;
        }

        number = StringUtils.reverse(number);

        int total = 0;

        for (int i = 0; i < number.length(); i++) {
            int x = 0;

            if (((i + 1) % 2) == 0) {
                x = Integer.parseInt(number.substring(i, i + 1)) * 2;

                if (x >= 10) {
                    String s = Integer.toString(x);

                    x = Integer.parseInt(s.substring(0, 1)) + Integer.parseInt(s.substring(1, 2));
                }
            }
            else {
                x = Integer.parseInt(number.substring(i, i + 1));
            }

            total = total + x;
        }

        if ((total % 10) == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isEmailAddress(String ea) {
        if (isNull(ea)) {
            return false;
        }

        int eaLength = ea.length();

        if (eaLength < 6) {

            return false;
        }

        ea = ea.toLowerCase();

        int at = ea.indexOf('@');
        int maxEmailLength = 48;

        if ((at > maxEmailLength) || (at == -1) || (at == 0) || ((at <= eaLength) && (at > eaLength - 5))) {

            return false;
        }

        int dot = ea.lastIndexOf('.');

        if ((dot == -1) || (dot < at) || (dot > eaLength - 3)) {

            return false;
        }

        if (ea.indexOf("..") != -1) {

            return false;
        }

        char[] name = ea.substring(0, at).toCharArray();

        for (int i = 0; i < name.length; i++) {
            if ((!isChar(name[i])) && (!isDigit(name[i])) && (name[i] != '.') && (name[i] != '-') && (name[i] != '_')) {

                return false;
            }
        }

        if ((name[0] == '.') || (name[name.length - 1] == '.') || (name[0] == '-') || (name[name.length - 1] == '-') || (name[0] == '_')) {

            return false;
        }

        char[] host = ea.substring(at + 1, ea.length()).toCharArray();

        for (int i = 0; i < host.length; i++) {
            if ((!isChar(host[i])) && (!isDigit(host[i])) && (host[i] != '.') && (host[i] != '-')) {

                return false;
            }
        }

        if ((host[0] == '.') || (host[host.length - 1] == '.') || (host[0] == '-') || (host[host.length - 1] == '-')) {

            return false;
        }

        if (ea.startsWith("postmaster@")) {
            return false;
        }

        if (ea.startsWith("root@")) {
            return false;
        }

        return true;
    }

    public static boolean isValidEmailAddress(String ea) {
        return isEmailAddress(ea);
    }

    public static boolean isName(String name) {
        if (isNull(name)) {
            return false;
        }

        char[] c = name.trim().toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (((!isChar(c[i])) && (!Character.isWhitespace(c[i]))) || (c[i] == ',')) {

                return false;
            }
        }

        return true;
    }

    public static boolean isNumber(String number) {
        if (isNull(number)) {
            return false;
        }

        char[] c = number.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (!isDigit(c[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNull(Object[] o) {
        if (o == null || o.length == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isNull(String s) {
        if (s == null) {
            return true;
        }

        s = s.trim();

        if ((s.equals(NULL)) || (s.equals(BLANK))) {
            return true;
        }

        return false;
    }

    public static boolean isNotNull(String s) {
        return !isNull(s);
    }

    public static boolean isPassword(String password) {
        if (isNull(password)) {
            return false;
        }

        if (password.length() < 4) {
            return false;
        }

        char[] c = password.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if ((!isChar(c[i])) && (!isDigit(c[i]))) {

                return false;
            }
        }

        return true;
    }

    public static boolean isPhoneNumber(String phoneNumber) {
        return isNumber(StringUtils.extractDigits(phoneNumber));
    }
}
