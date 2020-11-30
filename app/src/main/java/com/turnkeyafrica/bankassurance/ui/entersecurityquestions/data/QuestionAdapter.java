package com.turnkeyafrica.bankassurance.ui.entersecurityquestions.data;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.data.model.api.SecurityQuestions;

public class QuestionAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<SecurityQuestions> questionsList;

    public QuestionAdapter(Context applicationContext, List<SecurityQuestions> questionsList) {
        this.context = applicationContext;
        this.questionsList = questionsList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return questionsList.size();
    }

    @Override
    public Object getItem(int position) {
        return questionsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_layout, null);
        TextView item = view.findViewById(R.id.item);
        item.setText(questionsList.get(i).getDescription());
        item.setId(questionsList.get(i).getCode());
        return view;

    }


}
