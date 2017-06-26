/**
 * @Author Trung Kien Nguyen
 * @ID     100284963
 * @Course CPSC1181 - 002
 * @Prof   Jeremy Hilliker
 * @version 1.0
 * @Date   June 9th, 2017
 * @Assignment 06 : pipes and filters
 * 
 * LowerCaseFilter lowercases a whole String.
*/

public class LowerCaseFilter extends Filter{ 

    public LowerCaseFilter(){

    }

    /**
     * LowerCaseFilter lowercases a whole string
     * @param s The String to lowerCase 
     * @return the String that has been transformed by LowerCaseFilter
     */
    protected String doFilter(String s){
        return (s == null ? null : s.toLowerCase());
    }
}