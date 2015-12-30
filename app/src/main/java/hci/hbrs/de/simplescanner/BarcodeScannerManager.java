package hci.hbrs.de.simplescanner;

/**
 * Created by gstevens on 29.12.2015.
 */
public interface BarcodeScannerManager {


    /**
     * Bind to the barcode scanner.
     * If is was bind beforehand, it rebind the scanner
     */
    void bind();

    /**
     * Unbind to the barcode scanner.
     */
    void unbind();

    /**
     * Add a barcode listener
     * The listener will be informed, when a new product is scanned.
     * @param listener
     */
    void addBarcodeListener(BarcodeListener listener);

}
