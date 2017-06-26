/**
 * @Author Trung Kien Nguyen
 * @ID     100284963
 * @Course CPSC1181 - 002
 * @Prof   Jeremy Hilliker
 * @version 1.0
 * @Date   June 9th, 2017
 * @Assignment 06 : pipes and filters
 * 
 * Reverse caps filter normalizes the capitalized and vice-versa
*/
public class ReverseCapsFilter extends Filter{ 

    public ReverseCapsFilter(){
    }

    /**
     * Reverse Caps Filter normalizes the capitalized characters
     * and vice-versa
     * @param s the String to apply the transformation 
     * @return String that has been transformed by ReverseCapsFilter
     */
    protected String doFilter(String s){
        if (s == null){
            return null;
        }
        String sfilter = ""; 
        for (char i : s.toCharArray() ) { 
           sfilter += (Character.isLowerCase(i) ? Character.toUpperCase(i) : Character.toLowerCase(i));
        }
        return sfilter;
    }
}