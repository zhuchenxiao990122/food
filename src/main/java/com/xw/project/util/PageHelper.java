package com.xw.project.util;

import com.timevale.esign.result.account.LoginResult;
import com.timevale.esign.result.account.SealListResult;
import com.timevale.esign.result.account.SealResult;
import com.timevale.esign.result.file.SignPDFResult;
import com.timevale.esign.sdk.account.AccountService;
import com.timevale.esign.sdk.account.AccountServiceImpl;
import com.timevale.esign.sdk.file.LocalFileService;
import com.timevale.esign.sdk.file.LocalFileServiceImpl;
import esign.bean.PosBean;

import java.util.List;

public class PageHelper {

    private int current;

    private int size;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
