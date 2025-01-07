package main.java.y2019;

import main.java.common.AbstractService;
import main.java.common.CommonService;

import java.util.List;
import java.util.function.Function;

public class Y2019D1Service extends AbstractService {

    @Override
    public int getFirstResult() throws Exception {
        return getDataList().stream().map(s -> Integer.parseInt(s) / 3 - 2).reduce(Integer::sum).orElse(0);
    }

    @Override
    public int getSecondResult() throws Exception {

        final Function<String, Integer> calculate = s -> {
            int fuel = calculateFuel(Integer.parseInt(s));
            int result = fuel;
            while(calculateFuel(fuel) > 0) {
                fuel = calculateFuel(fuel);
                result += fuel;
            }
            return result;
        };

        return getDataList().stream().map(calculate).reduce(Integer::sum).orElse(0);
    }

    private List<String> getDataList() throws Exception {
        return CommonService.getDataList(2019, 1);
    }

    private int calculateFuel(int mass) {
        return mass / 3 - 2;
    }

}
