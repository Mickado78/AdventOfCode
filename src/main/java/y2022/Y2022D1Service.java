package main.java.y2022;

import main.java.common.AbstractService;
import main.java.common.CommonService;

import java.util.ArrayList;
import java.util.List;

public class Y2022D1Service extends AbstractService {

    @Override
    public String getFirstResult() throws Exception {

        int result = 0;
        final List<Integer> caloriesList = getCaloriesList(getDataList());

        for(Integer calories:caloriesList) {
            if (calories > result) {
                result = calories;
            }
        }
        return String.valueOf(result);
    }

    @Override
    public String getSecondResult() throws Exception {

        int result = 0;
        final List<Integer> caloriesList = getCaloriesList(getDataList());

        for(int i = 0; i < 3; i++) {
            Integer resultTmp = 0;
            for(Integer calories:caloriesList) {
                if (calories > resultTmp) {
                    resultTmp = calories;
                }
            }
            caloriesList.remove(resultTmp);
            result += resultTmp;
        }

        return String.valueOf(result);
    }

    private List<String> getDataList() throws Exception {
        return CommonService.getDataList(2022, 1);
    }

    private List<Integer> getCaloriesList(List<String> dataList) {

        final List<Integer> caloriesList = new ArrayList<>();

        for(String data:dataList) {
            if(caloriesList.isEmpty()) {
                caloriesList.add(Integer.parseInt(data));
            } else if(data.isEmpty()) {
                caloriesList.add(0);
            } else {
                caloriesList.set(caloriesList.size() - 1, caloriesList.getLast() + Integer.parseInt(data));
            }
        }
        return caloriesList;
    }

}
