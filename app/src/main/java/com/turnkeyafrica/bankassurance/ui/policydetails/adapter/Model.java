package com.turnkeyafrica.bankassurance.ui.policydetails.adapter;



    public class Model {

        public static final int TITLE_TYPE=0;
        public static final int DETAILS_TYPE=1;

        public int type;
        public String text;
        public String text2;

        public Model(int type, String text,String text2)
        {
            this.type=type;
            this.text=text;
            this.text2=text2;
        }
    }


