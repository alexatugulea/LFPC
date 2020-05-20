import java.util.*;
import java.lang.*;

public class TwoUnits {
    public Map<String, HashSet<String>> alphabets;
    public Map<String, HashSet<String>> tmpMap;
    public Map<String, HashSet<String>> anotherTmpMap;
    public HashSet<String> setForTwoUnits;
    public HashSet<String> setForTwo;
    public HashSet<String> anotherTmpSet;

    public String forTwo;
    public String keyOfTwo;
    public String keyToAdd; //like X
    public String valueToRemove; // like AB
    public String newString; //like adAdX
    public char charToAdd;

    public String [] newStates;
    public int count;

    public TwoUnits(Map<String, HashSet<String>> alphabets, String [] newStates) {
        this.alphabets = alphabets;
        this.newStates = newStates;
        setForTwoUnits = new HashSet<>();
        setForTwo = new HashSet<>();
        anotherTmpSet = new HashSet<>();
        tmpMap = new HashMap<>();
        anotherTmpMap = new HashMap<>();

        count = 0;

        //find unit productions with 2 Units & add the to the setForTwoUnits
        for (String nonterminal : alphabets.keySet()) {
            for (String value : alphabets.get(nonterminal)) {
                if (value.length() >= 2)
                    if(checkIfTwo(value, nonterminal))
                            setForTwoUnits.add(value);
                            anotherTmpSet.add(keyOfTwo);
                            anotherTmpMap.put(keyOfTwo, setForTwoUnits);
                tmpMap.put(newStates[count], createNewSets(forTwo));
                            charToAdd = newStates[count].charAt(0);



            }
        }

        for(int i = 0; i < newStates.length; i++){
            if(newStates[i] == newStates[count]){
                // shifting elements
                for(int j = i; j < newStates.length - 1; j++){
                    newStates[j] = newStates[j+1];
                }
                break;
            }
        }

        for (String nonterminal : alphabets.keySet()) {
            for (String value : alphabets.get(nonterminal)) {
                if(checkIfTwo(value, nonterminal)){
                if(containString(value, (HashMap<String, HashSet<String>>) tmpMap) && findIndexes(value));{
                    newExpressions((HashMap<String, HashSet<String>>) anotherTmpMap, value,nonterminal, valueToRemove, keyToAdd);
                }
                }
            }
        }
        for(String key:anotherTmpMap.keySet()){
            HashSet<String> set= new HashSet<String>() {{
                addAll(alphabets.get(key));
                addAll(anotherTmpMap.get(key)); } };
            alphabets.put(key,set);
        }

        alphabets.putAll(tmpMap);

        for( String value1 : anotherTmpSet){
            for ( String value2 : setForTwoUnits ){
                    alphabets.get(value1).remove(value2);
            }
        }
    }

    //METHODS
    public boolean checkIfTwo(String s, String nonterm) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (Character.isUpperCase(s.charAt(i)) && Character.isUpperCase(s.charAt(i + 1))) {

                forTwo = s.substring(i, i + 2);
                count++;

                keyOfTwo = nonterm;
                return true;
            }

        } return false;
    }
    public boolean findIndexes(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (Character.isUpperCase(s.charAt(i)) && Character.isUpperCase(s.charAt(i + 1)))
            return true;
        }
        return false;
    }

    public boolean containString(String value, HashMap<String, HashSet<String>> map){
        for( String key : map.keySet()){
           for (String str : map.get(key)){
               if (value.contains(str)){
                   keyToAdd = key;
                   valueToRemove = str;
                   return true;
               }
           }
        }
        return false;
    }

    public void newExpressions(HashMap<String, HashSet<String>> anAlphabet, String expression, String nonterm, String valRemove, String keyAdd){
       char keyAddToChar = keyAdd.charAt(0);
       char valRemoveToChar0 = valRemove.charAt(0);
       char valRemoveToChar1 = valRemove.charAt(1);
        int len = expression.length();

            if (expression.charAt(0) == valRemoveToChar0 && expression.charAt(1) == valRemoveToChar1 ){ // case !
                len = expression.length();
                newString = expression.substring(2, len);
                newString =  keyAddToChar + newString;
                HashSet aSet = new HashSet();
                aSet.add(newString);
                anAlphabet.put(nonterm, aSet);
            } else
                if(expression.charAt(len-2) == valRemoveToChar0 && expression.charAt(len-1) == valRemoveToChar1 ){ // case !
                    String a = expression.substring(0, len-2) + keyAddToChar;
                    //newString = newString + keyAddToChar;
                    HashSet aSet = new HashSet();
                    aSet.add(a);
                    anAlphabet.put(nonterm, aSet);
                 } else
                     for (int i = 2; i < len -2; i++){
                         if(expression.charAt(i) == valRemoveToChar0 && expression.charAt(i+1) == valRemoveToChar1 ){ // case !
                             newString = expression.substring(0, i-1) + keyAddToChar + expression.substring(i+2, len);
                             HashSet aSet = new HashSet();
                             aSet.add(newString);
                             anAlphabet.put(nonterm, aSet);
                         }
                     }
                    }
    public HashSet<String> createNewSets(String expression) {
        HashSet<String> newSet = new HashSet();
        newSet.add(expression);
        return newSet;
    }
                }



