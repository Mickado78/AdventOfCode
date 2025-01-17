package main.java.y2023;

import main.java.common.AbstractService;
import main.java.common.CommonService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Y2023D1Service extends AbstractService {

    @Override
    public String getFirstResult() throws Exception {

        int result = 0;
        final List<String> dataList = getDataList();

        for(String data:dataList) {
            Integer firstDigit = null;
            Integer lastDigit = null;
            String tmp = data.replaceAll("\\D", "");
            if(!tmp.isEmpty()) {
                for(int i = 0; i < tmp.length(); i++) {
                    if(firstDigit == null) {
                        firstDigit = Character.getNumericValue(tmp.charAt(i));
                    }
                    lastDigit = Character.getNumericValue(tmp.charAt(i));
                }
                result += firstDigit * 10 + lastDigit;
            }
        }

        return String.valueOf(result);
    }

    @Override
    public String getSecondResult() throws Exception {

        int result = 0;
        final List<String> dataList = getDataList();

        for(String data:dataList) {
            List<String> allMatches = new ArrayList<>();
            Integer firstDigit = null;
            Integer lastDigit = null;
            Matcher matcher = Pattern.compile("(?=(one|two|three|four|five|six|seven|eight|nine|\\d)).")
                    .matcher(data);
            while(matcher.find()) {
                allMatches.add(matcher.group(1));
            }
            if(!allMatches.isEmpty()) {
                firstDigit = convertInt(allMatches.getFirst());
                lastDigit = convertInt(allMatches.getLast());

                result += firstDigit * 10 + lastDigit;
            }
        }

        return String.valueOf(result);
    }

    private List<String> getDataList() throws Exception {
        return CommonService.getDataList(2023, 1);
    }

    private List<String> getStringDigits() {
        return List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    }

    private int convertInt(String number) {
        int result = 0;
        switch (number) {
            case  "one" :
                result = 1;
                break;
            case  "two" :
                result = 2;
                break;
            case  "three" :
                result = 3;
                break;
            case  "four" :
                result = 4;
                break;
            case  "five" :
                result = 5;
                break;
            case  "six" :
                result = 6;
                break;
            case  "seven" :
                result = 7;
                break;
            case  "eight" :
                result = 8;
                break;
            case  "nine" :
                result = 9;
                break;
            default:
                result = Integer.parseInt(number);
                break;
        }
        return result;
    }
}
