import java.util.*;
import java.lang.*;

public class Units {
    public String[] terminalsCfg;
    public String[] nonterminalsCfg;
    public String[] newStates;
    public Map<String, HashSet<String>> alphabets;
    public Map<String, HashSet<String>> unitTmpMap;
    public Map<String, HashSet<String>> tmpMap;

    public HashSet<String> anotherSet;
    public HashSet<String> unitTmpHashSet;
    public HashSet<String> forKeysOfUnits;
    public HashSet<String> forSs;

    public Units(Map<String, HashSet<String>> alphabets, String[] terminalsCfg, String[] nonterminalsCfg, String [] newStates) {
        this.alphabets = alphabets;
        this.newStates = newStates;
        this.terminalsCfg = terminalsCfg;
        this.nonterminalsCfg = nonterminalsCfg;
        unitTmpHashSet = new HashSet<>();
        unitTmpMap = new HashMap<>();
        tmpMap = new HashMap<>();
        anotherSet = new HashSet<>();
        forKeysOfUnits = new HashSet<>();
        forSs = new HashSet();


        //find unit productions & add the to the unitTmpHashSet hashset
        for (String nonterminal : alphabets.keySet()) {
            for (String value : alphabets.get(nonterminal)) {
                if (value.length() == 1 && Character.isUpperCase(value.charAt(0))) {
                    unitTmpHashSet.add(value);
                    forKeysOfUnits.add(nonterminal);
                }
            }
        }
        //find lowercase alphabets of the Unit Keys (for e.g. if A is a unit Key we'll store in unitTmpMap = A and in another set = "d")
        for (String nonterminal : alphabets.keySet()) {
            for (String value : alphabets.get(nonterminal)) {
                if (createMapoFunitkeys(nonterminal, unitTmpHashSet) && value.length() == 1 && Character.isLowerCase(value.charAt(0))) {
                    anotherSet.add(value);
                    unitTmpMap.put(nonterminal, anotherSet);
                }
            }
        }


        //find the terminal that
        for (String nonterminal : alphabets.keySet()) {
            for (String value : alphabets.get(nonterminal)) {
                if (eqaulKeys(value, (HashMap<String, HashSet<String>>) unitTmpMap))
                    newUnitAlphabets((HashMap<String, HashSet<String>>) unitTmpMap, (HashMap<String, HashSet<String>>) tmpMap, nonterminal, value);

            }
        }

        for(String key:tmpMap.keySet()){
            HashSet<String> set= new HashSet<String>() {{
                addAll(alphabets.get(key));
                addAll(tmpMap.get(key)); } };
            alphabets.put(key,set);
        }

        //remove the unit nonterminals
        for( String value1 : forKeysOfUnits){
            for ( String value2 : unitTmpHashSet ){
                if(!value2.equals("S"))
            alphabets.get(value1).remove(value2);
            }
        }
    }
    //METHODS
    public boolean  createMapoFunitkeys(String nonterminal, HashSet<String> set){
        for (String s : set) {
            if (nonterminal.equals(s) && !nonterminal.equals("S")) {
                return true;
            }
        }
        return false;
    }

    public boolean eqaulKeys(String nonterminal, HashMap<String, HashSet<String>> map){
         for( String key : map.keySet()){
             if(nonterminal.equals(key))
                 return true;
         }
         return false;
    }

    public void newUnitAlphabets(HashMap<String, HashSet<String>> anAlphabet, HashMap<String, HashSet<String>> anotherMap, String nonterm, String expression){
        for (String value : anAlphabet.get(expression)){
                HashSet<String> aSet = new HashSet<>();
                aSet.add(value);
                anotherMap.put(nonterm, aSet);
        }
    }

}