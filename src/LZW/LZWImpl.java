package LZW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LZWImpl {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        String compressWord = null;
        Map<String, Integer> compressionMap = new HashMap<>();
        Integer mapIndex = 1;
        StringBuilder sb = new StringBuilder();

        System.out.println("Vnesi karakteri koi sto sakas da se kompresiraat");
        compressWord = br.readLine();
        System.out.println("Vnesi recnik za kraj na recnikot vnesi '?' sekoj karakter vo nov red");

        while(!(line=br.readLine()).equals("?")){
            compressionMap.put(line, mapIndex++);
        }
        int j = 1;
        for(int i=0; i<compressWord.length(); i++){
            if(compressionMap.containsKey(compressWord.substring(i,j))){
                String wordToAdd = compressWord.substring(i,j);
                if(j+1 > compressWord.length()){
                    j = compressWord.length() - 1;
                }
                while(true){

                    if(compressionMap.containsKey(compressWord.substring(i,j+1))){
                        wordToAdd = compressWord.substring(i,j+1);
                        if(j+1>=compressWord.length()){
                            break;
                        }else{
                            j++;
                        }
                    }else{
                        break;
                    }
                }

                compressionMap.putIfAbsent(compressWord.substring(i, j+1), mapIndex++);
                sb.append(compressionMap.get(wordToAdd) +" ");
                if(j-i>1){
                    i = j-1;
                }else{

                }
                if(j==compressWord.length()-1){
                    j = compressWord.length();
                }else {
                    j++;
                }
            }else{
                break;
            }
        }

        sb.delete(sb.length()-2, sb.length() - 1);
        System.out.println(sb.toString());
    }
}
