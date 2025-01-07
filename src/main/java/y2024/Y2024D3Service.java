package main.java.y2024;

import main.java.common.AbstractService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static main.java.common.CommonService.getDataString;

public class Y2024D3Service extends AbstractService {

    @Override
    public int getFirstResult() throws Exception {
        return calculateMulSum(getData());
    }

    @Override
    public int getSecondResult() throws Exception {
        return calculateMulSum(getData()
                .replaceAll("don't\\(\\).*?do\\(\\)|don't\\(\\).*(?!.*do\\(\\))",""));
    }

    public int getResultUsingStream() throws Exception {

        final Function<MatchResult, Integer> multiply = mr -> IntStream.range(1, mr.groupCount() + 1)
                .mapToObj(mr::group).map(Integer::parseInt).reduce(1, Math::multiplyExact);

        return Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)", Pattern.MULTILINE).matcher(getData()).results()
                .map(multiply).reduce(0, Integer::sum);
    }

    private String getData() throws Exception {
        return getDataString(2024, 3);
    }

    private int calculateMulSum(String data) {

        Matcher matcher = Pattern.compile("mul\\(\\d+,\\d+\\)").matcher(data);
        List<String[]> values = new ArrayList<>();

        while(matcher.find()) {
            values.add(matcher.group().replaceAll("mul\\(|\\)","").split(","));
        }

        return values.stream().map(v -> Integer.parseInt(v[0])*Integer.parseInt(v[1])).reduce(Integer::sum)
                .orElse(0);
    }


}
