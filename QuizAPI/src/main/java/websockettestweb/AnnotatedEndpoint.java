package websockettestweb;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author mathiasjepsen
 */
@ServerEndpoint("/api")
public class AnnotatedEndpoint {

    private IEasyWebsocket userImplementation;
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    public AnnotatedEndpoint() throws InstantiationException, IllegalAccessException {
        try {
            URL url = null;

            try {
                url = getClass().getResource(".");
            } catch (Exception e) {
            }

            File dir = new File(url.toURI());
            MyClassFinder cf = new MyClassFinder();
            userImplementation = cf.findClass(dir);          

            Thread t1 = new Thread(() -> {
                userImplementation.pushNotification(sessions);
            });
            t1.start();

        } catch (URISyntaxException | SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(MyClassFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("added session");

    }

    @OnMessage
    public void onMessage(Session session, String msg) throws IOException {
        System.out.println("amount of sessions " + sessions.size());
        System.out.println("Recived message" + msg);
        session.getBasicRemote().sendText(userImplementation.handleMessage(msg));
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Session: " + session.getId() + " has disconected");
        sessions.remove(session);
    }

}
