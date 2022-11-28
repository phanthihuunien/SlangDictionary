package Manage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manage
 * Created by Nien Phan
 * Date 11/21/2022 - 11:03 PM
 * Description: ...
 */
public class DefinitionList extends ArrayList<String> {
    public DefinitionList(String definition) {
        this(new String[]{definition});
    }

    public DefinitionList(String[] definitionList) {
        super();
        this.addAll(Arrays.asList(definitionList));
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< this.size(); i++){
            sb.append(this.get(i));
            if(i < this.size() - 1){
                sb.append("|");
            }
        }
        return sb.toString();
    }
}
