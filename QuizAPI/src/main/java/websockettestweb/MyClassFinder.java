/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websockettestweb;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.clapper.util.classutil.AndClassFilter;
import org.clapper.util.classutil.ClassFilter;
import org.clapper.util.classutil.ClassFinder;
import org.clapper.util.classutil.ClassInfo;
import org.clapper.util.classutil.InterfaceOnlyClassFilter;
import org.clapper.util.classutil.NotClassFilter;
import org.clapper.util.classutil.SubclassClassFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author thomasthimothee
 */
public class MyClassFinder {

    public MyClassFinder() throws InstantiationException, IllegalAccessException {
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
             
            IEasyWebsocket newObject = (IEasyWebsocket)Class.forName(classInfo.getClassName()).newInstance();
            // this part is usefull if you wanna run a specifc method on run time
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

}
