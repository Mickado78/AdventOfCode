package main.java.y2024;

import main.java.common.AbstractService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.java.common.CommonService.getDataList;

public class Y2024D2Service extends AbstractService {

    @Override
    public String getFirstResult() throws Exception {

        List<String> data = getData();
        return String.valueOf(data.size() - getUnsafeReports(data).size());
    }

    @Override
    public String getSecondResult() throws Exception {

        List<String> data = getData();
        List<Integer> unSafeReportIndexes = getUnsafeReports(data);
        int singleBadLevel = 0;

        for (int i = 0; i < data.size(); i++) {

            if(unSafeReportIndexes.contains(i)) {

                String report = data.get(i);
                final List<String> numbersList = Arrays.stream(report.split(" ")).toList();

                for (int j = 0; j < numbersList.size(); j++) {

                    final List<String> reducedList = new ArrayList<>(numbersList);
                    reducedList.remove(j);

                    List<String> line = new ArrayList<>();
                    line.add(String.join(" ", reducedList.toArray(new String[0])));

                    if(getUnsafeReports(line).isEmpty()){
                        singleBadLevel++;
                        break;
                    }
                }
            }
        }
        return String.valueOf(data.size() - unSafeReportIndexes.size() + singleBadLevel);
    }

    private List<String> getData() throws Exception {

        return getDataList(2024, 2);
    }

    private List<Integer> getUnsafeReports (List<String> data) {

        List<Integer> unSafeReportIndexes = new ArrayList<>();

        for (String report:data){

            final String[] numbers = report.split(" ");

            Integer previousNumber = null;
            Boolean previousUp = null;
            Boolean previousDown = null;

            for (int i = 0; i < numbers.length; i++){

                int actualNumber = Integer.parseInt(numbers[i]);

                if (i > 0) {
                    int diff = actualNumber - previousNumber;
                    int absDiff = Math.abs(diff);

                    boolean up = diff > 0;
                    boolean down = diff < 0;

                    if ((i == 1 && (absDiff < 1 || absDiff > 3))
                            || (i > 1 && (previousUp != up || previousDown != down || absDiff < 1 || absDiff > 3))) {
                        unSafeReportIndexes.add(data.indexOf(report));
                        break;
                    } else {
                        previousUp = up;
                        previousDown = down;
                    }
                }
                previousNumber = actualNumber;
            }
        }
        return unSafeReportIndexes;
    }
}
