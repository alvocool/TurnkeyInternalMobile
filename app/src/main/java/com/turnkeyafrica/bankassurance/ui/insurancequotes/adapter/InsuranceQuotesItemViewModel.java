package com.turnkeyafrica.bankassurance.ui.insurancequotes.adapter;

import androidx.databinding.ObservableField;

import com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.bankassurance.utils.CommonUtils;

public class InsuranceQuotesItemViewModel {

    public final ObservableField<String> Insurer = new ObservableField<>();

    public final ObservableField<String> amount = new ObservableField<>();

    public final ObservableField<String> coverType= new ObservableField<>();

    public final InsuranceQuotesItemViewModelListener mListener;

    private final ObservableField<InsuranceQuoteResponce> minsuranceQuoteResponceObservableField;

    public InsuranceQuotesItemViewModel(InsuranceQuoteResponce insuranceQuoteResponce, InsuranceQuotesItemViewModelListener listener) {

        this.minsuranceQuoteResponceObservableField = new ObservableField<>(insuranceQuoteResponce);
        String price = "n/a";
        if (insuranceQuoteResponce.getInsuranceQuotation() != null) {

            if (!String.valueOf(insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount()).isEmpty()) {
                price = "KSh" + " " + CommonUtils.StringToCurrency(insuranceQuoteResponce.getInsuranceQuotation().getPremiumAmount().toString()) + " " + "per year";
            }
        }
        this.mListener = listener;

        if (insuranceQuoteResponce.getInsuranceQuotation() != null){
            if(insuranceQuoteResponce.getInsuranceQuotation().getAgnName() != null) {
                Insurer.set(insuranceQuoteResponce.getInsuranceQuotation().getAgnName());
            }else { Insurer.set("n/a"); }
        }
        if (insuranceQuoteResponce.getInsuranceQuotation() != null){
            if(insuranceQuoteResponce.getInsuranceQuotation().getCoverType() != null) {
                coverType.set(insuranceQuoteResponce.getInsuranceQuotation().getCoverType());
            }else { coverType.set("n/a"); }
        }
        amount.set(price);

    }

    
    public void onItemClick(InsuranceQuotesItemViewModel insuranceQuotesItemViewModel) {

        mListener.onItemClick(insuranceQuotesItemViewModel.minsuranceQuoteResponceObservableField);
    }




}
