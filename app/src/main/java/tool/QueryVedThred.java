package tool;

import java.util.ArrayList;
import java.util.List;

import static tool.StringDeal.StringDeal;

public class QueryVedThred implements Runnable {
    public static List<Integer> list = new ArrayList<>();
    public static int result;
    private String vname;
    private String vtype;
    private String vclass;
    private String varea;
    public QueryVedThred(String vname,String vtype,String vclass,String varea){
        this.vname = vname;
        this.varea = varea;
        this.vclass = vclass;
        this.vtype  = vtype;
    }
    @Override
    public void run() {
        try {
            list.clear();
            String s = SendGet.SendGet("/QueryServlet/vedio02", "vname="+vname+"&vtype="+vtype+"&vclass="+vclass+"&vtime=0&varea="+varea+"&vip=&vrecom=");
            if(!"0".equals(s)) {
                result = 1;
                String[] ss = StringDeal(s.toString().trim());
                for (int i = 0; i < ss.length; i++) {
                    list.add(Integer.parseInt(ss[i].toString().trim()));
                }
            }else{
                result = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
