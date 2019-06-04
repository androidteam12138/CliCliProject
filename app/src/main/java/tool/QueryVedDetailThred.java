package tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tool.StringDeal.StringDeal;

public class QueryVedDetailThred implements Runnable {
    public static Map<Integer,List<String>> map = new HashMap<>();
    private List<Integer> list;
    public QueryVedDetailThred(List<Integer> list){
        this.list = list;
    }
    @Override
    public void run() {
        map.clear();
        for(int i = 0;i < list.size();i++){
            List<String> list1 = new ArrayList<>();
            String[] dateStr = StringDeal( SendGet.SendGet("/QueryServlet/vedio01","vid="+list.get(i)));
            for(int j = 0;j < dateStr.length;j++){
                list1.add(dateStr[j]);
            }
            map.put(i,list1);
        }
    }
}
