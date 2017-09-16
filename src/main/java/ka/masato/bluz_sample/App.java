package ka.masato.bluz_sample;

import java.util.List;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;

import com.github.hypfvieh.bluetooth.DeviceManager;
import com.github.hypfvieh.bluetooth.wrapper.BluetoothAdapter;
import com.github.hypfvieh.bluetooth.wrapper.BluetoothDevice;
import com.github.hypfvieh.bluetooth.wrapper.BluetoothGattCharacteristic;
import com.github.hypfvieh.bluetooth.wrapper.BluetoothGattService;

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
    	
    	List<BluetoothDevice> devicies = deviceManager.getDevices();
    	devicies.stream().map(e->e.getName()).forEach(System.out::println);
    	for(BluetoothDevice bluetoothDevice : devicies){
    	try{
    		bluetoothDevice.connect();
        	List<BluetoothGattService> servicies = bluetoothDevice.getGattServices();
        	List<BluetoothGattCharacteristic> characteristics  = servicies.get(0).getGattCharacteristics();
        	
        	characteristics.stream().map(e->e.getUuid()).forEach(System.out::println);

    	}catch(DBusExecutionException e){
    		
    	}
    	}
    }
}
