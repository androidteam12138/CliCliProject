package tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class IsNetWork {
    /*
    此类是用来判断当前手机是否联网
     */
    public static boolean isNetWork(Context con){
        ConnectivityManager cm = (ConnectivityManager)con.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm == null){
            return false;
        }
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if(netinfo == null){
            return false;
        }
        if(netinfo.isConnected()){
            return true;
        }
        return false;
    }
    public static String checkNet(Context con){
        String s = "";
        if(!isNetWork(con)){
            s = "网络连接失败，请您检查网络!";
        }else{
            s = "网络连接成功!";
        }
        return s;
    }
}
