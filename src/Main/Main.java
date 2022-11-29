package Main;

import GUI.HomeMenuScreen;
import Manage.Dictionary;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary.getCurrentVer().getDataFromFile("UsedSLangFile.txt");
      new HomeMenuScreen();
    }
}


