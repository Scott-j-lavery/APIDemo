package fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobiquity.demo.apidemo.R;
import com.mobiquity.demo.apidemo.Stock;

import interfaces.ActivityListener;
import interfaces.MyFragmentListener;

/**
 * Created by sclavery on 7/19/15.
 */
public class ResultsFragment extends Fragment implements ActivityListener{

    public static final String TAG = "ResultsFragment";
    private static Activity mContainingActivity;
    private Stock mStock;
    


// INSTANTIATION FACTORY

    public static ResultsFragment newInstance() {
        ResultsFragment frag = new ResultsFragment();
        return frag;
    }


    @Override
    // view = inflate my fragment XML into containerBob without a ViewGroup
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreatView()");
        View view = _inflater.inflate(R.layout.fragment_result, _container, false);
        if (mStock != null) {
            ((TextView) view.findViewById(R.id.stockSymbol)).setText(mStock.getmSymbol());
            ((TextView) view.findViewById(R.id.stockPrice)).setText(mStock.getmPrice());
            ((TextView) view.findViewById(R.id.stockChange)).setText(mStock.getmChange());
        }


        return view;


    }

    public void onStart() {
        Log.i(TAG, "onStart()");
        super.onStart();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("savedStock", mStock);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate()");
        super.onCreate(savedInstanceState);

        if ( savedInstanceState != null){
            mStock = (Stock) savedInstanceState.getSerializable("savedStock");
        }
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume()");
        super.onResume();
    }

    public void onAttach(Activity _activity) {
        Log.i(TAG, "onAttach()");
        super.onAttach(_activity);
        if (_activity instanceof MyFragmentListener) {
            mContainingActivity =   _activity;
        } else {
            // WE SHOULD NEVER GET HERE
            throw new IllegalArgumentException("Activity must implement MyFragmentListener");
        }
    }

    @Override
    public void updateUi(Stock stock) {
        mStock = stock;
        ((TextView) mContainingActivity.findViewById(R.id.stockSymbol)).setText(stock.getmSymbol());
        ((TextView) mContainingActivity.findViewById(R.id.stockPrice)).setText(stock.getmPrice());
        ((TextView) mContainingActivity.findViewById(R.id.stockChange)).setText(stock.getmChange());    }
}


