package com.turnkeyafrica.turnkeybankassurance.ui.insuranceproducts.adapter;



    public class InsuranceProductsModel {

        public static final int TITLE_TYPE=0;
        public static final int DETAILS_TYPE=1;

        public int type;
        public String text;
        public int paddingTop;


        public InsuranceProductsModel(int type, String text, int paddingTop)
        {
            this.type=type;
            this.text=text;
            this.paddingTop = paddingTop;
        }
    }


