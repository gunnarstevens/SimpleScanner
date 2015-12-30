package hci.hbrs.de.simplescanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


import com.generalscan.NotifyStyle;
import com.generalscan.SendConstant;
import com.generalscan.bluetooth.BluetoothConnect;

import java.util.Vector;

/**
 *
 * @author GeneralScan
 *
 */
public class BarcodeScannerManagerImpl extends BroadcastReceiver implements BarcodeScannerManager {

    private Context context;
    final private Vector<BarcodeListener> listeners = new Vector<BarcodeListener>();


    private String nextBarcode = "";

    public BarcodeScannerManagerImpl(Context context) {
        this.context = context;

    }

    public void bind() {
        // to be sure that the scanner was unbind first
        //unbind();

        BluetoothConnect.CurrentNotifyStyle = NotifyStyle.NotificationStyle1;
        // Bind Bluetooth Service(Must bind service before start)
        BluetoothConnect.BindService(context);

        IntentFilter filter = new IntentFilter();
        filter.addAction(SendConstant.GetDataAction);
        filter.addAction(SendConstant.GetReadDataAction);
        filter.addAction(SendConstant.GetBatteryDataAction);
        context.registerReceiver(this, filter);

        try {
            BluetoothConnect.Connect();
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }
    public void unbind() {
        context.unregisterReceiver(this);

        BluetoothConnect.Stop(context);
        BluetoothConnect.UnBindService(context);
    }

    public void addBarcodeListener(BarcodeListener listener) {
        listeners.add(listener);
    }

    public void removeBarcodeListener(BarcodeListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SendConstant.GetDataAction)) {
            String data = intent.getStringExtra(SendConstant.GetData);

            // if is a digit than it is the next character of the scanned barcode
            if(data.length() > 0 && '0' <= data.charAt(0) &&  data.charAt(0) <= '9' ) {
                nextBarcode += data.charAt(0);
            } else if(nextBarcode.length() > 0 ){
                String barcode = nextBarcode;
                nextBarcode = "";
                barcodeScanned(barcode);
            }
        }

    }

    /**
     * inform listener that a new barcode was scanned
     * @param barcode
     */
    private void barcodeScanned(String barcode) {
        for (BarcodeListener listener: listeners) {
            listener.onBarcodeScannedEvent(barcode);
        }
    }


}
