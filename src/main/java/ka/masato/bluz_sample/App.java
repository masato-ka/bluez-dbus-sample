package ka.masato.bluz_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public static void main( String[] args ) throws InterruptedException, IOException
    {
    	
    	try {
			DeviceManager.createInstance(false);
		} catch (DBusException e1) {
		    System.out.println("failed create instance caused by D-BUS.");
		}
    	DeviceManager deviceManager = DeviceManager.getInstance();
    	
    	List<BluetoothAdapter> result = deviceManager.getAdapters();
    	BluetoothAdapter bluetoothAdaptor = result.get(0);	
    	System.out.println("-----------------HCI Interface---------------------");
    	result.stream().map(e->e.getDeviceName()).forEach(System.out::println);
    	System.out.println("-----------------Scan BLE Device---------------------");
    	bluetoothAdaptor.startDiscovery();
    	Thread.sleep(5000);
    	bluetoothAdaptor.stopDiscovery();
    	System.out.println("-----------------Result BLE Device----------------");
	   	List<BluetoothDevice> devicies = deviceManager.getDevices();
    	devicies.stream().map(e->e.getName()).forEach(System.out::println);
    	BufferedReader buffredReader = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print("Choose device:");
    	String deviceName = buffredReader.readLine();
    	System.out.println("-----------------Characteristics----------------");
    	for(BluetoothDevice bluetoothDevice : devicies){
    		if(bluetoothDevice.getName().equals(deviceName)){
    			try{
	    			bluetoothDevice.connect();
					bluetoothDevice.refreshGattServices();
    				List<BluetoothGattService> servicies = bluetoothDevice.getGattServices();
	    			for(BluetoothGattService service : servicies){	
	    				List<BluetoothGattCharacteristic> characteristics  = service.getGattCharacteristics();
	    				characteristics.stream().map(e->e.getUuid()).forEach(System.out::println);
	    			}
	    		}catch(DBusExecutionException e){
	    			System.out.println("FailedConnection");
	    			e.printStackTrace();
	    		}
	    	}
    	}
    }
}
