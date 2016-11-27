package com.example.zz.email3;

 import android.util.Base64;
 import android.util.Log;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.OutputStream;
 import java.net.InetSocketAddress;
 import java.net.Socket;

public class Email {
    static OutputStream ou=null;
    static BufferedReader bff=null;
    static private final String NAME_BASE64="MTMxMDEyMjc3NjhAMTYzLmNvbQ==";//用户名 base64 编码 ,注意是@前面的部分的base64编码
    static  Socket socket=new Socket();
    static String line=null;
    static String s=null;

    static String login(String user,String password)
    {
        try
        {
            socket.connect(new InetSocketAddress("smtp.163.com", 25), 3000);//连接服务器，这里用的是新浪邮箱
            bff = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ou = socket.getOutputStream();
            line=bff.readLine();
            System.out.println("CONNECT:");
            System.out.println(line);

            ou.write("HELO smtp.163.com\r\n".getBytes("UTF-8"));//发送问候消息
            line=bff.readLine();
            System.out.println("HELO smtp.163.com");
            System.out.println(line);

            ou.write("AUTH LOGIN\r\n".getBytes("UTF-8"));//发送登陆命令
            line=bff.readLine();
            System.out.println("auth login");
            System.out.println(line);

            byte[] buffer = user.getBytes();
            byte[] encode = Base64.encode(buffer, Base64.DEFAULT);
            String yonghuming = new String(encode);
            System.out.println("yonghuming: "+yonghuming);
            String tt=yonghuming+"\n";
            ou.write((NAME_BASE64+"\r\n").getBytes("UTF-8"));//发送用户名的base64编码
            line=bff.readLine();
            System.out.println(line);

            byte[] buffer2 = password.getBytes();
            byte[] encode2 = Base64.encode(buffer2, Base64.DEFAULT);
            String mima = new String(encode2);
            System.out.println("mima:"+mima);
            ou.write((mima+"\r\n").getBytes("UTF-8"));//发送密码的base64编码
            line=bff.readLine();
            System.out.println(line);
            s=line.substring(0,3);
            return s;

        }
        catch (Exception e)
        {
            System.out.println("Error. " + e);
            return null;
        }

    }

    static void sendMail(String from,String to,String subject,String content)
    {
        try
        {
            String t="MAIL FROM: <"+from+">";
            System.out.println(t);

            ou.write((t+"\r\n").getBytes("UTF-8"));//发送密码的base64编码
            line=bff.readLine();
            System.out.println(line);

            String t2="RCPT TO: <"+to+">";
            System.out.println(t2);
            ou.write((t2+"\r\n").getBytes("UTF-8"));
            line=bff.readLine();
            System.out.println("TO:"+line);

            ou.write("DATA\r\n".getBytes("UTF-8"));//发送数据命令
            line=bff.readLine();
            System.out.println("DATA:"+line);

            ou.write(("From:"+from+"\r\n"//发件人，要和前面的一致
                    + "To:"+to+"\r\n" //收件人，要和前面的一致
                    + "Subject:"+subject+"\r\n\r\n").getBytes("UTF-8"));//邮件主题

            ou.write(("\r\t"+content).getBytes("UTF-8"));//邮件正文内容

            ou.write("\r\n.\r\n".getBytes("UTF-8"));//结束标志
            line=bff.readLine();
            System.out.println("END:"+line);
            //String ss=line.substring(0,3);

            ou.write("QUIT\r\n".getBytes("UTF-8"));//退出登录
            line=bff.readLine();
            System.out.println("QUIT:"+line);
            ou.close(); // 关闭Socket输出流
            bff.close(); // 关闭Socket输入流
            socket.close(); // 关闭Socket

        } catch (Exception e){
            System.out.println("Error. " + e);
        }
    }
}
