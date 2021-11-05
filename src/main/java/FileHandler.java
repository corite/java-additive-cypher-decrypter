import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    public ArrayList<Byte> getBytesFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] bytes = Files.readAllBytes(path);
        ArrayList<Byte> returnArray = new ArrayList<>();
        for (int i=0; i<bytes.length ; i++) {
            returnArray.add(bytes[i]);
        }
        return returnArray;
    }
}
