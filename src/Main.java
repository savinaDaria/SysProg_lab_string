import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main
{
    public static void main(String[] args) throws Exception
    {
           String pathname ="C:\\Users\\Professional\\Desktop\\Война и мир.txt";
           String str=get_file_strings(pathname);
            str=get_clear_strings(str);
            Map<String, Integer> map=get_words_repeats(str);
            map=get_max_of_repeats(map);
            PrintWords(map);
    }


    public static String get_file_strings(String pathname) throws Exception {
        File f = new File(pathname);
        final int length = (int) f.length();
        if (length != 0) {
            char[] words = new char[length];
            InputStreamReader stream = new InputStreamReader(new FileInputStream(f), "CP1251");
            final int read = stream.read(words);
            String str = new String(words, 0, read);
           // stream.close();
            return str;
        }
        else{
            throw new Exception("Файл пустий або його не існує");
         }
    }

    public static String get_clear_strings( String str){
        str = str.trim();
        str = str.toLowerCase();
        str = str.replaceAll("[^A-Za-zА-ЯЇЄІа-яїєіыъ']+", " ");
        return str;
    }
    public static Map<String,Integer> get_words_repeats(String str){
        Map<String, Integer> map = new HashMap<>();
        for (String s : str.split(" ")) {
            if(s.length()>=30){
                s=s.substring(0,30);
            }

            //if(s.length()<2){continue;}

            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            }
            else {
                map.put(s, 1);
            }
        }
        return  map;
    }

    public static Map<String,Integer> get_max_of_repeats(Map<String,Integer> map){
        Map<String, Integer> final_map = new HashMap<>();
        Integer max_count=0;
        for (String key : map.keySet()) {
            if(map.get(key)>max_count)
            {
                final_map.clear();
                final_map.put(key,map.get(key));
                max_count=map.get(key);
            }
            if(map.get(key).equals(max_count)){
                final_map.put(key,map.get(key));
            }
        }
        return final_map;
    }

    public static void PrintWords(Map<String,Integer> map){
        System.out.println("Мапа: {слово=кількість повторень}\n"+ map);
        Map<String, String> treeMap = new TreeMap(map);
        for (String words_alphabetic : treeMap.keySet())
        {
            System.out.println(words_alphabetic);
        }
    }
}