package main.java.y2017;

import main.java.common.AbstractService;
import main.java.common.CommonService;

public class Y2017D1Service extends AbstractService {

    @Override
    public String getFirstResult() throws Exception {

        int result = 0;
        final String data = getData();
        for (int i = 0; i < data.length(); i++) {
            if(i == data.length() - 1 && data.charAt(data.length() - 1) == data.charAt(0)) {
                result += Character.getNumericValue(data.charAt(0));
            } else if(i < data.length() - 1 && data.charAt(i) == data.charAt(i + 1)) {
                result += Character.getNumericValue(data.charAt(i));
            }
        }
        return String.valueOf(result);
    }

    @Override
    public String getSecondResult() throws Exception {

        int result = 0;
        final String data = getData();
        final String firstPart = data.substring(0, data.length()/2);
        final String secondPart = data.substring(data.length()/2);
        for (int i = 0; i < data.length()/2; i++) {
            if(firstPart.charAt(i) == secondPart.charAt(i)) {
                result += Character.getNumericValue(data.charAt(i)) * 2;
            }
        }
        return String.valueOf(result);
    }

    private String getData() throws Exception {
        return CommonService.getDataString(2017, 1);
    }
}
