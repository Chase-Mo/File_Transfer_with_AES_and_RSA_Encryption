import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class aestest {
    public static void main(String args[]) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        SecretKey key = CryptMethods.generateAESKey(128);
        CryptMethods.encryptFileAES(key, CryptMethods.generateIv(), new File("beemovie.txt")
        , new File("beemovieenc"));
        CryptMethods.decryptFileAES(key, CryptMethods.generateIv(), new File("beemovieenc"),
        new File("beemovieasdfasdf.txt"));
    }
}
