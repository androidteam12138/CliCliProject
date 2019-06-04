package tool;

public class QueryColVed implements Runnable {
    public static String[] ss;
    private int vid;
    public QueryColVed(int vid){
        this.vid = vid;
    }
    @Override
    public void run() {
        ss = StringDeal.StringDeal(SendGet.SendGet("/QueryServlet/vedio01","vid="+vid));
    }
}
