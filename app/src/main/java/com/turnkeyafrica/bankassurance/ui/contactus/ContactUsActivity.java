package com.turnkeyafrica.bankassurance.ui.contactus;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import android.widget.TextView;
import com.turnkeyafrica.bankassurance.BR;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ViewModelProviderFactory;
import com.turnkeyafrica.bankassurance.data.model.others.LocalError;
import com.turnkeyafrica.bankassurance.databinding.ActivityContactUsBinding;
import com.turnkeyafrica.bankassurance.ui.base.BaseActivity;
import javax.inject.Inject;

public class ContactUsActivity extends BaseActivity<ActivityContactUsBinding, ContactUsViewModel> implements ContactUsNavigator {


    @Inject
    ViewModelProviderFactory factory;
    private ContactUsViewModel mContactUsViewModel;

    private ActivityContactUsBinding mActivityContactUsBinding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact_us;
    }

    @Override
    public ContactUsViewModel getViewModel() {
        mContactUsViewModel = new ViewModelProvider(getViewModelStore(),factory).get(ContactUsViewModel.class);
        return mContactUsViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactUsViewModel.setNavigator(this);
        mActivityContactUsBinding = getViewDataBinding();
        Toolbar toolbar = mActivityContactUsBinding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setActions();
        mContactUsViewModel.getContacts();
    }

    private void setActions() {

        mContactUsViewModel.mainContact.set(getResources().getString(R.string.mainSupportNumber));

        mContactUsViewModel.cellOne.set(getResources().getString(R.string.cellOne));

        mContactUsViewModel.cellTwo.set(getResources().getString(R.string.cellTwo));

        TextView supportNumber = mActivityContactUsBinding.supportNumber;

        TextView cellNumber = mActivityContactUsBinding.cellNumber;

        TextView cellNumber2 = mActivityContactUsBinding.cellNumber2;

        TextView supportEmail= mActivityContactUsBinding.supportEmail;

       supportNumber.setOnClickListener(v -> {

           Intent intent = new Intent(Intent.ACTION_DIAL);

           intent.setData(Uri.parse("tel:" + supportNumber.getText().toString()));

           startActivity(intent);

       });

        cellNumber.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_DIAL);

            intent.setData(Uri.parse("tel:" + cellNumber.getText().toString()));

            startActivity(intent);

        });

        cellNumber2.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_DIAL);

            intent.setData(Uri.parse("tel:" + cellNumber2.getText().toString()));

            startActivity(intent);

        });

        supportEmail.setOnClickListener(v -> {

            String mailto = "mailto:" + supportEmail.getText().toString();

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse(mailto));

            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                handleError(new LocalError(0,"Alert \n\n No mail application found."));
            }

        });

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ContactUsActivity.class);
    }


}
