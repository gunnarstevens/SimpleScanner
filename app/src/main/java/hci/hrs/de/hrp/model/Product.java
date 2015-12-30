package hci.hrs.de.hrp.model;

//import org.jsoup.nodes.Element;
//import org.jsoup.nodes.Document;
//import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Vector;


/**
 * Created by gstevens on 20.12.2015.
 */
public class Product {


    /**
     * The pid is an identifier of the product usually scanned via a barcode scanner
     * In most of the cases this will be the GTIN-x (@see http://www.gtin.info/).
     * However also other formats are allowed and the system must be robust enough to deal with this.
     */
    private String pid;

    /**
     * The name of the product
     */
    private String name;

    /**
     * Set of foreign keys to the product group the product is associated with
     */
    private Vector<String> productGroup;

    public Product() {
        productGroup = new  Vector<String>();
    }

    public Product(String pid) {
        this.pid = pid;
    }

    /*
            * @return
    */
    public String getName()  {
        if(name == null) {
            // lookupProduct();
        }
        return name;
    }
    /**
     * Get the identifier of the product
     * In most of the cases this will be the GTIN-x (@see http://www.gtin.info/).
     * However also other formats are allowed and the system must be robust enough to deal with this.
     * @return product id
     */
    public String getPID() {
        return pid;
    }

    /**

    /**
     *
     * @return
     * @throws IOException
     */
    public Vector<ProductGroup> getProductGroup() throws IOException {
        if(productGroup == null) {
            // lookupProduct();
        }
        return null;
    }

//    private void lookupProduct() throws IOException {
//        String url = "http://www.hrs.info/product.search?q=" + pid + "&OK=Suchen";
//        Document doc = Jsoup.connect(url).get();
//
//        // select product name
//        Element nameElem = doc.select("div.product-title h1").first();
//        if(nameElem != null) {
//            name = nameElem.text();
//        } else {
//            System.err.println("Not found: " + url);
//        }
//
//        // select product category
//        Element catElem = doc.select("div.product-info-item-list").first();
//        if(catElem != null) productGroup.add(catElem.text());
//
//    }
}
