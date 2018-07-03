package pl.pharmaway.prezentacjatrilac.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

import pl.pharmaway.prezentacjatrilac.database.DataRow;

public class ChooseSpecDialog extends ChooseElementDialog {

    private String lekarz;

    @Override
    public String getRowText(DataRow row) {
        return row.pm;
    }

    public static ChooseSpecDialog create() {
        ChooseSpecDialog dialog = new ChooseSpecDialog();
        Bundle arguments = new Bundle();
        dialog.setArguments(arguments);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        lekarz = getArguments().getString("lekarz");

        super.onCreate(savedInstanceState);
    }

    @Override
    public List<DataRow> getRows(Context context) {
        return Arrays.asList(
                DataRow.create("Choroby wewnętrzne"),
                DataRow.create("Medycyna Rodzinna"),
                DataRow.create("Pediatria"),
                DataRow.create("Medycyna rodzinna Pediatria"),
                DataRow.create("Choroby wewnętrzne Medycyna rodzinna"),
                DataRow.create("Otolaryngologia"),
                DataRow.create("Alergologia"),
                DataRow.create("Inne")
        );
    }

    @Override
    public void onRowSelected(DataRow row) {
        mLekarzDialogListener.onSpecSelected(row.pm);
    }

    LekarzDialogListener mLekarzDialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof LekarzDialogListener) {
            mLekarzDialogListener = (LekarzDialogListener) context;
        } else {
            throw new RuntimeException(context.getClass().getSimpleName() + " must implement "
                    + LekarzDialogListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLekarzDialogListener = null;
    }

    public interface LekarzDialogListener {
        void onSpecSelected(String spec);
    }
}
