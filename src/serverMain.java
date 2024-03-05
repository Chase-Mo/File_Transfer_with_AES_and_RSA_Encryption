import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class serverMain {
    public static void main(String args[]) throws Exception {

        titleHolder.serverTitle();
        clientServer mine = new clientServer();
        serverServer mine2 = new serverServer();

        //bs to keep it from starting w/o me
        Scanner myObj = new Scanner(System.in);
        String radVariablelmaoPleasedontreadme = myObj.nextLine();

        //Get public key
        mine2.recieveSomething("rec_pubkey.pem", 1738);
        System.out.println("Recived Public Key: Encrypting Key and File");
        CryptMethods.getPublicKeyFromFile("rec_pubkey.pem");

        //Start encrypting with AES
        SecretKey tempoKey = CryptMethods.generateAESKey(128);
        IvParameterSpec tempoIV = CryptMethods.generateIv();

        //Encrypt AESkey with RSA Public key
        CryptMethods.encryptFileAES(tempoKey, tempoIV, new File("DTA.jpg"),
                new File("secretEncrypted"));
        CryptMethods.saveAESKeyToFile(tempoKey, "AESkey.pem");
        CryptMethods.encryptFile("AESkey.pem", "AESkeyEnc",
                CryptMethods.getPublicKeyFromFile("rec_pubkey.pem"));




        System.out.println("File encrypted: Sending back...");
        CryptMethods.waitForMe(7000);
        mine.sendSomething("secretEncrypted", "127.0.0.1", 1739);
        CryptMethods.waitForMe(5000);
        mine.sendSomething("AESkeyEnc", "127.0.0.1", 1737);
        System.out.println("My work here is Done!");

    }
}
