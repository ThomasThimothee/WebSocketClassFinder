package websockettestweb;

import javax.websocket.Session;



/**
 *
 * @author mathiasjepsen
 */
public interface IEasyWebsocket{
    
    String handleMessage(String msg);
    
}
