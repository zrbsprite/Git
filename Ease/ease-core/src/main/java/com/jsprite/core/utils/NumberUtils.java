package com.jsprite.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jsprite.core.exception.NumberException;

public class NumberUtils {

	private static final String DEFAULT_PATTERN = "#,###.00";
	
	/**
	 * 将数字直接截取小数点两位，不考虑进位问题，如：123.257将被格式化为123.25
	 * @param dNum 需要格式的数字
	 * @return String 格式化后的结果
	 */
	public static String defaultNumberFormat(double dNum){
		DecimalFormat formater = useRuleSplit();
		return formater.format(dNum);
	}
	
	public static String charSequenceNumberFormat(String sNum){
		DecimalFormat formater = useRuleSplit();
		BigDecimal decimal = new BigDecimal(sNum);
		return formater.format(decimal);
	}
	
	public static String roundNumberFormat(BigDecimal decimal){
		DecimalFormat formater = useRuleRound();
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
		DecimalFormat formater = useRule(RoundingMode.HALF_EVEN, 2, 2, 3, true);
		return formater.format(decimal);
	}
	
	public static String roundAndGroupNumberFormat(BigDecimal decimal, RoundingMode roundingMode, int max, int min, int groupSize){
		DecimalFormat formater = useRule(roundingMode, max, min, groupSize, true);
		return formater.format(decimal);
	}
	
	public static String customNumberFormat(BigDecimal decimal){
		return customNumberFormat(decimal, DEFAULT_PATTERN);
	}
	
	public static String customNumberFormat(BigDecimal decimal, String pattern){
		DecimalFormat formater = new DecimalFormat();
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
	
	private static DecimalFormat useRule(RoundingMode roundingMode, int maxFractionDigits, int minFractionDigits, int groupSize, boolean useGroup){
		DecimalFormat formater = new DecimalFormat();
		formater.setRoundingMode(roundingMode);
		formater.setMaximumFractionDigits(maxFractionDigits);
		formater.setMinimumFractionDigits(minFractionDigits);
		formater.setGroupingUsed(useGroup);
		if(useGroup){
			formater.setGroupingSize(groupSize);
		}
		return formater;
	}
	
	private static DecimalFormat useRuleSplit(){
		return useRule(RoundingMode.FLOOR, 2, 2, 0, false);
	}
	
	private static DecimalFormat useRuleRound(){
		return useRule(RoundingMode.HALF_EVEN, 2, 2, 0, false);
	}
	
}
