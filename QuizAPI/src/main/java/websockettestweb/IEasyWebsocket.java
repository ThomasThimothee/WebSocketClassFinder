package websockettestweb;

import java.util.Set;
import javax.websocket.Session;



/**
 *
 * @author mathiasjepsen
 */
public interface IEasyWebsocket{
    
    String handleMessage(String msg);
    void pushNotification(Set<Session> sessions);
    
}
