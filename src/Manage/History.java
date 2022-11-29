package Manage;

import java.io.*;
import java.util.ArrayList;

/**
 * Manage
 * Created by Nien Phan
 * Date 11/21/2022 - 10:59 PM
 * Description: ...
 */
public class History{
    private static History currentVer = null;
    final private ArrayList<String> historyList;
    private History(){
        historyList = new ArrayList<>();
    }
    public void saveHistory() throws IOException {
        FileWriter wrFile = new FileWriter("history.txt");
        for (String historyWord :historyList) {
            wrFile.write(historyWord + "\n");
        }
        wrFile.close();
    }
    public void addWordToHistory(String SlangWord){
        historyList.add(SlangWord);
    }
    public  ArrayList<String> getHistoryList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("history.txt"));
        String word;
        historyList.clear();
        while ((word = br.readLine()) != null){
            historyList.add(word);
        }
        br.close();
        return historyList;
    }
    public static History getCurrentVer(){
        if(currentVer == null)
            currentVer = new History();
        return currentVer;
    }
}
