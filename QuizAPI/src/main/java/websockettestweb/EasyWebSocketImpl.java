/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websockettestweb;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 *
 * @author pravien
 */
public class EasyWebSocketImpl implements IEasyWebsocket {

    private Session session;

    @Override
    public String handleMessage(String msg) {
        return "message received: " + msg;
    }

    @Override
    public void pushNotification(Set<Session> sessions) {
        int count = 1;
        while (true) {
            for (Session s : sessions) {
                if (s.isOpen()) {
                    try {
                        System.out.println("sending..to" + s.getId());
                        s.getBasicRemote().sendText("message: " + count);
                        count++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                System.out.println("before sleeep");
                Thread.sleep(3000);
                System.out.println("after sleeep");
            } catch (InterruptedException ex) {
                Logger.getLogger(EasyWebSocketImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
