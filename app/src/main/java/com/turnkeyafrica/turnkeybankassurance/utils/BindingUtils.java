package com.turnkeyafrica.turnkeybankassurance.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims.ClaimsAdapter;
import com.turnkeyafrica.turnkeybankassurance.ui.dashboard.claims.ClaimsItemViewModel;
import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;



public final class BindingUtils {

    private BindingUtils() {
    }


  /*  @BindingAdapter({"adapter"})
    public static void addQuoteItems(RecyclerView recyclerView, List<QouteResponce> qouteResponces) {
        InsuranceQuotesAdapter adapter = (InsuranceQuotesAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(qouteResponces);
        }
    }*/


    @BindingAdapter({"adapter"})
    public static void addClaimsItems(RecyclerView recyclerView, List<ClaimsItemViewModel> claimsItems) {
        ClaimsAdapter adapter = (ClaimsAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(claimsItems);
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}
