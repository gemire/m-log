package org.mspring.platform.security.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class PathMapper {
    private Map mappings = new HashMap();
    static String[] DEFAULT_KEYS = { "/", "*", "/*" };

    private List complexPaths = new ArrayList();

    public void put(String key, String pattern) {
        if (key != null)
            this.mappings.put(pattern, key);
    }

    public boolean isEmpty() {
        return this.mappings.isEmpty();
    }

    public String get(String path) {
        if (path == null)
            path = "/";
        String mapped = findKey(path, this.mappings);
        if (mapped == null)
            return null;
        return (String) this.mappings.get(mapped);
    }

    public Collection getAll(String path) {
        if (path == null) {
            path = "/";
        }
        List matches = new ArrayList();

        String exactKey = findExactKey(path, this.mappings);

        if (exactKey != null) {
            matches.add(this.mappings.get(exactKey));
        }

        Iterator iterator = findComplexKeys(path, this.complexPaths).iterator();
        while (iterator.hasNext()) {
            String mapped = (String) iterator.next();
            matches.add(this.mappings.get(mapped));
        }

        iterator = findDefaultKeys(this.mappings).iterator();
        while (iterator.hasNext()) {
            String mapped = (String) iterator.next();
            matches.add(this.mappings.get(mapped));
        }

        return matches;
    }

    private static String findKey(String path, Map mappings) {
        String result = findExactKey(path, mappings);
        if (result == null)
            result = findComplexKey(path, mappings);
        if (result == null)
            result = findDefaultKey(mappings);
        return result;
    }

    private static String findExactKey(String path, Map mappings) {
        if (mappings.containsKey(path))
            return path;
        return null;
    }

    private static String findComplexKey(String path, Map mappings) {
        Iterator i = mappings.keySet().iterator();
        String result = null;
        String key = null;
        while (i.hasNext()) {
            key = (String) i.next();
            if ((key.length() <= 1) || ((key.indexOf('?') == -1) && (key.indexOf('*') == -1)) || (!match(key, path, false)) || ((result != null) && (key.length() <= result.length())))
                continue;
            result = key;
        }

        return result;
    }

    private static String findDefaultKey(Map mappings) {
        String[] defaultKeys = { "/", "*", "/*" };
        for (int i = 0; i < defaultKeys.length; i++) {
            if (mappings.containsKey(defaultKeys[i]))
                return defaultKeys[i];
        }
        return null;
    }

    private static boolean match(String pattern, String str, boolean isCaseSensitive) {
        char[] patArr = pattern.toCharArray();
        char[] strArr = str.toCharArray();
        int patIdxStart = 0;
        int patIdxEnd = patArr.length - 1;
        int strIdxStart = 0;
        int strIdxEnd = strArr.length - 1;

        boolean containsStar = false;
        for (int i = 0; i < patArr.length; i++) {
            if (patArr[i] == '*') {
                containsStar = true;
                break;
            }
        }

        if (!containsStar) {
            if (patIdxEnd != strIdxEnd) {
                return false;
            }
            for (int i = 0; i <= patIdxEnd; i++) {
                char ch = patArr[i];
                if (ch != '?') {
                    if ((isCaseSensitive) && (ch != strArr[i])) {
                        return false;
                    }
                    if ((!isCaseSensitive) && (Character.toUpperCase(ch) != Character.toUpperCase(strArr[i]))) {
                        return false;
                    }
                }
            }
            return true;
        }

        if (patIdxEnd == 0)
            return true;
        char ch;
        while (((ch = patArr[patIdxStart]) != '*') && (strIdxStart <= strIdxEnd)) {
            if (ch != '?') {
                if ((isCaseSensitive) && (ch != strArr[strIdxStart])) {
                    return false;
                }
                if ((!isCaseSensitive) && (Character.toUpperCase(ch) != Character.toUpperCase(strArr[strIdxStart]))) {
                    return false;
                }
            }
            patIdxStart++;
            strIdxStart++;
        }
        if (strIdxStart > strIdxEnd) {
            for (int i = patIdxStart; i <= patIdxEnd; i++) {
                if (patArr[i] != '*') {
                    return false;
                }
            }
            return true;
        }

        while (((ch = patArr[patIdxEnd]) != '*') && (strIdxStart <= strIdxEnd)) {
            if (ch != '?') {
                if ((isCaseSensitive) && (ch != strArr[strIdxEnd])) {
                    return false;
                }
                if ((!isCaseSensitive) && (Character.toUpperCase(ch) != Character.toUpperCase(strArr[strIdxEnd]))) {
                    return false;
                }
            }
            patIdxEnd--;
            strIdxEnd--;
        }
        if (strIdxStart > strIdxEnd) {
            for (int i = patIdxStart; i <= patIdxEnd; i++) {
                if (patArr[i] != '*') {
                    return false;
                }
            }
            return true;
        }

        while ((patIdxStart != patIdxEnd) && (strIdxStart <= strIdxEnd)) {
            int patIdxTmp = -1;
            for (int i = patIdxStart + 1; i <= patIdxEnd; i++) {
                if (patArr[i] == '*') {
                    patIdxTmp = i;
                    break;
                }
            }
            if (patIdxTmp == patIdxStart + 1) {
                patIdxStart++;
                continue;
            }

            int patLength = patIdxTmp - patIdxStart - 1;
            int strLength = strIdxEnd - strIdxStart + 1;
            int foundIdx = -1;

            for (int i = 0; i <= strLength - patLength; i++) {
                int j = 0;
                while (true)
                    if (j < patLength) {
                        ch = patArr[(patIdxStart + j + 1)];
                        if (ch != '?') {
                            if ((isCaseSensitive) && (ch != strArr[(strIdxStart + i + j)])) {
                                break;
                            }
                            if ((!isCaseSensitive) && (Character.toUpperCase(ch) != Character.toUpperCase(strArr[(strIdxStart + i + j)])))
                                break;
                        } else {
                            j++;
                            continue;
                        }

                    } else {
                        foundIdx = strIdxStart + i;
                        if (foundIdx == -1) {
                            break;
                        }
                    }
            }

            patIdxStart = patIdxTmp;
            strIdxStart = foundIdx + patLength;
        }

        for (int i = patIdxStart; i <= patIdxEnd; i++) {
            if (patArr[i] != '*') {
                return false;
            }
        }
        return true;
    }

    private static Collection findDefaultKeys(Map mappings) {
        List matches = new ArrayList();

        for (int i = 0; i < DEFAULT_KEYS.length; i++) {
            if (mappings.containsKey(DEFAULT_KEYS[i])) {
                matches.add(DEFAULT_KEYS[i]);
            }
        }
        return matches;
    }

    private static Collection findComplexKeys(String path, List complexPaths) {
        int size = complexPaths.size();
        List matches = new ArrayList();
        for (int i = 0; i < size; i++) {
            String key = (String) complexPaths.get(i);
            if (match(key, path, false)) {
                matches.add(key);
            }
        }
        return matches;
    }

    public String toString() {
        return "PathMapper[" + this.mappings.toString() + "]";
    }
}