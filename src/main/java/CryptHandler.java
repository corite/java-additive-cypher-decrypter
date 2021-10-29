

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CryptHandler {

    public ArrayList<Byte> getDecryptedText(ArrayList<Byte> encryptedText) {
        HashMap<Byte, Double> byteFrequencyMap = getByteFrequencyMap(encryptedText);
        Byte space = Byte.valueOf(" ");
        Byte mostFrequentByte = getMostFrequentByte(byteFrequencyMap);
        int key = mostFrequentByte-space;
        return decrypt(encryptedText,key);
    }

    private HashMap<Byte, Double> getByteFrequencyMap(ArrayList<Byte> encryptedText) {
        ArrayList<Byte> distinctBytes = encryptedText.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        HashMap<Byte, Double> byteDoubleMap = new HashMap<>();
        for (Byte distinctByte : distinctBytes) {
            long numberOfOccurrences = encryptedText.stream().filter(b -> b.byteValue() == distinctByte.byteValue()).count();
            byteDoubleMap.put(distinctByte, Double.valueOf(numberOfOccurrences)/Double.valueOf(encryptedText.size()));
        }
        return byteDoubleMap;
    }

    private LinkedHashMap<Byte,Double> getSortedMap(Map<Byte,Double> unsortedMap) {
        return unsortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
    private Byte getMostFrequentByte(HashMap<Byte, Double> byteFrequencyMap) {
        LinkedHashMap<Byte,Double> sortedMap =getSortedMap(byteFrequencyMap);
        return (Byte) sortedMap.entrySet().toArray()[0];
    }

    private ArrayList<Byte> decrypt(ArrayList<Byte> byteArray, int key) {
        return byteArray.stream().map(b -> b - key).collect(Collectors.toCollection((Supplier<ArrayList>) ArrayList::new));
    }
}
