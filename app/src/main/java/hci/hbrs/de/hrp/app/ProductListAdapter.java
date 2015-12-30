package hci.hbrs.de.hrp.app;

import java.util.ArrayList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import hci.hbrs.de.simplescanner.BarcodeListener;
import hci.hrs.de.hrp.model.Product;
import hci.hbrs.de.simplescanner.R;

/**
 * Created by gstevens on 30.12.2015.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>
        implements BarcodeListener {
    private ArrayList<Product> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ProductViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtName;
        public TextView txtPID;

        public ProductViewHolder(View v) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.product_name);
            txtPID = (TextView) v.findViewById(R.id.product_id);
        }
    }

    public void add(int position, Product item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Product item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductListAdapter(ArrayList<Product> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ProductViewHolder vh = new ProductViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Product product = mDataset.get(position);
        holder.txtName.setText("" + product.getName());
        holder.txtName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(product);
            }
        });

        holder.txtPID.setText("" + product.getPID());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onBarcodeScannedEvent(String barcode) {
        // Add item to adapter
        Product newProduct = new Product(barcode);
        // this.add(newProduct);
    }

}