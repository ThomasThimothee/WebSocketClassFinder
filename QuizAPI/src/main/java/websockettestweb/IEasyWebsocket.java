package websockettestweb;

import org.clapper.util.classutil.ClassFilter;

/**
 *
 * @author mathiasjepsen
 */
public interface IEasyWebsocket extends ClassFilter{
    
    String handleMessage(String msg);
    
}
