package main.java.y2018;

import main.java.common.AbstractService;
import main.java.common.CommonService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Y2018D2Service extends AbstractService {
    @Override
    public String getFirstResult() throws Exception {

        int doubleLetters = 0;
        int tripleLetters = 0;

        final List<String> dataList = getDataList();
        for(String data:dataList) {
            Map<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < data.length(); i++){
                char c = data.charAt(i);
                map.merge(c, 1, Integer::sum);
            }
            if(map.containsValue(2)) {
                doubleLetters++;
            }
            if(map.containsValue(3)) {
                tripleLetters++;
            }
        }

        return String.valueOf(doubleLetters * tripleLetters);
    }

    @Override
    public String getSecondResult() throws Exception {

        String result = "";
        final List<String> dataList = getDataList();
        boolean found = false;
        String first = "";
        String second = "";

        for(int i = 0; i < dataList.size() - 1; i++) {
            for(int j =  i + 1; j < dataList.size(); j++) {
                int diff = 0;
                Integer index = null;
                for(int k = 0; k < dataList.get(i).length(); k++){
                    if(dataList.get(i).charAt(k) != dataList.get(j).charAt(k)) {
                        diff++;
                    }
                    if(diff == 1 && index == null) {
                        index = k;
                    }
                    if(diff > 1) {
                        break;
                    }
                }
                if(diff == 1) {
                    found = true;
                    result = new StringBuilder(dataList.get(i)).deleteCharAt(index).toString();
                    break;
                }
            }
            if(found) {
                break;
            }
        }
        return result;
    }

    private List<String> getDataList() throws Exception {
        return CommonService.getDataList(2018, 2);
    }
}
