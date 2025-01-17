package main.java.y2024;

import main.java.common.AbstractService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.java.common.CommonService.getDataList;

public class Y2024D4Service extends AbstractService {

    public Y2024D4Service() throws Exception {
    }

    @Override
    public String getFirstResult() throws Exception {

        int result = 0;

        List<String> inputList = getInputList();
        result += getMatchNumber(inputList);
        result += getMatchNumber(getColumnList(inputList));
        result += getMatchNumber(getDiagonaleList(inputList));

        return String.valueOf(result);
    }

    @Override
    public String getSecondResult() throws Exception {

        return String.valueOf(getSecondMatchNumber(getDiagonaleList(getInputList())));
    }

    private List<String> getInputList() throws Exception {
        return getDataList(2024, 4);
    }

    private int getMatchNumber(List<String> list) {

        int result = 0;
        Pattern pattern = Pattern.compile("(XMAS)");
        Pattern reversedPattern = Pattern.compile("(SAMX)");
        for (String row:list) {
            Matcher matcher = pattern.matcher(row);
            Matcher reversedMatcher = reversedPattern.matcher(row);
            while(matcher.find()) {
                result++;
            }
            while(reversedMatcher.find()){
                result++;
            }
        }
        return result;
    }

    private List<String> getColumnList(List<String> list) {

        List<StringBuilder> buildList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.getFirst().length(); j++) {
                if(i == 0 && buildList.size() == j) {
                    buildList.add(new StringBuilder());
                }
                buildList.get(j).append(list.get(i).charAt(j));
            }
        }
        return buildList.stream().map(StringBuilder::toString).toList();
    }

    private List<String> getDiagonaleList(List<String> list){

        List<StringBuilder> buildList = new ArrayList<>();

        //Triangle nord est
        for(int i = 0; i < list.getFirst().length(); i++) {
            for(int j = 0; j < list.size() && i + j < list.getFirst().length(); j++) {
                if(j == 0) {
                    buildList.add(new StringBuilder());
                }
                buildList.get(i).append(list.get(j).charAt(i + j));
            }
        }

        int builderSize = buildList.size();

        //Triangle sud ouest
        for(int i = 1; i < list.size(); i++) {
            for(int j = 0; j < list.getFirst().length() && j + i < list.size(); j++) {
                if(j == 0) {
                    buildList.add(new StringBuilder());
                }
                buildList.get(builderSize - 1 + i).append(list.get(j + i).charAt(j));
            }
        }

        builderSize = buildList.size();

        //Triangle nord ouest
        for(int i = list.getFirst().length() - 1; i > -1; i--) {
            for(int j = 0; j < list.size() && i - j > -1; j++) {
                if(j == 0) {
                    buildList.add(new StringBuilder());
                }
                buildList.get(builderSize - 1 + list.getFirst().length() - i)
                        .append(list.get(j).charAt(i - j));
            }
        }

        builderSize = buildList.size();

        //Triangle sud est
        for(int i = 1; i < list.size(); i++) {
            for(int j = list.getFirst().length() - 1; j > -1
                    && list.getFirst().length() - 1 - j + i < list.size(); j--) {
                if(j == list.getFirst().length() - 1) {
                    buildList.add(new StringBuilder());
                }
                buildList.get(builderSize - 1 + i)
                        .append(list.get(list.getFirst().length() - 1 - j + i).charAt(j));
            }
        }

        return buildList.stream().map(StringBuilder::toString).toList();
    }

    private int getSecondMatchNumber(List<String> list) {

        int result = 0;

        List<int[]> masOrSamLocationsRight = getMasOrSam(list.subList(0, (list.size()/2)));
        List<int[]> masOrSamLocationsLeft = getMasOrSam(list.subList((list.size()/2), list.size()));
        List<int[]> aLocationsRight = new ArrayList<>();
        List<int[]> aLocationsLeft = new ArrayList<>();

        for(int[] location:masOrSamLocationsRight) {
            if(location[0] < list.getFirst().length()) {
                aLocationsRight.add(new int[] {location[1] + 1, location[0] + location[1] + 1});
            } else {
                aLocationsRight.add(new int[] {location[0] - list.getFirst().length() + location[1] + 2,
                        location[1] + 1});
            }
        }

        for(int[] location:masOrSamLocationsLeft) {
            if(location[0] < list.getFirst().length()) {
                aLocationsLeft.add(new int[] {location[1] + 1,
                        list.getFirst().length() - location[0] - location[1] - 2});
            } else {
                aLocationsLeft.add(new int[] {2 + location[0] - list.getFirst().length() + location[1],
                        list.getFirst().length() - 2 - location[1]});
            }
        }

        for(int[] aLocationRight:aLocationsRight) {
            for(int[] aLocationLeft:aLocationsLeft) {
                if(aLocationLeft[0] == aLocationRight[0] && aLocationLeft[1] == aLocationRight[1]) {
                    result++;
                }
            }
        }

        return result;
    }

    private List<int[]> getMasOrSam(List<String> list) {

        List<int[]> masOrSamLocations = new ArrayList<>();
        Pattern pattern = Pattern.compile("(MAS)");
        Pattern reversedPattern = Pattern.compile("(SAM)");

        for (int i = 0; i < list.size(); i++) {

            Matcher matcher = pattern.matcher(list.get(i));
            Matcher reversedMatcher = reversedPattern.matcher(list.get(i));
            while(matcher.find()) {
                masOrSamLocations.add(new int[]{i, matcher.start()});

            }
            while(reversedMatcher.find()){
                masOrSamLocations.add(new int[]{i, reversedMatcher.start()});
            }
        }
        return masOrSamLocations;
    }
}
