package pl.pharmaway.prezentacjatrilac.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import pl.pharmaway.prezentacjatrilac.database.DataRow;
import pl.pharmaway.prezentacjatrilac.database.DatabaseHelper;

public class ChooseMiastoDialog extends ChooseElementDialog {

    private String pm;

    @Override
    public String getRowText(DataRow row) {
        return row.m;
    }

    public static ChooseMiastoDialog create(String pm) {
        ChooseMiastoDialog dialog = new ChooseMiastoDialog();
        Bundle arguments = new Bundle();
        arguments.putString("pm", pm);
        dialog.setArguments(arguments);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        pm = getArguments().getString("pm");

        super.onCreate(savedInstanceState);
    }

    @Override
    public List<DataRow> getRows(Context context) {
        return DatabaseHelper.miastoForPm(context, pm);
    }

    @Override
    public void onRowSelected(DataRow row) {
        mLekarzDialogListener.onMiastoSelected(row.m);
    }

    MiastoDialogListener mLekarzDialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MiastoDialogListener) {
            mLekarzDialogListener = (MiastoDialogListener) context;
        } else {
            throw new RuntimeException(context.getClass().getSimpleName() + " must implement "
                    + MiastoDialogListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLekarzDialogListener = null;
    }

    public interface MiastoDialogListener {
        void onMiastoSelected(String spec);
    }
}
