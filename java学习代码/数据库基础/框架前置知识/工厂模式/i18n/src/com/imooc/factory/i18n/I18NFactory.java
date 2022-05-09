package com.imooc.factory.i18n;

public class I18NFactory {
	//¾²Ì¬¹¤³§
	public static I18N getI18NObject(String area) {
		if(area.equals("china")) {
			return new Chinese();
		}else if(area.equals("spain")) {
			return new Spainish();
		}else if(area.equals("italy")) {
			return new Italian();
		}else {
			return null;
		}
	}
}
