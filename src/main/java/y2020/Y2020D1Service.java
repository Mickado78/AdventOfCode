package main.java.y2020;

import main.java.common.AbstractService;
import main.java.common.CommonService;

import java.util.List;

public class Y2020D1Service extends AbstractService {

    @Override
    public int getFirstResult() throws Exception {

        int result = 0;
        List<String> dataList = getDataList();
        boolean found = false;
        for(int i = 0; i < dataList.size(); i++) {
            for(int j = i + 1; j < dataList.size(); j++) {
                if(Integer.parseInt(dataList.get(i)) + Integer.parseInt(dataList.get(j)) == 2020) {
                    result = Integer.parseInt(dataList.get(i)) * Integer.parseInt(dataList.get(j));
                    found = true;
                    break;
                }
            }
            if(found) {
                break;
            }
        }
        return result;
    }

    @Override
    public int getSecondResult() throws Exception {

        int result = 0;
        List<String> dataList = getDataList();
        boolean found = false;
        for(int i = 0; i < dataList.size(); i++) {
            for(int j = i + 1; j < dataList.size(); j++) {
                for(int k = j + 1; k < dataList.size(); k++) {
                    if(Integer.parseInt(dataList.get(i))
                            + Integer.parseInt(dataList.get(j))
                            + Integer.parseInt(dataList.get(k)) == 2020) {
                        result = Integer.parseInt(dataList.get(i))
                                * Integer.parseInt(dataList.get(j))
                                * Integer.parseInt(dataList.get(k));
                        found = true;
                        break;
                    }
                }
                if(found) {
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
        return CommonService.getDataList(2020, 1);
    }
}
