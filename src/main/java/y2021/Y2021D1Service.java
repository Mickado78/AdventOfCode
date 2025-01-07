package main.java.y2021;

import main.java.common.AbstractService;
import main.java.common.CommonService;

import java.util.List;

public class Y2021D1Service extends AbstractService {

    @Override
    public int getFirstResult() throws Exception {

        int result = 0;
        final List<String> dataList = getDataList();
        for(int i = 0; i < dataList.size() - 1; i++) {
            if(Integer.parseInt(dataList.get(i + 1)) > Integer.parseInt(dataList.get(i))) {
                result++;
            }
        }

        return result;
    }

    @Override
    public int getSecondResult() throws Exception {

        int result = 0;
        final List<String> dataList = getDataList();
        for(int i = 0; i < dataList.size() - 3; i++) {
            if(Integer.parseInt(dataList.get(i + 1))
                    + Integer.parseInt(dataList.get(i + 2))
                    + Integer.parseInt(dataList.get(i + 3)) > Integer.parseInt(dataList.get(i + 2))
                                                            + Integer.parseInt(dataList.get(i + 1))
                                                            + Integer.parseInt(dataList.get(i))) {
                result++;
            }
        }

        return result;
    }

    private List<String> getDataList() throws Exception {
        return CommonService.getDataList(2021, 1);
    }
}
