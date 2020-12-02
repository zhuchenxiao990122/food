package com.xw.project.util;

import java.util.List;

import com.timevale.esign.result.account.LoginResult;
import com.timevale.esign.result.account.SealListResult;
import com.timevale.esign.result.account.SealResult;
import com.timevale.esign.result.file.SignPDFResult;
import com.timevale.esign.sdk.account.AccountService;
import com.timevale.esign.sdk.account.AccountServiceImpl;
import com.timevale.esign.sdk.file.LocalFileService;
import com.timevale.esign.sdk.file.LocalFileServiceImpl;

import esign.bean.PosBean;

public class SignHelper {

	/***
	 * 第三方应用登录（用户登录）
	 * 
	 * @param sealCode 老的电子印章系统的appkey（云证书序列号），新印章的印章编码
	 * @return
	 */
	public static String doAppLogin(String sealCode) {
		AccountService accountService = new AccountServiceImpl();
		// 第三方应用登录实体类
		LoginResult loginResult = accountService.appLogin(sealCode);
		// 用户编号
		String accountId = null;
		if (0 != loginResult.getErrCode()) {
			System.out.println("登录失败：errCode = " + loginResult.getErrCode() + ", Msg = " + loginResult.getMsg());
		} else {
			accountId = loginResult.getAccountId();
			System.out.println("用户登录成功：accountId = " + accountId);
		}
		return accountId;
	}

	/***
	 * 
	 * @param accountId
	 * @return
	 */
	public static int getSealInfo(String accountId) {
		AccountService accountService = new AccountServiceImpl();
		// 获取印章列表
		SealListResult sealListResult = accountService.getSealList(accountId, null, null, 0);
		List<SealResult> seals = sealListResult.getSeals();
		SealResult sealResult;
		// 印章个数
		int sealCount = seals.size();
		// 印章sealId
		int sealId = 0;
		for (int i = 0; i < sealCount; i++) {
			sealResult = (SealResult) seals.get(i);
			int isDefaultSeal = sealResult.getIsDefault();
			String defaultSealFlag = "0-表示非默认印章";
			if (1 == isDefaultSeal) {
				defaultSealFlag = "1-表示默认印章";
				// 演示Demo中仅返回默认印章的sealId用于后续流程使用
				sealId = sealResult.getSealId();
			}
			System.out.println("第 " + i + 1 + "个印章的sealId = " + sealResult.getSealId() + " ,印章名称 = "
					+ sealResult.getName() + "是否默认印章: " + defaultSealFlag);
		}
		return sealId;
	}
	/**
	 * 
	 * @param srcPdfPath
	 * @param signedPdfPath
	 * @param accountId
	 * @param sealId
	 */
	public static int doSign(String srcPdfPath, String signedPdfPath, String accountId, int sealId) {
		// 定义盖章位置信息
		PosBean posBean = new PosBean();

		// 签署类型 1-单页签章，2-多页，3-骑缝，4-关键字
		int signType = 4;

		if (4 == signType) {
			// 定义盖章类型:1-关键字盖章
			posBean.setPosType("1");
			// 关键字，如果设置了坐标盖章，不要这个值
			posBean.setKey("浙江省红十字会");
			// 签署位置X坐标,关键字时以关键字为原点,控制左右移动距离
			posBean.setPosX(50);
			// 签署位置Y坐标,关键字时以关键字为原点,控制上下移动距离
			posBean.setPosY(0);
		}

		if (1 == signType) {
			// 定义盖章类型:0-坐标盖章；
			posBean.setPosType("0");
			// 签署页码
			posBean.setPosPage("1");
			// 签署位置X坐标,以pdf页面的左下角作为原点,控制左右移动距离
			posBean.setPosX(300);
			// 签署位置Y坐标,以pdf页面的左下角作为原点,控制上下移动距离
			posBean.setPosY(180);
		}

		LocalFileService localfileservice = new LocalFileServiceImpl();
		SignPDFResult signPDFResult = localfileservice.localSignPDF(accountId, null, srcPdfPath, signedPdfPath, sealId,
				signType, posBean);
        int errCode = signPDFResult.getErrCode();
        if (0 != errCode) {
			System.out.println("签署失败：errCode = " + errCode + ", Msg = " + signPDFResult.getMsg());
		} else {
			System.out.println("签署成功：errCode = " + errCode + ", Msg = " + signPDFResult.getMsg());
		}
        return errCode;
	}
}
