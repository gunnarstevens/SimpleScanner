package hci.hbrs.de.simplescanner;


/**
 * Created by gstevens on 28.12.2015.
 */
public interface BarcodeListener {

    /**
     * The method is called when a new barcode was scanned
     * @param barcode
     */
    void onBarcodeScannedEvent(String barcode);
}
