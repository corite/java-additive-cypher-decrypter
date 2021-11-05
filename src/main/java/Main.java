import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main (String [] args) {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Byte> encryptedBytes = new ArrayList<>();
        try {
            //read file
            encryptedBytes = fileHandler.getBytesFromFile("LoremIpsumEncrypted.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        CryptHandler cryptHandler = new CryptHandler();
        int key = cryptHandler.getKey(encryptedBytes,(byte) ' ');
        System.out.println("Key: "+key);
        ArrayList<Byte> decryptedBytes = cryptHandler.decrypt(encryptedBytes,key);
        System.out.println("Decrypted Text: "+cryptHandler.asString(decryptedBytes));


    }
}
