package Arithmetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Arithmetic {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String wordToCode = null;
        Map<String, Double> probabilityMap = new LinkedHashMap<>();
        Map<String, List<Double>> mapWithRange = new LinkedHashMap<>();
        String line = null;

        System.out.println("Vnesi zbor koj treba da se zakodira");
        wordToCode = br.readLine();
        System.out.println("Vnesi verojatnosti od najgolem prema najmal vo stil M 0.5 za kraj vnesi '?' i site karakteri so novi verojatnosti vnesi gi vo nov red");


        while(!(line=br.readLine()).equals("?")){
            String [] splitted = line.split(" ");
            probabilityMap.put(splitted[0], Double.parseDouble(splitted[1]));
        }

        if(probabilityMap.values().stream().mapToDouble(Double::shortValue).sum() >= 0.99){
            System.out.println("verojatnostite ne se ednakni na 1");
            return;
        }

        Double prevValue = 0d;

       for(Map.Entry<String, Double> set : probabilityMap.entrySet()){
           mapWithRange.put(set.getKey(), List.of(prevValue, set.getValue()+prevValue));
           prevValue = set.getValue() + prevValue;
       }

       for(int i=0;i<wordToCode.length() - 1; i++){
           List<Double> range = mapWithRange.get(String.valueOf(wordToCode.charAt(i)));
           Double diff = (range.get(1) - range.get(0)) / 10;
           Double firstValue = range.get(0);
           for(Map.Entry<String, Double> set : probabilityMap.entrySet()){
                mapWithRange.put(set.getKey(), List.of(firstValue, firstValue+(diff * probabilityMap.get(String.valueOf(set.getKey()))*10)));
                firstValue = firstValue + (diff * probabilityMap.get(String.valueOf(set.getKey()))*10);
           }
       }
        System.out.println(mapWithRange.get(String.valueOf(wordToCode.charAt(wordToCode.length()-1))));
    }
}
