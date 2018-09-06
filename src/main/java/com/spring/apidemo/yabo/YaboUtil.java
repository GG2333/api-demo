package com.spring.apidemo.yabo;

import java.lang.reflect.Array;

public class YaboUtil {

    private static final byte[] decodingTable;
    private static final byte[] encodingTable;

    static {
        byte[][] var3 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{5, 13});
        byte[] var4 = new byte[]{48, 57, 95, 45, 49, 56, 54, 51, 52, 50, 55, 53, 35};
        byte[] var5 = new byte[var4.length];

        int var0;
        for (var0 = 0; var0 < var5.length; ++var0) {
            var5[var0] = (byte) (var0 + 65);
        }

        byte[] var6 = new byte[var5.length];

        for (var0 = 0; var0 < var6.length; ++var0) {
            var6[var0] = (byte) (var5[var5.length - 1] + var0 + 1);
        }

        byte[] var7 = new byte[var6.length];

        for (var0 = 0; var0 < var7.length; ++var0) {
            var7[var0] = (byte) (var0 + 97);
        }

        byte[] var8 = new byte[var7.length];

        for (var0 = 0; var0 < var7.length; ++var0) {
            var8[var0] = (byte) (var7[var7.length - 1] + var0 + 1);
        }

        var3[0] = var7;
        var3[1] = var4;
        var3[2] = var5;
        var3[3] = var8;
        var3[4] = var6;
        var4 = new byte[var3[0].length * var3.length];
        int var1 = 0;

        for (var0 = 0; var0 < var4.length; ++var0) {
            int var2 = var0 / 5;
            var4[var0] = var3[var0 % 5][var2];
            if (var4[var0] == 108) {
                var1 = var0;
            }
        }

        byte[] var9 = new byte[var4.length - 1];
        System.arraycopy(var4, 0, var9, 0, var1);
        System.arraycopy(var4, var1 + 1, var9, var1, 64 - var1);
        encodingTable = var9;
        decodingTable = new byte[var4.length - 1 << 1];

