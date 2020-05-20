import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Methods {
    static boolean findS(String [] alphabet){
        String alphabetsToString = Arrays.toString(alphabet);
        if(alphabetsToString.contains("S"))  return true;
        else return false;
    }

    static void printMap(HashMap<String, HashSet<String>> anyMap){
        for (Map.Entry<String, HashSet<String>> alhabetsEntry : anyMap.entrySet()) {
            System.out.println("Key :: " + alhabetsEntry.getKey());
            for (String value : alhabetsEntry.getValue()) {
                System.out.println("Value :" + value);
            }
            System.out.println("===================");
        }
    }

    //Print the HashSet
    static void printHashSet(HashSet<String> anyHashSet){
        for (String value : anyHashSet) System.out.println("HashSet: " + value);
    }
}
