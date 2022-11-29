package Manage;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Manage
 * Created by Nien Phan
 * Date 11/21/2022 - 11:02 PM
 * Description: ...
 */
public class Dictionary extends HashMap<String, DefinitionList> {
    private static Dictionary currentVer = null;
    private Dictionary() {}
    public static Dictionary getCurrentVer() {
        if (currentVer==null) {
            currentVer = new Dictionary();
        }
        return currentVer;

    }
    public List<String> searchDefinition(String definitionSearched){
        List<String> slangList = new ArrayList<>();
        for(Map.Entry<String, DefinitionList> set:this.entrySet()){
            String[] definitionList = set.getValue().toArray(new String[0]);
            for(String definition: definitionList){
                if(definition.toLowerCase().contains(definitionSearched.toLowerCase())){
                    slangList.add(set.getKey());
                    break;
                }
            }
        }
        //System.out.println(slangList);
        return slangList;
    }
    public void addNewSlang(String slangWord, String definition){
        DefinitionList definitionList = this.get(slangWord);
        if(definitionList == null){
            DefinitionList newDefinitionList = new DefinitionList(definition);
            this.put(slangWord, newDefinitionList);
            return;
        }
        definitionList.add(definition);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, DefinitionList> item:this.entrySet()) {
            builder.append(item.getKey());
            builder.append("`");
            builder.append(item.getValue().toString());
            builder.append("\n");
        }
        return builder.toString();
    }
    public void saveNewDefinitionToFile(String fileName){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            for(Map.Entry<String, DefinitionList> set:this.entrySet()){
                bw.write(String.format("%s%s%s\n", set.getKey(),"`", set.getValue().toString()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteDefinition(String slangWord, int selectedItemIndex) {
        this.get(slangWord).remove(selectedItemIndex);
    }
    public void getDataFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line=br.readLine())!=null) {
            String[] part = line.split("`");
            if (part.length<2) {
                continue;
            }
            String slang = part[0];
            String[] definitionList = part[1].split(Pattern.quote("|"));
            DefinitionList definitions = new DefinitionList(definitionList);
            this.put(slang, definitions);
        }
        br.close();
    }
    public String randomASlangWord(){
        String[] slangList = this.keySet().toArray(new String[0]);
        return slangList[new Random().nextInt(slangList.length)];
    }
}
