package page5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public static void main(String[] args) throws UnknownHostException {
        Socket socket = null;
        ServerSocket serverSocket=null;
        try {
            serverSocket= new ServerSocket(8081);//创建绑定端口8000的对象ServerSocket
            System.out.println("服务器已经启动");
            while(true){
                System.out.println("--------");
                socket = serverSocket.accept();//接收客户端请求，得到Socket
                System.out.println("有客户端连接过来了");
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//创建带缓冲的字符读入流
                String sr = br.readLine();//读取一个文本行
                System.out.println(sr);
                PrintStream ps = new  PrintStream(socket.getOutputStream());//此输出流不会抛出异常
                ps.println(new StringBuffer(sr).reverse());//将反转后的字符串打印到客户端屏幕上
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(socket != null)
                    socket.close();
                if(serverSocket != null)
                    serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}