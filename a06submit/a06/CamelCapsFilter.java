/**
 * @Author Trung Kien Nguyen
 * @ID     100284963
 * @Course CPSC1181 - 002
 * @Prof   Jeremy Hilliker
 * @version 1.0
 * @Date   June 9th, 2017
 * @Assignment 06 : pipes and filters
 * 
 * Camel caps Filter capitalizes even characters and normalizes odd characters
*/

public class CamelCapsFilter extends Filter{ 

    public CamelCapsFilter(){
        
    }

    /**
     * CamelCaps capitalizes even characters and normalizes odd characters
     * @param s String to apply Filter transformation 
     * @return String a String whose valued has been filtered by CamelCapsFilter.
     */
    protected String doFilter(String s){ 
        if (s == null){
            return null;
        }
        String sfilter =""; 
        for (int i = 0; i < s.length() ; i++) {
            sfilter += ( (i%2 ==0) ? Character.toUpperCase(s.charAt(i)) : Character.toLowerCase(s.charAt(i)));
        }
        return sfilter; 
    }
}