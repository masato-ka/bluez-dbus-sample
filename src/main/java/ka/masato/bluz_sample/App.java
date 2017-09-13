package ka.masato.bluz_sample;

import java.util.List;
import java.util.Set;

import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;

import com.github.hypfvieh.DbusHelper;
import com.github.hypfvieh.bluetooth.DeviceManager;
import com.github.hypfvieh.bluetooth.wrapper.BluetoothAdapter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
			DeviceManager.createInstance(false);
		} catch (DBusException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	DeviceManager deviceManager = DeviceManager.getInstance();
    	List<BluetoothAdapter> result = deviceManager.getAdapters();
    	
    	result.stream().map(e->e.getDeviceName()).forEach(System.out::println);
    	
    	
        System.out.println( "Hello World!" );
    }
}
