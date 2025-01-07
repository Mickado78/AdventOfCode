package main.java.y2023;

import main.java.common.AbstractService;
import main.java.common.CommonService;

import java.util.List;

public class Y2023D1Service extends AbstractService {

    @Override
    public int getFirstResult() throws Exception {

        int result = 0;
        final List<String> dataList = getDataList();

        for(String data:dataList) {
            Integer firstDigit = null;
            Integer lastDigit = null;
            String tmp = data.replaceAll("\\D", "");
            if(tmp.length() > 1) {
                for(int i = 0; i < tmp.length(); i++) {
                    if(firstDigit == null) {
                        firstDigit = Character.getNumericValue(tmp.charAt(i));
                    } else {
                        lastDigit = Character.getNumericValue(tmp.charAt(i));
                    }
                }
                result += firstDigit * 10 + lastDigit;
            }
        }

        return result;
    }

    @Override
    public int getSecondResult() throws Exception {
        return 0;
    }

    private List<String> getDataList() throws Exception {
        return CommonService.getDataList(2023, 1);
    }
}
