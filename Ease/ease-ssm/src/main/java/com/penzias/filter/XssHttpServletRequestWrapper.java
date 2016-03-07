package com.penzias.filter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
    }

    /** 
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/> 
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/> 
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖 
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * 对页面传入的所有值进行过滤
     * @see javax.servlet.ServletRequestWrapper#getParameterMap()
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> paramMap = super.getParameterMap();
        Set<String> keySet = paramMap.keySet();
        for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            String[] str = paramMap.get(key);
            for (int i = 0; i < str.length; i++) {
                str[i] = xssEncode(str[i]);
            }
        }
        return paramMap;
    }

    /** 
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/> 
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/> 
     * getHeaderNames 也可能需要覆盖 
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /** 
     * 将容易引起xss漏洞的半角字符直接替换成全角字符 
     *  
     * @param s 
     * @return 
     */
    private static String xssEncode(String value) {
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

    /** 
     * 获取最原始的request 
     *  
     * @return 
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /** 
     * 获取最原始的request的静态方法 
     *  
     * @return 
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }
        return req;
    }
}