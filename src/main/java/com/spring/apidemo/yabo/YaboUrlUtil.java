package com.spring.apidemo.yabo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.BitSet;

public class YaboUrlUtil {

    static BitSet dontNeedEncoding = new BitSet(256);

    static {
        int var0;
        for(var0 = 97; var0 <= 122; ++var0) {
            dontNeedEncoding.set(var0);
        }

        for(var0 = 65; var0 <= 90; ++var0) {
            dontNeedEncoding.set(var0);
        }

        for(var0 = 48; var0 <= 57; ++var0) {
            dontNeedEncoding.set(var0);
        }

        dontNeedEncoding.set(32);
        dontNeedEncoding.set(45);
        dontNeedEncoding.set(95);
        dontNeedEncoding.set(46);
        dontNeedEncoding.set(42);
        dontNeedEncoding.set(43);
        dontNeedEncoding.set(37);
    }

    public YaboUrlUtil() {
    }

    public static String decodeTextIfEncode(String var0) {
        String var1 = var0;
        if (isURLEncoded(var0)) {
            var1 = decodeURL(var0);
        }

        return var1;
    }

    public static final String decodeURL(String var0) {
        try {
            if (!"%".equals(var0) && !var0.contains("%%")) {
                var0 = URLDecoder.decode(var0, "utf-8");
                return "%".equals(var0) ? "\\%" : var0;
            } else {
                return "\\" + var0;
            }
        } catch (UnsupportedEncodingException var1) {
            throw new RuntimeException(var1);
        }
    }

    public static final String encodeURL(String var0) {
        try {
            var0 = URLEncoder.encode(var0, "utf-8");
            return var0;
        } catch (UnsupportedEncodingException var1) {
            throw new RuntimeException(var1);
        }
    }

    public static final boolean isURLEncoded(String var0) {
        if (var0 != null && !"".equals(var0.trim())) {
            char[] var5 = var0.toCharArray();
            boolean var3 = false;
            int var4 = var5.length;
            int var2 = 0;

            while(true) {
                if (var2 >= var4) {
                    if (var3) {
                        return true;
                    }
                    break;
                }

                char var1 = var5[var2];
                if (Character.isWhitespace(var1) || !dontNeedEncoding.get(var1)) {
                    break;
                }

                if (var1 == '%') {
                    var3 = true;
                }

                ++var2;
            }
        }

        return false;
    }

    public static String replaceHtml(String var0) {
        return var0.replace("<", "&#60;").replace(">", "&#62;");
    }

}
