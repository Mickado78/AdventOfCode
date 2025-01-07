package main.java.y2024;

import main.java.common.AbstractService;

import java.util.*;
import java.util.regex.Pattern;

import static main.java.common.CommonService.getDataList;

public class Y2024D1Service extends AbstractService {

    @Override
    public int getFirstResult() throws Exception {

        int result = 0;
        final List<List<Integer>> twoLists = getTwoLists();

        for(int i = 0; i < twoLists.get(0).size();i++ ) {
            result += Math.abs(twoLists.get(0).get(i) - twoLists.get(1).get(i));
        }
        return result;
    }

    @Override
    public int getSecondResult() throws Exception {

        int result = 0;
        final List<List<Integer>> twoLists = getTwoLists();

        for (Integer number:twoLists.getFirst()) {
            result += number * Collections.frequency(twoLists.getLast(),number);
        }
        return result;
    }

    private List<List<Integer>> getTwoLists() throws Exception {

        final List<String> data = getDataList(2024, 1);
        final List<List<Integer>> twoLists = new ArrayList<>();

        twoLists.add(data.stream().map(e -> Integer.parseInt(Pattern.compile("(^\\d+)")
                .matcher(e).results().findFirst().get().group(1))).sorted().toList());
        twoLists.add(data.stream().map(e -> Integer.parseInt(Pattern.compile("(\\d+$)")
                .matcher(e).results().findFirst().get().group(1))).sorted().toList());

        return twoLists;
    }
}
