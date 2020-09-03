package com.turnkeyafrica.turnkeybankassurance.ui.servicerequests.adapter;

import com.turnkeyafrica.turnkeybankassurance.data.model.api.ServiceRequest;

public class ServiceRequestModel {

        public static final int TITLE_TYPE=0;
        public static final int SERVICEREQUEST=1;

        public int type;
        public ServiceRequest mServiceRequest;
        public String text;

        public ServiceRequestModel(int type, String text, ServiceRequest serviceRequest)
        {
            this.type=type;
            this.text=text;
            this.mServiceRequest=serviceRequest;
        }
    }


