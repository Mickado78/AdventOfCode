package main.java.y2024;

import main.java.common.AbstractService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static main.java.common.CommonService.getDataList;

public class Y2024D5Service extends AbstractService {

    @Override
    public String getFirstResult() throws Exception {

        final List<List<String>> inputLists = getInputLists();
        final List<List<String>> pagesList = getSeparatedPagesLists(getUpdatesOKorKO(inputLists.get(1),
                inputLists.getFirst(), true));

        return String.valueOf(getSumOfMiddlePages(pagesList));
    }

    @Override
    public String getSecondResult() throws Exception {

        final List<List<String>> inputLists = getInputLists();
        final List<List<String>> pagesList = getSeparatedPagesLists(getUpdatesOKorKO(inputLists.get(1),
                inputLists.getFirst(), false));

        for(List<String> pages:pagesList) {
            reOrderPages(pages, inputLists.getFirst());
        }

        return String.valueOf(getSumOfMiddlePages(pagesList));
    }

    private List<List<String>> getInputLists() throws Exception {

        final List<List<String>> returnList = new ArrayList<>();
        final List<String> inputList = getDataList(2024, 5);
        returnList.add(inputList.subList(0, inputList.indexOf("")));
        returnList.add(inputList.subList(inputList.indexOf("") + 1, inputList.size()));

        return returnList;
    }

    private boolean pagesDontRespectRules (List<String> rules, String first, String second) {

        boolean result = false;

        for (String rule:rules) {
            if(rule.contains(first) && rule.contains(second) && !first.equals(rule.split("\\|")[0])) {
                result = true;
                break;
            }
        }

        return result;
    }

    private List<String> getUpdatesOKorKO(List<String> updates, List<String> rules, boolean ok) {

        final List<String> updatesOKorKO = new ArrayList<>();

        for(String row:updates) {
            boolean respectsRules = true;
            List<String> pages = Arrays.stream(row.split(",")).toList();
            for(int i = 0; i < pages.size(); i++) {
                for(int j = i; j < pages.size() - 1; j++) {
                    if(pagesDontRespectRules(rules, pages.get(i) ,pages.get(j + 1))){
                        respectsRules = false;
                        break;
                    }
                }
                if(!respectsRules) {
                    break;
                }
            }

            if(ok && respectsRules || !ok && !respectsRules) {
                updatesOKorKO.add(row);
            }
        }
        return updatesOKorKO;
    }

    private List<List<String>> getSeparatedPagesLists(List<String> updates) {
        return updates.stream().map(row -> Arrays.asList(row.split(","))).collect(Collectors.toList());
    }

    private int getSumOfMiddlePages(List<List<String>> pagesList) {
        return pagesList.stream().map(pages -> Integer.parseInt(pages.get(pages.size() / 2)))
                .reduce(Integer::sum).stream().findFirst().orElse(0);
    }

    private String getOriginalUpdateFormat(List<String> separatedPages) {

        StringBuilder sb = new StringBuilder();
        for(String page:separatedPages) {
            sb.append(page).append(",");
        }
        return sb.substring(0, sb.length());
    }

    private void reOrderPages(List<String> pages, List<String> rules) {

        boolean pagesDontRespectRules = false;

        for(int i = 0; i < pages.size(); i++) {
            for(int j = i; j < pages.size() - 1; j++) {
                if(pagesDontRespectRules(rules, pages.get(i) ,pages.get(j + 1))){
                    pagesDontRespectRules = true;
                    final String wrongFirst = pages.get(i);
                    final String wrongSecond = pages.get(j + 1);
                    pages.set(i, wrongSecond);
                    pages.set(j + 1, wrongFirst);
                    break;
                }
            }
            if(pagesDontRespectRules) {
                break;
            }
        }

        if(pagesDontRespectRules && getUpdatesOKorKO(Collections.singletonList(getOriginalUpdateFormat(pages)), rules,
                true).isEmpty()) {
            reOrderPages(pages, rules);
        }
    }
}
