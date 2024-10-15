package com.wythe.mall.Client;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.wythe.mall.activity.RegisterActivity;
import com.wythe.mall.activity.RegisterCompleteActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ClientThread{
    private static final String SERVER_IP="192.168.43.82";
    private static final int SERVER_PORT=6789;

    public static String username;
    public static String password;
    public static int operators;
    public static int point;


    public static int val_point;
    public static String statue;

    public boolean run() {
        Socket socket=null;
        PrintWriter out=null;
        BufferedReader in=null;
        boolean flag=false;
        try {
            InetAddress serverAddr=InetAddress.getByName(SERVER_IP);
            socket=new Socket(serverAddr,SERVER_PORT);
            socket.setSoTimeout(5000);
            out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            out.println(Integer.toString(operators)+":"+username+":"+password+":"+Integer.toString(point));

            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            String line=null;
            while ((line = in.readLine()) != null) {
                Log.e("CLIENT123", line);
                statue=line.substring(0,4);
                if(statue.equals(ClientVal.OK)){
                    String point=line.substring(4);
                    val_point=Integer.parseInt(point);
                    Log.e("CLIENT123", Integer.toString(val_point));
                }
                break;
            }
            flag=true;
        }catch (SocketTimeoutException e){
            Log.e("CLIENT123", "Socket timed out!");
        }
        catch (UnknownHostException e) {
            Log.e("CLIENT123", "Don't know about host: " + SERVER_IP, e);
        } catch (IOException e) {
            Log.e("CLIENT123", "Couldn't get I/O for the connection to: " + SERVER_IP, e);
        }finally {
            // 关闭资源
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

//    private void showMsg(Context context, String msg) {
//        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//    }
    public boolean check(){
        Log.e("CLIENT123", "check method entered"); // 添加这一行来确认方法入口

        Log.e("CLIENT123", statue+" "+ClientVal.USER_EXIT);
        if(statue.equals(ClientVal.WRONG_PASSWORD)){
            RegisterCompleteActivity.s="账号密码错误";
//            showMsg(context,"账号密码错误");
            return true;
        }else if(statue.equals(ClientVal.USER_EXIT)){
            RegisterCompleteActivity.s="用户存在";
//            showMsg(context,"用户不存在");
            return true;
        }else if(statue.equals(ClientVal.NO_POINt_ENOUGH)){
            RegisterCompleteActivity.s="积分不足";

//            showMsg(context,"积分不足");
            return true;
        }
        return false;
    }
}
