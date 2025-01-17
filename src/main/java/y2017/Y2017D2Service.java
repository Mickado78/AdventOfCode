package main.java.y2017;

import main.java.Main;
import main.java.common.AbstractService;
import main.java.common.CommonService;

import java.util.Arrays;
import java.util.List;

public class Y2017D2Service extends AbstractService {
    @Override
    public String getFirstResult() throws Exception {

        int result = 0;

        List<String> dataList = getDataList();
        for(String data:dataList) {
            String[] values = data.split("\t");
            int min = Arrays.stream(values).mapToInt(Integer::parseInt).min().getAsInt();
            int max = Arrays.stream(values).mapToInt(Integer::parseInt).max().getAsInt();
            result += max - min;
        }

        return String.valueOf(result);
    }

    @Override
    public String getSecondResult() throws Exception {
        int result = 0;

        List<String> dataList = getDataList();
        for(String data:dataList) {
            List<Integer> values = Arrays.stream(data.split("\t")).map(Integer::parseInt).toList();
            boolean found = false;
            for(int i = 0; i < values.size(); i++) {
                for(int j = i + 1; j < values.size(); j++){
                    int min = Math.min(values.get(i), values.get(j));
                    int max = Math.max(values.get(i), values.get(j));
                    if (max % min == 0) {
                        result += max / min;
                        found = true;
                        break;
                    }
                }
                if(found) {
                    break;
                }
            }
        }

        return String.valueOf(result);
    }

    private List<String> getDataList() throws Exception {
        return CommonService.getDataList(2017, 2);
    }
}
