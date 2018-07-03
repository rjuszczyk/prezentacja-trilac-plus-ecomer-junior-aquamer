package pl.pharmaway.prezentacjatrilac.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import pl.pharmaway.prezentacjatrilac.database.DataRow;
import pl.pharmaway.prezentacjatrilac.database.DatabaseHelper;

public class ChooseInstytucjaDialog extends ChooseElementDialog {

    private String pm;
    private String m;

    @Override
    public String getRowText(DataRow row) {
        return row.i;
    }

    public static ChooseInstytucjaDialog create(String pm, String m) {
        ChooseInstytucjaDialog dialog = new ChooseInstytucjaDialog();
        Bundle arguments = new Bundle();
        arguments.putString("pm", pm);
        arguments.putString("m", m);
        dialog.setArguments(arguments);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        pm = getArguments().getString("pm");
        m = getArguments().getString("m");

        super.onCreate(savedInstanceState);
    }

    @Override
    public List<DataRow> getRows(Context context) {
        return DatabaseHelper.instytucjaForPmAndM(context, pm, m);
    }

    @Override
    public void onRowSelected(DataRow row) {
        mLekarzDialogListener.onInstytucjaSelected(row.i);
    }

    InstytucjaDialogListener  mLekarzDialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof InstytucjaDialogListener ) {
            mLekarzDialogListener = (InstytucjaDialogListener ) context;
        } else {
            throw new RuntimeException(context.getClass().getSimpleName() + " must implement "
                    + InstytucjaDialogListener .class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLekarzDialogListener = null;
    }

    public interface InstytucjaDialogListener {
        void onInstytucjaSelected(String spec);
    }
}
