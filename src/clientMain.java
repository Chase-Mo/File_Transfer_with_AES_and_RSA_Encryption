import java.io.File;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class clientMain {
    public static void main(String[] args) throws Exception {

        titleHolder.clientTitle();
        //The laziest way to go about sending files
        serverServer mine = new serverServer();
        clientServer mine2 = new clientServer();

        //bs to keep it from starting w/o me
        Scanner myObj = new Scanner(System.in);
        String radVariablelmaoPleasedontreadme = myObj.nextLine();

        System.out.println("Generating Key Pair");
        KeyPair keypair = CryptMethods.generateKeyPair();
        PublicKey publicKey = keypair.getPublic();
        PrivateKey privateKey = keypair.getPrivate();
        CryptMethods.saveKeyToFile(publicKey, "pubkey.pem");
        System.out.println("Sending public key...");
        mine2.sendSomething("pubkey.pem", "127.0.0.2", 1738);

        //wait for 5 seconds, because.
        System.out.println("Waiting for file");
        CryptMethods.waitForMe(5000);


        mine.recieveSomething("encryptedfile", 1739);

        System.out.println("Waiting for key");
        mine.recieveSomething("encryptedkey", 1737);

        System.out.println("We got it! \n lets decrypt it...");

        CryptMethods.decryptFile("encryptedkey", "decryptedAES.pem", privateKey);
        CryptMethods.decryptFileAES(CryptMethods.loadAESKeyFromFile("decryptedAES.pem"),
                CryptMethods.generateIv(), new File("encryptedfile"),
                new File("im over here but viewable.jpg"));



    }
}
