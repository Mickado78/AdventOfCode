package main.java.y2015;

import main.java.common.AbstractService;
import main.java.common.CommonService;

public class Y2015D1Service extends AbstractService {

    @Override
    public String getFirstResult() throws Exception {
        final String data = getData();
        return String.valueOf(data.length() - data.replace("(", "").length()
                - (data.length() - data.replace(")", "").length()));
    }

    @Override
    public String getSecondResult() throws Exception {
        final String data = getData();
        int floor = 0;
        int position = 0;
        for(int i = 0; i < data.length(); i++) {
            if(data.charAt(i) == '(') {
                floor++;
            } else {
                floor--;
            }
            if(floor == -1) {
                position = i + 1;
                break;
            }
        }
        return String.valueOf(position);
    }

    private String getData() throws Exception {
        return CommonService.getDataString(2015, 1);
    }
}
