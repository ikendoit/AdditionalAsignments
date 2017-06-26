import java.io.PrintStream;

/**
 * @Author Trung Kien Nguyen
 * @ID     100284963
 * @Course CPSC1181 - 002
 * @Prof   Jeremy Hilliker
 * @version 1.0
 * @Date   June 9th, 2017
 * @Assignment 06 : pipes and filters
 * 
 * PrintStream Sink accept the input string from the previous filter
 * and print to a given print stream.
*/

public class PrintStreamSink extends Filter { 

    final PrintStream sink; 

    /**
     * Constructor of the PrintStream Sink
     * @param sink the PrintStream to give to PrintStreamSink
     */
    public PrintStreamSink(PrintStream sink){
        this.sink = sink; 
    }
    
    /**
     * PrintStreamSink writes the input to a given printStream 
     * @param s the String to write to the PrintStream
     * @return a String printed in the printStream
     */
    protected String doFilter(String s)  {
        if (s == null ){
            try {
                sink.write(null);
                return null;
            } catch (Exception e){
                return null;
            }
            
        }
    
        try{
            sink.write(s.getBytes());
            return sink.toString();
        } catch (Exception e ){
            return null;
        }
    }

    

}