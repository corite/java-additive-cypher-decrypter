import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main (String [] args) {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Byte> encryptedBytes = new ArrayList<>();
        try {
            encryptedBytes = fileHandler.getBytesFromFile("encryptedFile");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        CryptHandler cryptHandler = new CryptHandler();
        ArrayList<Byte> decryptedBytes = cryptHandler.getDecryptedText(encryptedBytes);



    }
}
