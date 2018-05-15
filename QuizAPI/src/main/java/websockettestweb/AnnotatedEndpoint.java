package websockettestweb;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.clapper.util.classutil.AndClassFilter;
import org.clapper.util.classutil.ClassFilter;
import org.clapper.util.classutil.ClassFinder;
import org.clapper.util.classutil.ClassInfo;
import org.clapper.util.classutil.InterfaceOnlyClassFilter;
import org.clapper.util.classutil.NotClassFilter;
import org.clapper.util.classutil.SubclassClassFilter;

/**
 *
 * @author mathiasjepsen
 */
@ServerEndpoint("/api")
public class AnnotatedEndpoint{
    private IEasyWebsocket webscoket;
    
    public AnnotatedEndpoint() throws InstantiationException, IllegalAccessException{
    try {
            URL url = null;

            try {
                url = getClass().getResource(".");
            } catch (Exception e) {
            }

            File dir = new File(url.toURI());
            
            ClassFinder finder = new ClassFinder();
            finder.add(dir);
            ClassFilter filter =
             new AndClassFilter
                 // Must not be an interface
                 (new NotClassFilter (new InterfaceOnlyClassFilter()),

                 // Must implement the ITestClass interface
                 new SubclassClassFilter (IEasyWebsocket.class));


         Collection<ClassInfo> foundClasses = new ArrayList();
         finder.findClasses (foundClasses, filter);

         for (ClassInfo classInfo : foundClasses){
             System.out.println ("Found " + classInfo.getClassName());
             String[] inter = classInfo.getInterfaces();
             for (String in : inter) System.out.println("inter: " + in);
             
             webscoket = (IEasyWebsocket)Class.forName(classInfo.getClassName()).newInstance();
             System.out.println(webscoket.handleMessage("sd"));
            //Method[] methods = newObject.getClass().getMethods();
//            for (Method met : methods)
//            {
//                System.out.println(met.getName());
//                 if(met.getName().equals("run")){
//                     met.invoke(newObject);
//                 }
//             }
         }
        } catch (URISyntaxException | SecurityException | IllegalArgumentException | ClassNotFoundException  ex) {
            Logger.getLogger(MyClassFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnMessage
    public void onMessage(Session session, String msg) throws IOException {
        System.out.println("Recived message"+msg);
        session.getBasicRemote().sendText(webscoket.handleMessage(msg));
    }
}
