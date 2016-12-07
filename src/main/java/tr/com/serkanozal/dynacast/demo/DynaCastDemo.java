package tr.com.serkanozal.dynacast.demo;

import java.io.IOException;
import java.net.UnknownHostException;

import tr.com.serkanozal.dynacast.DynaCast;
import tr.com.serkanozal.dynacast.storage.DynaCastStorage;
import tr.com.serkanozal.dynacast.storage.DynaCastStorageType;

public class DynaCastDemo {

    public static void main(String[] args) throws UnknownHostException, IOException {
        DynaCastStorage<Integer, String> storage = 
                DynaCast.getOrCreateStorage("MyDemoStorage", DynaCastStorageType.TIERED);
        
        ///////////////////////////////////////////////////////////////////////
        
        System.out.println("Clearing storage initially ...");
        
        storage.clear();
        
        ///////////////////////////////////////////////////////////////////////
        
        System.out.println("================================");
        for (int i = 0; i < 10; i++) {
            System.out.println("\t- [" + i + "]: " + storage.get(i));
        }
        System.out.println("================================\n");
        
        ///////////////////////////////////////////////////////////////////////
        
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("Put key: %d, value: %s ...", i, "value-" + i));
            storage.put(i, "value-" + i);
        }
        
        ///////////////////////////////////////////////////////////////////////
        
        System.out.println("================================");
        for (int i = 0; i < 10; i++) {
            System.out.println("\t- [" + i + "]: " + storage.get(i));
        }
        System.out.println("================================\n");

        ///////////////////////////////////////////////////////////////////////
        
        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("Remove key: %d ...", i));
            storage.remove(i);
        }
        
        ///////////////////////////////////////////////////////////////////////
        
        System.out.println("================================");
        for (int i = 0; i < 10; i++) {
            System.out.println("\t- [" + i + "]: " + storage.get(i));
        }
        System.out.println("================================\n");
        
        ///////////////////////////////////////////////////////////////////////
        
        System.out.println("Destroying storage ...");
        
        storage.destroy();
    }

}
