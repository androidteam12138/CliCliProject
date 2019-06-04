package tool;

import com.example.administrator.m123.Myhome;

public class QueryCol implements Runnable{
    public static String[] s;

    private int id;

    public QueryCol(int id){
        this.id = id;
    }

    @Override
    public void run() {
        String ss = SendGet.SendGet("/QueryServlet/collect","id="+id);
        if(!"0".equals(ss)) {
            s = StringDeal.StringDeal(ss);
            Myhome.statement=1;
        }else{
            Myhome.statement=0;
        }
    }
}
