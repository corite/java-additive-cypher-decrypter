import java.util.*;
import java.util.stream.Collectors;

public class CryptHandler {

    public int getKey(ArrayList<Byte> encryptedText, Byte mostFrequentDecryptedByte) {
        HashMap<Byte, Double> byteFrequencyMap = getByteFrequencyMap(encryptedText);

        Byte mostFrequentEncryptedByte = getMostFrequentByte(byteFrequencyMap);
        return (mostFrequentEncryptedByte-mostFrequentDecryptedByte+128)%128;
        //adding 128 before modulo because java thinks p.e -2 % 10 = -2 != 8
    }

    /**
     * @param encryptedText the text to analyse
     * @return a map with each byte of the text as a key and the frequency in which it occurs in the text as value
     */
    private HashMap<Byte, Double> getByteFrequencyMap(ArrayList<Byte> encryptedText) {
        ArrayList<Byte> distinctBytes = encryptedText.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        //gets the distinct bytes from the text, so that we can put it in the frequency-map later
        HashMap<Byte, Double> byteDoubleMap = new HashMap<>();
        for (Byte distinctByte : distinctBytes) {
            long numberOfOccurrences = encryptedText.stream().filter(b -> b.byteValue() == distinctByte.byteValue()).count();
            //number of times this byte is present in the text
            byteDoubleMap.put(distinctByte, Double.valueOf(numberOfOccurrences)/Double.valueOf(encryptedText.size()));
        }
        return byteDoubleMap;
    }

    private Byte getMostFrequentByte(HashMap<Byte, Double> byteFrequencyMap) {
        return byteFrequencyMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .findFirst().get();
                //get the first entry since it is the most common one
    }

    public ArrayList<Byte> decrypt(ArrayList<Byte> byteArray, int key) {
        ArrayList<Byte> decryptedBytes = new ArrayList<>();
        for (Byte encByte : byteArray) {
            decryptedBytes.add(((Integer) (((encByte - key)+128) % 128)).byteValue());
            //adding 128 before modulo because java thinks p.e -2 % 10 = -2 != 8
        }

        return decryptedBytes;
    }

    public String asString(ArrayList<Byte> byteArray) {
        //Convert all bytes to characters end concatenate them to a String
        return byteArray.stream()
                .map(Character::toString)
                .collect(Collectors.joining());
    }
}
