package websockettestweb;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author mathiasjepsen
 */
@ServerEndpoint("/api")
public class AnnotatedEndpoint {

    public AnnotatedEndpoint() {

        try {
            String path = "";
            String newPath = "";
            try {
                path = getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            } catch (URISyntaxException ex) {
                Logger.getLogger(AnnotatedEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(path);
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    System.out.println("File " + file.getName());
                } else if (file.isDirectory()) {
                    newPath = path + file.getName();
                }
            }

            File subFolder = new File(newPath);
            File[] listOfFiles1 = subFolder.listFiles();
            for (File file : listOfFiles1) {
                if (file.isFile()) {
                    System.out.println("File loop2 " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Directory " + file.getName());
                }
            }

            File classDir = new File(newPath);
            URL[] url = {classDir.toURI().toURL()};
            System.out.println("URLs" + url[0].toString());
            URLClassLoader urlLoader = new URLClassLoader(url);
            for (File file : classDir.listFiles()) {
                System.out.println("in loop 3");
              String  filename = file.getName();
                System.out.println("file name: " + filename);

                /*                
            if (filename.equals("EasyWebsocketImpl.class")){
                Class instance = (Class) urlLoader.loadClass(filename).newInstance();
                                  
               Class[] i = instance.getInterfaces();
               System.out.println("Interfaces = " + Arrays.asList(i));
            }
                 */
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(AnnotatedEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("bla");
    }
}
