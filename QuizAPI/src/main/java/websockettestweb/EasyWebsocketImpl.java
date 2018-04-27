package websockettestweb;

import org.clapper.util.classutil.ClassFinder;
import org.clapper.util.classutil.ClassInfo;

/**
 *
 * @author mathiasjepsen
 */
public class EasyWebsocketImpl implements IEasyWebsocket {

    @Override
    public String handleMessage(String msg) {
        return msg + " : echo";
    }

    @Override
    public boolean accept(ClassInfo ci, ClassFinder cf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
