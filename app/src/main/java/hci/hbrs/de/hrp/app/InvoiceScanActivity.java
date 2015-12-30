package hci.hbrs.de.hrp.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hci.hbrs.de.simplescanner.BarcodeScannerManager;
import hci.hbrs.de.simplescanner.BarcodeScannerManagerImpl;
import hci.hbrs.de.simplescanner.R;
import hci.hrs.de.hrp.model.Product;


public class InvoiceScanActivity extends AppCompatActivity  {

    private BarcodeScannerManager mReadBroadcast;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_scan);

        // Initialize Toolbar stuff
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mReadBroadcast.bind();
                Snackbar.make(view, "Scanner Connected", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Create Dummy Products
        List valueList = new ArrayList<Product>();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize an empty invoice list
        if(mAdapter == null) {
            ArrayList<Product> data = new ArrayList<Product>();
            data.add(new Product("TEST "));
            data.add(new Product("TEST 1"));
            data.add(new Product("TEST 2"));
            data.add(new Product("TEST 3"));
            data.add(new Product("TEST 4"));
            data.add(new Product("TEST 5"));
            data.add(new Product("TEST 6"));
            data.add(new Product("Hallo 7"));
            mAdapter = new ProductListAdapter(data);
        }
        mRecyclerView.setAdapter(mAdapter);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize the BarcodeScanner
        mReadBroadcast = new BarcodeScannerManagerImpl(this);
        mReadBroadcast.addBarcodeListener((ProductListAdapter) mAdapter);

        getLoaderManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mReadBroadcast.bind();
    }

    @Override
    protected void onStop() {
        mReadBroadcast.unbind();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mReadBroadcast.unbind();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_invoice_scan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
