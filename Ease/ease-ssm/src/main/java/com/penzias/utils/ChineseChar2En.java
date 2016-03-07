package com.penzias.utils;

import java.io.UnsupportedEncodingException;

/**
 * <b>描述：取得给定汉字串的首字母串,即声母串 </b> <br/>
 * <b>作者：</b>Bob <br/>
 * <b>修改日期：</b>2016年1月14日 - 下午5:47:20<br/>
 */
public class ChineseChar2En {

	/**
	 * 取得给定汉字串的首字母串,即声母串 Title: ChineseCharToEn
	 * @date 2004-02-19 注：只支持GB2312字符集中的汉字
	 */
	private final int[] li_SecPosValue = {1601,1637,1833,2078,2274,2302,2433,2594,2787,3106,3212,
			3472,3635,3722,3730,3858,4027,4086,4390,4558,4684,4925,5249,5590};
	private final String[] lc_FirstLetter = {"a","b","c","d","e","f","g","h","j","k","l","m","n",
			"o","p","q","r","s","t","w","x","y","z"};

	/**
	 * <b>作者:</b> Bob<br/>
	 * <b>修改时间：</b>2016年1月14日 - 下午6:04:22<br/>
	 * <b>功能说明：</b>取得给定汉字串的首字母串,即声母串	<br/>
	 * @param str
	 * @return
	 */
	public String getAllFirstLetter(String str){

		if(str == null || str.trim().length() == 0){
			return "";
		}

		String _str = "";
		for(int i = 0;i < str.length();i++){
			_str = _str + this.getFirstLetter(str.substring(i,i + 1));
		}

		return _str;
	}

	/**
	 * <b>作者:</b> Bob<br/>
	 * <b>修改时间：</b>2016年1月14日 - 下午6:02:32<br/>
	 * <b>功能说明：</b>取得给定汉字的首字母（大写）,即声母	<br/>
	 * @param chinese
	 * @return
	 */
	public String getFirstUpperLetter(String chinese){
		return getFirstLetter(chinese).toUpperCase();
	}
	
	/**
	 * <b>作者:</b> Bob<br/>
	 * <b>修改时间：</b>2016年1月14日 - 下午6:02:32<br/>
	 * <b>功能说明：</b>取得给定汉字的首字母,即声母	<br/>
	 * @param chinese
	 * @return
	 */
	public String getFirstLetter(String chinese){

		if(chinese == null || chinese.trim().length() == 0){
			return "";
		}
		chinese = this.conversionStr(chinese,"GB2312","ISO8859-1");

		if(chinese.length() > 1) // 判断是不是汉字
		{
			int li_SectorCode = (int) chinese.charAt(0); // 汉字区码
			int li_PositionCode = (int) chinese.charAt(1); // 汉字位码
			li_SectorCode = li_SectorCode - 160;
			li_PositionCode = li_PositionCode - 160;
			int li_SecPosCode = li_SectorCode * 100 + li_PositionCode; // 汉字区位码
			if(li_SecPosCode > 1600 && li_SecPosCode < 5590){
				for(int i = 0;i < 23;i++){
					if(li_SecPosCode >= li_SecPosValue[i] && li_SecPosCode < li_SecPosValue[i + 1]){
						chinese = lc_FirstLetter[i];
						break;
					}
				}
			} else // 非汉字字符,如图形符号或ASCII码
			{
				chinese = this.conversionStr(chinese,"ISO8859-1","GB2312");
				chinese = chinese.substring(0,1);
			}
		}

		return chinese;
	}

	/**
	 * 字符串编码转换
	 * @param str
	 *            要转换编码的字符串
	 * @param charsetName
	 *            原来的编码
	 * @param toCharsetName
	 *            转换后的编码
	 * @return 经过编码转换后的字符串
	 */
	private String conversionStr(String str,String charsetName,String toCharsetName){

		try{
			str = new String(str.getBytes(charsetName),toCharsetName);
		} catch (UnsupportedEncodingException ex){
			System.out.println("字符串编码转换异常：" + ex.getMessage());
		}
		return str;
	}
}