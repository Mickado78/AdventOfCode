package main.java.y2015;

import main.java.common.AbstractService;
import main.java.common.CommonService;

import java.util.Arrays;
import java.util.List;

public class Y2015D2Service extends AbstractService {

    @Override
    public String getFirstResult() throws Exception {

        int result = 0;
        List<String> dataList = getDataList();
        for(String data:dataList) {
            String[] values = data.split("x");
            int l = Integer.parseInt(values[0]);
            int w = Integer.parseInt(values[1]);
            int h = Integer.parseInt(values[2]);
            int min = Arrays.stream(new int[] {l * w, l * h, w * h}).min().getAsInt();
            result += 2*l*w + 2*w*h + 2*h*l + min;
        }

        return String.valueOf(result);
    }

    @Override
    public String getSecondResult() throws Exception {

        int result = 0;
        List<String> dataList = getDataList();
        for(String data:dataList) {
            String[] values = data.split("x");
            int l = Integer.parseInt(values[0]);
            int w = Integer.parseInt(values[1]);
            int h = Integer.parseInt(values[2]);
            int min = Arrays.stream(new int[] {2 * (l + w), 2 * (l + h), 2 * (w + h)}).min().getAsInt();
            result += h*l*w + min;
        }

        return String.valueOf(result);
    }

    private List<String> getDataList() throws Exception {
        return CommonService.getDataList(2015, 2);
    }
}
