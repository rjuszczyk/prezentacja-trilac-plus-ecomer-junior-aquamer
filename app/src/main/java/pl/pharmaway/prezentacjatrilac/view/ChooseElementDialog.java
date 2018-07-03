package pl.pharmaway.prezentacjatrilac.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Comparator;
import java.util.List;

import pl.pharmaway.prezentacjatrilac.R;
import pl.pharmaway.prezentacjatrilac.database.DataRow;
import pl.pharmaway.prezentacjatrilac.database.DatabaseHelper;

public class ChooseElementDialog extends BaseDialog implements StoresRecyclerViewAdapter.OnItemClickListener {
    public static String TAG = "ChoosePositionDialog";

    private StoresRecyclerViewAdapter mStoresRecyclerViewAdapter;

    private RecyclerView mRecyclerView;
    private EditText mSearchView;
    private View mProgressContainer;

    public static ChooseElementDialog createInstance()
    {
        ChooseElementDialog choosePositionDialog = new ChooseElementDialog();
        Bundle bundle = new Bundle();
        return choosePositionDialog;
    }

    public String getRowText(DataRow row) {
        return row.pm;
    }

    public List<DataRow> getRows(Context context) {
        return DatabaseHelper.rowsForLekarzType(context);
    }

    public void onRowSelected(DataRow row) {
    }

    protected boolean isTwoLines() {
        return false;
    }

    public ChooseElementDialog()
    {
        super();

        setStyle(STYLE_NO_TITLE, 0);
    }

    @Override
    public int getLayoutId() {

        return R.layout.dialog_choose_element;
    }

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) mContentView.findViewById(R.id.recycler_view);
        mProgressContainer = mContentView.findViewById(R.id.progress_contianer);
        mSearchView = (EditText) mContentView.findViewById(R.id.search_text);






        mSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                query(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        setupList();
    }

    private void hideKeyboard()
    {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(mSearchView.getApplicationWindowToken(),
                0);
    }

    public void query(CharSequence newText) {
        if (newText.length() > 1) {
            mStoresRecyclerViewAdapter.filter(newText.toString());
            return ;
        }
    }


    boolean storesLoaded = false;
    private void setupList() {
        long time = System.currentTimeMillis();
        if (mRecyclerView != null) {

            LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(manager);

            mStoresRecyclerViewAdapter = new StoresRecyclerViewAdapter() {

                @Override
                public Comparator<DataRow> getRowComparator() {
                    if(ChooseElementDialog.this instanceof ChooseSpecDialog) {
                        return null;
                    }
                    return super.getRowComparator();
                }

                @Override
                public String getTextFromRow(DataRow row) {
                    return getRowText(row);
                }

                public boolean isTwoLinesAdapter() {
                    return isTwoLines();
                }
            };

            mStoresRecyclerViewAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mStoresRecyclerViewAdapter);

            final Handler handler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final List<DataRow> stores = getRows(getActivity());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mStoresRecyclerViewAdapter.setStores(stores);
                            storesLoaded = true;
                            mProgressContainer.setVisibility(View.GONE);
                        }
                    });
                }
            }).start();
        }
        Log.d("loading choose dialog", "took = " + (System.currentTimeMillis() - time));
    }

    @Override
    public void onItemClick(View view, DataRow store) {
        onRowSelected(store);
        dismiss();
    }
}
