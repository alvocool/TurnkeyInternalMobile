
package com.turnkeyafrica.turnkeybankassurance.ui.cardpayment.carddatesdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.turnkeyafrica.turnkeybankassurance.R;
import com.turnkeyafrica.turnkeybankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.turnkeybankassurance.databinding.DialogCardDatesBinding;
import com.turnkeyafrica.turnkeybankassurance.ui.base.BaseDialog;
import com.turnkeyafrica.turnkeybankassurance.ui.cardpayment.CardPaymentActivity;
import com.turnkeyafrica.turnkeybankassurance.ui.cardpayment.carddatesdialog.adapter.MySimpleArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CardDatesDialog extends BaseDialog implements CardDatesCallback {

    private static final String TAG = CardDatesDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;

    private CardDatesViewModel mCardDatesViewModel;

    private DialogCardDatesBinding mDialogCardDatesBinding;

    private  int dateOption;


    public static CardDatesDialog newInstance() {
        CardDatesDialog fragment = new CardDatesDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void close() {

        dismissDialog(TAG);
    }


    private void setValues(int option) {

        switch (option){

            case 1:
                setMonths();
                break;
            case 2:
                setYears();
                break;
            default:
                close();
                break;
        }

    }

    private void setYears() {


        List<String> years = new ArrayList<>();

     int today = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = 0; i<= 5; i++){
            years.add(String.valueOf(today));
            ++today;
        }

        MySimpleArrayAdapter mySimpleArrayAdapter = new MySimpleArrayAdapter(getContext(), years);

        mDialogCardDatesBinding.cardDatesList.setAdapter(mySimpleArrayAdapter);
        mDialogCardDatesBinding.cardDatesList.setOnItemClickListener((parent, view, position, id) -> {

            String itemAtPosition = (String) mDialogCardDatesBinding.cardDatesList.getItemAtPosition(position);

            ((CardPaymentActivity) Objects.requireNonNull(getActivity())).setYear(itemAtPosition);

            close();
        });
    }

    private void setMonths() {

        String[] data = {"01","02","03","04","05","06","07","08","09","10",
                "11","12"};

       List<String> months =  Arrays.asList(data);

        MySimpleArrayAdapter mySimpleArrayAdapter = new MySimpleArrayAdapter(getContext(), months);
        mDialogCardDatesBinding.cardDatesList.setAdapter(mySimpleArrayAdapter);
        mDialogCardDatesBinding.cardDatesList.setOnItemClickListener((parent, view, position, id) -> {

            String itemAtPosition = (String) mDialogCardDatesBinding.cardDatesList.getItemAtPosition(position);

            ((CardPaymentActivity) Objects.requireNonNull(getActivity())).setMonth(itemAtPosition);

            close();
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogCardDatesBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_card_dates, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mCardDatesViewModel = new ViewModelProvider(getViewModelStore(),factory).get(CardDatesViewModel.class);
        binding.setViewModel(mCardDatesViewModel);

        mCardDatesViewModel.setNavigator(this);

        mDialogCardDatesBinding = binding;

        this.setValues(dateOption);

        return view;
    }

    public void show(FragmentManager fragmentManager, int dateOption) {
        this.dateOption = dateOption;
        super.show(fragmentManager, TAG);
    }
}
