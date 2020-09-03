package com.turnkeyafrica.turnkeybankassurance.ui.dashboard.policy.policyitem.adapter;


import com.turnkeyafrica.turnkeybankassurance.data.model.api.InsuranceQuoteResponce;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.PolicyResponce;

public class DashboardModel {

        public static final int TITLE_TYPE=0;
        public static final int POLICY_TYPE=1;
        public static final int QUOTE_TYPE=2;

        public int type;
        public PolicyResponce mPolicyResponce;
        public InsuranceQuoteResponce mQouteResponce;
        public String text;

        public DashboardModel(int type, String text, PolicyResponce policyResponce, InsuranceQuoteResponce qouteResponce)
        {
            this.type=type;
            this.text=text;
            this.mPolicyResponce=policyResponce;
            this.mQouteResponce=qouteResponce;
        }
    }


