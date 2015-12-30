package hci.hrs.de.hrp.model;

import java.util.Date;
import java.util.Vector;

/**
 * Created by gstevens on 22.12.2015.
 */
public class Invoice {

    /**
     *
     */
    class Item {
        public String pid;
        public String price;
    }

    /**
     *
     */
    private Date date;

    /**
     *
     */
    private Vector<Item> items;

    /**
     * foreign key on the Supplier
     */
    private String supplierID;

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
