/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.util;

/**
 *
 * @author Ernesto
 */
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Security {

    private static final byte[] key = {0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41,
        0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x73};

    public static String decrypt(String strToDecrypt) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt
                    .getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
