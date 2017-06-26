/**
 * @Author Trung Kien Nguyen
 * @CoAuthor JEremy Hilliker
 * @ID     100284963
 * @Course CPSC1181 - 002
 * @Prof   Jeremy Hilliker
 * @version 1.0
 * @Date   June 9th, 2017
 * @Assignment 06 : pipes and filters
 * 
 * TrimFilter will remove the leading and trailing whitespace from a string.
*/
public class TrimFilter extends Filter {
  
  public TrimFilter(){
  }
  
  /**
  * TrimFilter will remove the leading and trailing whitespace from a string.
  * @param s the string to apply the filter's transformation to
  * @return a string whose value is the given string,
  *   with any leading and trailing whitespace removed. AKN: Javadoc
  */
  protected String doFilter(String s) {
    return s != null ? s.trim() : null;
  }
}
