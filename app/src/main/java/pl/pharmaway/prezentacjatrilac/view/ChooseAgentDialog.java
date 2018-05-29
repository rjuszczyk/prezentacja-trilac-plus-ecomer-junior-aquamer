package pl.pharmaway.prezentacjatrilac.view;

import android.content.Context;

import java.util.List;

import pl.pharmaway.prezentacjatrilac.Constants;
import pl.pharmaway.prezentacjatrilac.database.DataRow;
import pl.pharmaway.prezentacjatrilac.database.DatabaseHelper;


/**
 * Created by Radek on 2017-01-14.
 */

public class ChooseAgentDialog extends ChooseElementDialog {

    @Override
    public String getRowText(DataRow row) {
        return row.agent;
    }

    @Override
    public List<DataRow> getRows(Context context) {
        return DatabaseHelper.rowsForLekarzType(Constants.LEKARZ_TYPE, context);
    }

    public static ChooseAgentDialog create() {
        ChooseAgentDialog dialog = new ChooseAgentDialog();
        return dialog;
    }

    @Override
    public void onRowSelected(DataRow row) {
        mAgentDialogListener.onAgentSelected(row.agent);
    }

    AgentDialogListener mAgentDialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof AgentDialogListener) {
            mAgentDialogListener = (AgentDialogListener) context;
        } else {
            throw new RuntimeException(context.getClass().getSimpleName() + " must implement "
                    + AgentDialogListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAgentDialogListener = null;
    }

    public interface AgentDialogListener {
        void onAgentSelected(
                String agent
        );
    }
}