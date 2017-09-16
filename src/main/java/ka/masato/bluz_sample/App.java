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
	    	if(bluetoothDevice.getName().equals("Env")){
	    		try{
	    			bluetoothDevice.connect();
	    			List<BluetoothGattService> servicies = bluetoothDevice.getGattServices();
	    			if( servicies.size() > 0){
	    				List<BluetoothGattCharacteristic> characteristics  = servicies.get(0).getGattCharacteristics();
	    				characteristics.stream().map(e->e.getUuid()).forEach(System.out::println);
	    			}else{
	    				System.out.println("No servicies");
	    			}	
	    		}catch(DBusExecutionException e){
	    			System.out.println("FailedConnection");
	    			e.printStackTrace();
	    		}
	    	}
	    }
    }
}
