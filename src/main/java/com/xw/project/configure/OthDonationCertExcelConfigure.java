package com.xw.project.configure;

public class OthDonationCertExcelConfigure {

    //起始标题位置标示
    public static String PS_CWBT_ID = "捐赠时间";

    //绝对位置，包括空行，从0开始
    public static int PS_DOTYPE = 6;            //操作类型
    public static int PS_INFO = 7;              //提示信息
    public static int PS_PRESULT = 8;           //操作状态
    public static String[] materialCert =
            {
                    "donationDate",
                    "donor",
                    "donationAmount",
                    "donationProject"

            };
    public static String[] moneyCert =
            {
                    "donationDate",
                    "donor",
                    "amount",
                    "donationProject"

            };
}
