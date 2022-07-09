package QueryProcessor;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;

public class connection {
    public static Boolean listFolderStructure(String command) throws Exception {
        String username="ubuntu";
        String password = "root";
        String host="34.228.70.96";
        int port = 22;
        Session session = null;
        ChannelExec channel = null;
        System.out.println(command);
        try {
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            String responseString = new String(responseStream.toByteArray());
            if(!responseString.isEmpty()){
                System.out.println("data recieved");
                System.out.println(responseString);
                return true;
            }
            else {
                System.out.println("No file");
                return false;
            }
        }catch (Exception e){
            System.out.println("No Output");
            return false;
        }
        finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
    public static void making_files(String command) throws Exception {
        String username="ubuntu";
        String password = "root";
        String host="3.85.239.161";
        int port = 22;
        Session session = null;
        ChannelExec channel = null;
        System.out.println(command);
        try {
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            String responseString = new String(responseStream.toByteArray());
            System.out.println(responseString);
        }catch (Exception e){
            System.out.println("No Output");
        }
        finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
    public static void main(String args[]) throws Exception {
        listFolderStructure("cat /home/ubuntu/project/testing/abc.txt");
    }
}
