/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websockettestweb;

import java.io.IOException;
import javax.websocket.Session;
import org.clapper.util.classutil.ClassFinder;
import org.clapper.util.classutil.ClassInfo;

/**
 *
 * @author pravien
 */
public class testImpl implements IEasyWebsocket {
    
    private Session session;

    @Override
    public String handleMessage(String msg) {
        return "working";
    }
    public void x (Session session){
       session = session;
    }
    
    public void pushMessage(String msg) throws IOException{
        session.getBasicRemote().sendText(msg);
    }
    
    
    
}