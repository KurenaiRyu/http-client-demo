package io.github.natsusai.httpclient.demo.util;

/**
 * @author Kurenai
 * @since 2020-11-10 16:30
 */

public class StringUtil {

  public static boolean areNotEmpty(String... values) {
    boolean result = true;
    if (values != null && values.length != 0) {
      String[] arr$ = values;
      int len$ = values.length;

      for (int i$ = 0; i$ < len$; ++i$) {
        String value = arr$[i$];
        result &= !isEmpty(value);
      }
    } else {
      result = false;
    }

    return result;
  }

  public static boolean isEmpty(String value) {
    int strLen;
    if (value != null && (strLen = value.length()) != 0) {
      for (int i = 0; i < strLen; ++i) {
        if (!Character.isWhitespace(value.charAt(i))) {
          return false;
        }
      }

      return true;
    } else {
      return true;
    }
  }

  public static boolean startsWith(CharSequence str, CharSequence prefix) {
    return startsWith(str, prefix, false);
  }

  private static boolean startsWith(CharSequence str, CharSequence prefix, boolean ignoreCase) {
    if (str != null && prefix != null) {
      return prefix.length() <= str.length() && regionMatches(str, ignoreCase, 0, prefix, 0,
          prefix.length());
    } else {
      return str == prefix;
    }
  }

  public static String toUnderlineCase(String str) {
    final char symbol = '_';

    if (str == null) {
      return null;
    }

    final int length = str.length();
    final StringBuilder sb = new StringBuilder();
    char c;
    for (int i = 0; i < length; i++) {
      c = str.charAt(i);
      final Character preChar = (i > 0) ? str.charAt(i - 1) : null;
      if (Character.isUpperCase(c)) {
        // 遇到大写字母处理
        final Character nextChar = (i < str.length() - 1) ? str.charAt(i + 1) : null;
        if (null != preChar && Character.isUpperCase(preChar)) {
          // 前一个字符为大写，则按照一个词对待
          sb.append(c);
        } else if (null != nextChar && Character.isUpperCase(nextChar)) {
          // 后一个为大写字母，按照一个词对待
          if (null != preChar && symbol != preChar) {
            // 前一个是非大写时按照新词对待，加连接符
            sb.append(symbol);
          }
          sb.append(c);
        } else {
          // 前后都为非大写按照新词对待
          if (null != preChar && symbol != preChar) {
            // 前一个非连接符，补充连接符
            sb.append(symbol);
          }
          sb.append(Character.toLowerCase(c));
        }
      } else {
        if (sb.length() > 0 && Character.isUpperCase(sb.charAt(sb.length() - 1)) && symbol != c) {
          // 当结果中前一个字母为大写，当前为小写，说明此字符为新词开始（连接符也表示新词）
          sb.append(symbol);
        }
        // 小写或符号
        sb.append(c);
      }
    }
    return sb.toString();

  }

  private static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart,
      CharSequence substring, int start, int length) {
    if (cs instanceof String && substring instanceof String) {
      return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
    } else {
      int index1 = thisStart;
      int index2 = start;
      int tmpLen = length;
      int srcLen = cs.length() - thisStart;
      int otherLen = substring.length() - start;
      if (thisStart >= 0 && start >= 0 && length >= 0) {
        if (srcLen >= length && otherLen >= length) {
          while(tmpLen-- > 0) {
            char c1 = cs.charAt(index1++);
            char c2 = substring.charAt(index2++);
            if (c1 != c2) {
              if (!ignoreCase) {
                return false;
              }

              if (Character.toUpperCase(c1) != Character.toUpperCase(c2) && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                return false;
              }
            }
          }

          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
  }

}