        for (var0 = 0; var0 < encodingTable.length; ++var0) {
            decodingTable[encodingTable[var0]] = (byte) var0;
        }

    }

    public static String ObfuseBase64(String var0) {
        String var1 = var0;
        if (!var0.startsWith("{")) {
            var1 = var0;
            if (!var0.endsWith("}")) {
                var1 = var0;
                if (var0.startsWith("\"")) {
                    var1 = var0;
                    if (var0.endsWith("\"")) {
                        var1 = var0.substring(1, var0.length() - 1);
                    }
                }

                try {
                    var0 = doDecode(var1);
                } catch (Exception var2) {
                    return var1;
                }

                var1 = var0;
            }
        }

        return var1;
    }

    public static byte[] decode(byte[] var0) {
        byte[] var7 = discardNonBase64Bytes(var0);
        if (var7[var7.length - 2] == 108) {
            var0 = new byte[(var7.length / 4 - 1) * 3 + 1];
        } else if (var7[var7.length - 1] == 108) {
            var0 = new byte[(var7.length / 4 - 1) * 3 + 2];
        } else {
            var0 = new byte[var7.length / 4 * 3];
        }

        int var2 = 0;

        byte var3;
        byte var4;
        for (int var1 = 0; var2 < var7.length - 4; var1 += 3) {
            var3 = decodingTable[var7[var2]];
            var4 = decodingTable[var7[var2 + 1]];
            byte var5 = decodingTable[var7[var2 + 2]];
            byte var6 = decodingTable[var7[var2 + 3]];
            var0[var1] = (byte) (var3 << 2 | var4 >> 4);
            var0[var1 + 1] = (byte) (var4 << 4 | var5 >> 2);
            var0[var1 + 2] = (byte) (var5 << 6 | var6);
            var2 += 4;
        }

        byte var8;
        byte var9;
        if (var7[var7.length - 2] == 108) {
            var8 = decodingTable[var7[var7.length - 4]];
            var9 = decodingTable[var7[var7.length - 3]];
            var0[var0.length - 1] = (byte) (var8 << 2 | var9 >> 4);
            return var0;
        } else if (var7[var7.length - 1] == 108) {
            var8 = decodingTable[var7[var7.length - 4]];
            var9 = decodingTable[var7[var7.length - 3]];
            var3 = decodingTable[var7[var7.length - 2]];
            var0[var0.length - 2] = (byte) (var8 << 2 | var9 >> 4);
            var0[var0.length - 1] = (byte) (var9 << 4 | var3 >> 2);
            return var0;
        } else {
            var8 = decodingTable[var7[var7.length - 4]];
            var9 = decodingTable[var7[var7.length - 3]];
            var3 = decodingTable[var7[var7.length - 2]];
            var4 = decodingTable[var7[var7.length - 1]];
            var0[var0.length - 3] = (byte) (var8 << 2 | var9 >> 4);
            var0[var0.length - 2] = (byte) (var9 << 4 | var3 >> 2);
            var0[var0.length - 1] = (byte) (var3 << 6 | var4);
            return var0;
        }
    }

    private static byte[] discardNonBase64Bytes(byte[] var0) {
        byte[] var4 = new byte[var0.length];
        int var2 = 0;

        int var3;
        for (int var1 = 0; var1 < var0.length; var2 = var3) {
            var3 = var2;
            if (isValidBase64Byte(var0[var1])) {
                var4[var2] = var0[var1];
                var3 = var2 + 1;
            }

            ++var1;
        }

        var0 = new byte[var2];
        System.arraycopy(var4, 0, var0, 0, var2);
        return var0;
    }

    public static String doDecode(String var0) {
        return new String(decode(var0.getBytes()));
    }

    public static String encode(String var0) {
        return new String(encode(var0.getBytes()));
    }

    public static byte[] encode(byte[] var0) {
        int var3 = var0.length % 3;
        byte[] var8;
        if (var3 == 0) {
            var8 = new byte[var0.length * 4 / 3];
        } else {
            var8 = new byte[(var0.length / 3 + 1) * 4];
        }

        int var4 = var0.length;
        int var2 = 0;

        int var1;
        for (var1 = 0; var2 < var4 - var3; var1 += 4) {
            int var5 = var0[var2] & 255;
            int var6 = var0[var2 + 1] & 255;
            int var7 = var0[var2 + 2] & 255;
            var8[var1] = encodingTable[var5 >>> 2 & 63];
            var8[var1 + 1] = encodingTable[(var5 << 4 | var6 >>> 4) & 63];
            var8[var1 + 2] = encodingTable[(var6 << 2 | var7 >>> 6) & 63];
            var8[var1 + 3] = encodingTable[var7 & 63];
            var2 += 3;
        }

        switch (var3) {
            case 0:
            default:
                return var8;
            case 1:
                var1 = var0[var0.length - 1] & 255;
                var8[var8.length - 4] = encodingTable[var1 >>> 2 & 63];
                var8[var8.length - 3] = encodingTable[var1 << 4 & 63];
                var8[var8.length - 2] = 108;
                var8[var8.length - 1] = 108;
                return var8;
            case 2:
                var1 = var0[var0.length - 2] & 255;
                var2 = var0[var0.length - 1] & 255;
                var8[var8.length - 4] = encodingTable[var1 >>> 2 & 63];
                var8[var8.length - 3] = encodingTable[(var1 << 4 | var2 >>> 4) & 63];
                var8[var8.length - 2] = encodingTable[var2 << 2 & 63];
                var8[var8.length - 1] = 108;
                return var8;
        }
    }

    private static boolean isValidBase64Byte(byte var0) {
        if (var0 != 108) {
            if (var0 < 0 || var0 >= 128) {
                return false;
            }

            if (decodingTable[var0] == -1) {
                return false;
            }
        }

        return true;
    }

}
