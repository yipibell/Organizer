package Utility;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

public class Encryption {
    public static byte[] Encryption(String string, String skey) {
        byte[] Seald;
        try {
            Cipher cipher = Core(Cipher.ENCRYPT_MODE, skey);
            Seald = cipher.doFinal(string.getBytes());
            return Seald;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return Seald = null;
    }

    public static byte[] Decryption(byte[] bytes, String skey) {
        byte[] Seald;
        try {
            Cipher cipher = Core(Cipher.DECRYPT_MODE, "login");
            Seald = cipher.doFinal(bytes);
            return Seald;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return Seald = null;
    }

    private static Cipher Core(int mode, String skey) {
        try {
            Random random = new Random(1010101L);
            byte[] salt = new byte[8];
            random.nextBytes(salt);

            PBEParameterSpec spec = new PBEParameterSpec(salt, 5);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(new PBEKeySpec("Loginfile".toCharArray()));
            Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
            cipher.init(mode, key, spec);
            return cipher;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
}