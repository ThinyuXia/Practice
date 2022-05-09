package com.imooc.factory;

import com.imooc.factory.i18n.I18N;
import com.imooc.factory.i18n.I18NFactory;

public class Software {
	public static void main(String[] args) {
		I18N i18n = I18NFactory.getI18NObject("spain");
		System.out.println(i18n.getTitle());
	}
}
