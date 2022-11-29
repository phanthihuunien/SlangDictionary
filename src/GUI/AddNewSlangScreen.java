//package GUI;
//
//import Manage.DefinitionList;
//import Manage.Dictionary;
//
//import javax.swing.*;
//
///**
// * GUI
// * Created by Nien Phan
// * Date 11/27/2022 - 10:56 PM
// * Description: ...
// */
//public class AddNewSlangScreen extends JFrame {
//
//    public AddNewSlangScreen(Dictionary dictionary){
//        JTextField slangTf = new JTextField(5);
//        JTextField definitionTf = new JTextField(5);
//
//        this.add(new JLabel("Slang: "));
//        this.add(slangTf);
//        this.add(new JLabel("Definition"));
//        this.add(definitionTf);
//
//        int option = JOptionPane.showConfirmDialog(null, this,"Add new slang word", JOptionPane.OK_CANCEL_OPTION);
//        if (option == JOptionPane.OK_OPTION) {
//            String slang = slangTf.getText();
//            String definition = definitionTf.getText();
//            DefinitionList definitionList = dictionary.get(slang);
//            if(definitionList == null){
//                dictionary.addNewSlang(slang, definition);
//            }else{
//                int chosen = JOptionPane.showOptionDialog(null,"This slang word has existed. Please choose one option!",
//                        "Add new slang word",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Duplicate", "Overwrite"}, null);
//                if(chosen == 0)
//                    dictionary.addNewSlang(slang, definition);
//                else {
//                    DefinitionList newList = new DefinitionList(definition);
//                    dictionary.put(slang, newList);
//                }
//            }
//            dictionary.saveNewDefinitionToFile("UsedSLangFile");
//            new NotificationScreen(this, "Add new slang word", "Add successfully");
//
//        }
//
//    }
//}
