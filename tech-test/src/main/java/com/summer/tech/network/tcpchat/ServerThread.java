package com.summer.tech.network.tcpchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    BufferedReader br = null;
    PrintStream ps = null;
    
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //һ���ͻ��˵����������
            ps = new PrintStream(socket.getOutputStream());
            String line = null;
            while((line = br.readLine()) != null) {
                //�����Ϣ��ChatProtocol.USER_ROND��ʼ�����������
                //�����ȷ�����������û���¼���û���
                if(line.startsWith(ChatProtocol.USER_ROND) &&
                        line.endsWith(ChatProtocol.USER_ROND)) {
                    String userName = getRealMsg(line);
                    //�û����������ظ�
                    if(Server.clients.map.containsKey(userName)) {
                        System.out.println("�û����ظ�");
                        ps.println(ChatProtocol.NAME_REP);
                    } else {
                        System.out.println("["+userName+"] ע��ɹ�������Կ�ʼ�����ˣ�");
                        ps.println(ChatProtocol.LOGIN_SUCCESS);
                        //���û����������������ɵļ�ֵ�����Դ���ǰ�澭�������map
                        Server.clients.map.put(userName, ps);
                    }
                } //�����Ϣ��ChatProtocol.PRIVATE_ROND��ͷ����ChatProtocol.PRIVATE_ROND��β
                //�����ȷ����˽����Ϣ
                else if (line.startsWith(ChatProtocol.PRIVATE_ROND ) && 
                        line.endsWith(ChatProtocol.PRIVATE_ROND)) {
                    String userAndMsg = getRealMsg(line);
                    
                    //��SPILT_SIGN�ָ��ַ�����ǰ�����û����������������Ϣ
                    String user = userAndMsg.split(ChatProtocol.SPLIT_SIGN)[0];
                    String msg = userAndMsg.split(ChatProtocol.SPLIT_SIGN)[1];
                    //�����û�����map���ҳ���������󣬽���˽����Ϣ����
                    Server.clients.map.get(user).println("[˽����Ϣ] [���� "+Server.clients.getKeyByValue(ps)+"] : " + msg);
                    
                }
                // Ⱥ����Ϣ���㲥��Ϣ
                else {
                    String msg = getRealMsg(line);
                    for(PrintStream clientPs :  Server.clients.valueSet()) {
                        clientPs.println("[Ⱥ����Ϣ] [���� "+Server.clients.getKeyByValue(ps)+"] : " + msg);
                    }
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
            Server.clients.removeByValue(ps);
            System.out.println(Server.clients.map.size());
            try {
                if (br != null) {
                    br.close();
                } 
                
                if (ps != null) {
                    ps.close();
                }
                
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String getRealMsg(String line) {
        return line.substring(ChatProtocol.PROTOCOL_LEN, line.length() - ChatProtocol.PROTOCOL_LEN);
    }

}