package com.turnkeyafrica.bankassurance.ui.inbox.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.turnkeyafrica.bankassurance.R;
import com.turnkeyafrica.bankassurance.ui.notificationdetails.NotificationDetailsActivity;

import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter{

    private ArrayList<InboxModel> dataSet;
        private Context mContext;


        public static class NewMessagesHolder extends RecyclerView.ViewHolder {

            LinearLayout  inboxBackground;

            TextView notifyTitleMain;

            ImageView newIcon;

            TextView notifyTitle;

            TextView notifySubject;

            TextView notifyMessage;

            CardView notificationCard;

            TextView notifyPeriod;

            public NewMessagesHolder(View itemView) {
                super(itemView);

                this.inboxBackground = itemView.findViewById(R.id.inboxBackground);
                this.notifyTitleMain = itemView.findViewById(R.id.notifyTitleMain);

                this.newIcon = itemView.findViewById(R.id.newIcon);

                this.notifyTitle = itemView.findViewById(R.id.notifyTitle);

                this.notifySubject = itemView.findViewById(R.id.notifySubject);

                this.notifyMessage = itemView.findViewById(R.id.notifyMessage);

                this.notificationCard = itemView.findViewById(R.id.notificationCard);

                this.notifyPeriod = itemView.findViewById(R.id.notifyTime);

            }
        }

    public static class OldMessagesHolder extends RecyclerView.ViewHolder {

        LinearLayout  inboxBackground;

        TextView notifyTitleMain;

        ImageView newIcon;

        TextView notifyTitle;

        TextView notifySubject;

        TextView notifyMessage;

        CardView notificationCard;

        TextView notifyPeriod;

        public OldMessagesHolder(View itemView) {
            super(itemView);

            this.inboxBackground = itemView.findViewById(R.id.inboxBackground);
            this.notifyTitleMain = itemView.findViewById(R.id.notifyTitleMain);

            this.newIcon = itemView.findViewById(R.id.newIcon);

            this.notifyTitle = itemView.findViewById(R.id.notifyTitle);

            this.notifySubject = itemView.findViewById(R.id.notifySubject);

            this.notifyMessage = itemView.findViewById(R.id.notifyMessage);

            this.notificationCard = itemView.findViewById(R.id.notificationCard);

            this.notifyPeriod = itemView.findViewById(R.id.notifyTime);

        }
    }

    public InboxAdapter(ArrayList<InboxModel>data, Context context) {
            this.dataSet = data;
            this.mContext = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            mContext = parent.getContext();

            View view;

            if(viewType == InboxModel.NEW_MESSAGE){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_layout, parent, false);
                return new NewMessagesHolder(view);
            }else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item_layout, parent, false);
                return new OldMessagesHolder(view);
            }

        }

        @Override
        public int getItemViewType(int position) {

            switch (dataSet.get(position).type) {
                case 0:
                    return InboxModel.NEW_MESSAGE;
                case 1:
                    return InboxModel.OLD_MESSAGE;
                default:
                    return -1;
            }
        }


        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int listPosition) {

            InboxModel object = dataSet.get(listPosition);
            if (object != null) {
                switch (object.type) {
                    case InboxModel.NEW_MESSAGE:
                        initializeNewMessages((NewMessagesHolder) holder, object,listPosition);
                        break;
                    case InboxModel.OLD_MESSAGE:
                        initializeOldMessages((OldMessagesHolder) holder, object,listPosition);
                        break;

                }
            }
        }

    private void initializeNewMessages(NewMessagesHolder holder, InboxModel mInboxModel, int listPosition) {
            holder.notifyTitle.setText(mInboxModel.mNotificationsResponse.getTitle());
            holder.newIcon.setVisibility(View.VISIBLE);
            holder.inboxBackground.setBackgroundColor(holder.itemView.getResources().getColor(R.color.bg_color3));
            holder.notifyMessage.setText(mInboxModel.mNotificationsResponse.getMessage());
            holder.notifySubject.setText(mInboxModel.mNotificationsResponse.getSubject());
            holder.notifyPeriod.setText(mInboxModel.mNotificationsResponse.getElaspedTime());
            if(mInboxModel.display) {
                holder.notifyTitleMain.setVisibility(View.VISIBLE);
                holder.notifyTitleMain.setText(holder.itemView.getResources().getText(R.string.New));
            }
            holder.notificationCard.setOnClickListener(view -> {
                Intent intent = NotificationDetailsActivity.newIntent(mContext);
                Gson gson = new Gson();
                String notification = gson.toJson(mInboxModel.mNotificationsResponse);
                intent.putExtra("A51@3%^",notification);
                mContext.startActivity(intent);
            });
    }

    private void initializeOldMessages(OldMessagesHolder holder, InboxModel mInboxModel, int listPosition) {

        holder.notifyTitle.setText(mInboxModel.mNotificationsResponse.getTitle());
        holder.notifyMessage.setText(mInboxModel.mNotificationsResponse.getMessage());
        holder.notifySubject.setText(mInboxModel.mNotificationsResponse.getSubject());
        holder.notifyPeriod.setText(mInboxModel.mNotificationsResponse.getElaspedTime());
        if(mInboxModel.display) {
            holder.notifyTitleMain.setVisibility(View.VISIBLE);
            holder.notifyTitleMain.setText(holder.itemView.getResources().getText(R.string.earlier));
        }
        holder.notificationCard.setOnClickListener(view -> {
            Intent intent = NotificationDetailsActivity.newIntent(mContext);
            Gson gson = new Gson();
            String notification = gson.toJson(mInboxModel.mNotificationsResponse);
            intent.putExtra("A51@3%^",notification);
            mContext.startActivity(intent);
        });
    }
        @Override
        public int getItemCount() {
            return dataSet.size();
        }

}