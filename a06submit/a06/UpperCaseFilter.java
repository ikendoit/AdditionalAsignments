/**
 * @Author Trung Kien Nguyen
 * @ID     100284963
 * @Course CPSC1181 - 002
 * @Prof   Jeremy Hilliker
 * @version 1.0
 * @Date   June 9th, 2017
 * @Assignment 06 : pipes and filters
 * 
 * Upper Case Filter capitalizes the whole string
*/

public class UpperCaseFilter extends Filter{ 

    public UpperCaseFilter(){
    }

    /**
     * UpperCaseFilter capitalizes a whole string
     * @param s String to apply transformation 
     * @return String that has been capitalized.
     */
    protected String doFilter(String s){ 
        return (s == null ? null : s.toUpperCase());
    }
}