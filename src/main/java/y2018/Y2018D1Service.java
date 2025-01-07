package main.java.y2018;

import main.java.common.AbstractService;
import main.java.common.CommonService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Y2018D1Service extends AbstractService {

    @Override
    public int getFirstResult() throws Exception {
        return getDataList().stream().map(Integer::parseInt).reduce(Integer::sum).orElse(0);
    }

    @Override
    public int getSecondResult() throws Exception {
        final List<String> dataList = getDataList();
        int frequency = 0;
        final Set<Integer> frequencySet = new HashSet<>();
        int i = 0;

        while(frequencySet.add(frequency)) {
            frequency += Integer.parseInt(dataList.get(i));
            i++;
            if(i == dataList.size()) {
                i = 0;
            }
        }

        return frequency;
    }

    private List<String> getDataList() throws Exception {
        return CommonService.getDataList(2018, 1);
    }
}
