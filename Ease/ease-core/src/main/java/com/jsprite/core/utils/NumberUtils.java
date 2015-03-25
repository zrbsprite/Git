package com.jsprite.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jsprite.core.exception.NumberException;

public class NumberUtils {

	private static DecimalFormat formater;
	
	private static final String DEFAULT_PATTERN = "#,###.00";
	
	static{
		formater = new DecimalFormat();
	}
	
	/**
	 * 将数字直接截取小数点两位，不考虑进位问题，如：123.257将被格式化为123.25
	 * @param dNum 需要格式的数字
	 * @return String 格式化后的结果
	 */
	public static String defaultNumberFormat(double dNum){
		useRuleSplit();
		return formater.format(dNum);
	}
	
	public static String charSequenceNumberFormat(String sNum){
		useRuleSplit();
		BigDecimal decimal = new BigDecimal(sNum);
		return formater.format(decimal);
	}
	
	public static String roundNumberFormat(BigDecimal decimal){
		useRuleRound();
		return formater.format(decimal);
	}
	
	public static String roundCharSequenceNumberFormat(String sNum) throws NumberException{
		if(isValidNumber(sNum)){
			BigDecimal decimal = new BigDecimal(sNum);
			return roundNumberFormat(decimal);
		}else{
			throw new NumberException();
		}
	}
	
	public static String roundAndGroupNumberFormat(BigDecimal decimal){
		useRule(RoundingMode.HALF_EVEN, 2, 2, 3, true);
		return formater.format(decimal);
	}
	
	public static String roundAndGroupNumberFormat(BigDecimal decimal, RoundingMode roundingMode, int max, int min, int groupSize){
		useRule(roundingMode, max, min, groupSize, true);
		return formater.format(decimal);
	}
	
	public static String customNumberFormat(BigDecimal decimal){
		return customNumberFormat(decimal, DEFAULT_PATTERN);
	}
	
	public static String customNumberFormat(BigDecimal decimal, String pattern){
		formater.applyPattern(pattern);
		return customNumberFormat(decimal, formater);
	}
	
	public static String customNumberFormat(BigDecimal decimal, NumberFormat format){
		return format.format(decimal);
	}
	
	public static boolean isValidNumber(String oNum){
		Pattern pattern = Pattern.compile("([0-9]+\\.?[0-9]*)|([0-9]*\\.?[0-9]+)");
		Matcher matcher = pattern.matcher(oNum);
		return matcher.matches();
	}
	
	private static void useRule(RoundingMode roundingMode, int maxFractionDigits, int minFractionDigits, int groupSize, boolean useGroup){
		formater.setRoundingMode(roundingMode);
		formater.setMaximumFractionDigits(maxFractionDigits);
		formater.setMinimumFractionDigits(minFractionDigits);
		formater.setGroupingUsed(useGroup);
		if(useGroup){
			formater.setGroupingSize(groupSize);
		}
	}
	
	private static void useRuleSplit(){
		useRule(RoundingMode.FLOOR, 2, 2, 0, false);
	}
	
	private static void useRuleRound(){
		useRule(RoundingMode.HALF_EVEN, 2, 2, 0, false);
	}
	
}
