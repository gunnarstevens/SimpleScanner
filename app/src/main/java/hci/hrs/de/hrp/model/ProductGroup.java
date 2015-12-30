package hci.hrs.de.hrp.model;

/**
 * Created by gstevens on 23.12.2015.
 */
public class ProductGroup {

    /**
     *
     */
    private String pgid;

    /**
     *
     */
    private String name;

    /**
     *
     * @return
     */
    static ProductGroup getById() {
        return null;
    }

    /**
     *
     * @return
     */
    static ProductGroup getByName() {
        return null;
    }

    /**
     *
     * @param pgid
     * @param name
     */
    ProductGroup(String pgid, String name) {
        this.name = name;
        this.pgid = pgid;
    }
}
