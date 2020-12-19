package org.market.hedge.bibox.coinswap.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class MacUtils {

    public static byte[] buildMAC(String msg, String algo, String secretKey) {
        try {
            Mac mac = Mac.getInstance(algo);
            mac.init(new SecretKeySpec(
                    secretKey.getBytes(StandardCharsets.UTF_8), algo));
            return mac.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}