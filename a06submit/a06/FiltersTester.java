import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
/**
 * @Author Trung Kien Nguyen
 * @coAuthor Jeremy Hilliker
 * @ID     100284963
 * @Course CPSC1181 - 002
 * @Prof   Jeremy Hilliker
 * @version 1.0
 * @Date   June 9th, 2017
 * @Assignment 06 : pipes and filters
 * 
 * Filter Tester Tests all the cases of Filters and pipelines.
*/

public class FiltersTester {
	public static void main(String[] args) throws IOException {
		testTrimFilter();
		testCamelCapsFilter();
		testLowerCaseFilter();
		testUpperCaseFilter();
		testReverseCapsFilter();
		testPipeLines();
		System.err.println("*** PASSED ***");
	}

	/**
	 * TestTrimFilter tests the TrimFilter
	 */
	private static void testTrimFilter() {
		TrimFilter f = new TrimFilter();
		String s;

		// typical
		s = "i am a string";	// no spaces
		assert s.equals(f.doFilter(s));
		s = "    leading";		// leading
		assert "leading".equals(f.doFilter(s));
		s = "trailing    ";		// trailing
		assert "trailing".equals(f.doFilter(s));
		s = "  leading & trailing  ";	// both
		assert "leading & trailing".equals(f.doFilter(s));
		s = "  a     b  ";				// both with gap
		assert "a     b".equals(f.doFilter(s));
		s = "\tx\t";		// tabs
		assert "x".equals(f.doFilter(s));

		// boundary
		s = "    ";		// all spaces
		assert "".equals(f.doFilter(s));

		// special
		s = "";			// empty
		assert "".equals(f.doFilter(s));

		// special
		s = null;		// null
		assert null == f.doFilter(s);
	}

	/**
	 * testCamelCapsFilter tests the CamelCapsFilters
	 */
	private static void testCamelCapsFilter(){
		CamelCapsFilter f = new CamelCapsFilter(); 
		String s; 
		
		//special
		s=null; 
		assert f.doFilter(s) == null;

		//boundary
		s ="" ;
		assert "".equals(f.doFilter(s));
		s = "     "; 
		assert "     ".equals(f.doFilter(s));

		//typical
		s = "1 two Three FouR"; 
		assert "1 TwO ThReE FoUr".equals(f.doFilter(s));
		s = "    a  AA  ";
		assert ("    A  aA  ").equals(f.doFilter(s));
		s = "x";
		assert "X".equals(f.doFilter(s));
		s = " X"; 
		assert " x".equals(f.doFilter(s));

		//special character
		s = "$ ONe liNE2 .. "; 
		assert "$ OnE LiNe2 .. ".equals(f.doFilter(s));
		s = "..";
		assert "..".equals(f.doFilter(s));
	}

	/**
	 * testReverseCapsFilter tests the ReverseCapsFilter
	 */
	private static void testReverseCapsFilter(){ 
		ReverseCapsFilter f = new ReverseCapsFilter();
		String s ; 
		
		//special
		s = null; 
		assert (null == f.doFilter(s)); 

		//boundary
		s = ""; 
		assert "".equals(f.doFilter(s)); 
		s = "   "; 
		assert "   ".equals(f.doFilter(s));

		//typical
		s = "this is a string"; 
		assert "THIS IS A STRING".equals(f.doFilter(s));
		s= "THIS IS IN CAPS"; 
		assert "this is in caps".equals(f.doFilter(s));
		s= "tHiS IS a Line"; 
		assert "ThIs is A lINE".equals(f.doFilter(s));
		s= "  a  A  "; 
		assert "  A  a  ".equals(f.doFilter(s));
		s = "abcDEF 123 "; 
		assert "ABCdef 123 ".equals(f.doFilter(s)); 

		//special character
		s = "^ oNe Line .. "; 
		assert "^ OnE lINE .. ".equals(f.doFilter(s));
		s = "..";
		assert "..".equals(f.doFilter(s));
	}

	/**
	 * tests the LowerCase Filter
	 */
	private static void testLowerCaseFilter(){ 
		LowerCaseFilter f = new LowerCaseFilter(); 
		String s; 

		//special
		s = null; 
		assert null == f.doFilter(s);
		
		//boundary
		s = "";
		assert "".equals(f.doFilter(s));
		s = "     "; 
		assert "     ".equals(f.doFilter(s));
		
		//typical
		s = "a normal line"; 
		assert "a normal line".equals(f.doFilter(s));
		s = "A BIG LINE";
		assert "a big line".equals(f.doFilter(s));
		s = " a MixEd LinE  "; 
		assert " a mixed line  ".equals(f.doFilter(s));

		//special character
		s = "! oNe Line .. "; 
		assert "! one line .. ".equals(f.doFilter(s));
		s = ".,";
		assert ".,".equals(f.doFilter(s));
		s = "1X*";
		assert "1x*".equals(f.doFilter(s));
		s = "x"; 
		assert "x".equals(f.doFilter(s));
	}

	/** 
	 * tests the UpperCase Filter
	 */
	private static void testUpperCaseFilter(){ 
		UpperCaseFilter f = new UpperCaseFilter();
		String s ; 

		//special
		s = null; 
		assert null == f.doFilter(s);

		//boundary
		s = "";
		assert "".equals(f.doFilter(s));
		s = "     "; 
		assert "     ".equals(f.doFilter(s));

		//typical
		s = "a normal line"; 
		assert "A NORMAL LINE".equals(f.doFilter(s));
		s = "A BIG LINE";
		assert "A BIG LINE".equals(f.doFilter(s));
		s = " a MixEd LinE  "; 
		assert " A MIXED LINE  ".equals(f.doFilter(s));

		//special character
		s = ". oNe Line .! "; 
		assert ". ONE LINE .! ".equals(f.doFilter(s));
		s = ",.";
		assert ",.".equals(f.doFilter(s));
		s = "X(";
		assert "X(".equals(f.doFilter(s));
		s = "x"; 
		assert "X".equals(f.doFilter(s));
	}

	/**
	 * tests different pipelines including Filters+ Sink
	 */
	private static void testPipeLines() throws IOException{

		//first pipe : CamelCaps -> Trim ->  ReverseCaps -> Sink
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Filter f1 = new CamelCapsFilter().add(new TrimFilter())
										 .add(new ReverseCapsFilter())
										 .add(new PrintStreamSink(new PrintStream(baos)));
				
		f1.filter("      this is a line  ");
		assert "tHiS Is a lInE".equals(baos.toString());

		//second pipe : first pipe + ReverseCapsFilter
		baos = new ByteArrayOutputStream(); 
		Filter f2 = new CamelCapsFilter().add(new TrimFilter())
										 .add(new ReverseCapsFilter().add(new ReverseCapsFilter()))
										 .add(new PrintStreamSink(new PrintStream(baos))); 
		
		f2.filter("      this is a line  ");
		assert "ThIs iS A LiNe".equals(baos.toString());

		//third pipe : UpperCaps -> Trim -> Reverse Caps -> Sink
		baos = new ByteArrayOutputStream(); 
		Filter f3 = new UpperCaseFilter().add(new TrimFilter())
										 .add(new ReverseCapsFilter())
										 .add(new PrintStreamSink(new PrintStream(baos)));

		f3.filter("  a    normal LINE 911"); 
		assert ("a    normal line 911").equals(baos.toString());

		//fourth pipe: third pipe -> UpperCaseFilter
		baos = new ByteArrayOutputStream(); 
		Filter f4 = new UpperCaseFilter().add(new TrimFilter())
										 .add(new ReverseCapsFilter())
										 .add(new UpperCaseFilter())
										 .add(new PrintStreamSink(new PrintStream(baos))); 

		f4.filter("  ,a   normal LINE "); 
		assert(",A   NORMAL LINE").equals(baos.toString());

		//fifth pipe : LowerCaps -> Revercaps -> Trim -> Sink
		baos = new ByteArrayOutputStream(); 
		Filter f5 = new LowerCaseFilter().add(new ReverseCapsFilter().add(new TrimFilter()))
										 .add(new PrintStreamSink(new PrintStream(baos)));
		
		f5.filter("  12 ThIs Line IS MixEd !! "); 
		assert ("12 THIS LINE IS MIXED !!").equals(baos.toString());

		//sixth pipe: fifth pipe, test Boundary
		baos = new ByteArrayOutputStream(); 
		Filter f6 = new LowerCaseFilter().add(new ReverseCapsFilter().add(new TrimFilter()))
										 .add(new PrintStreamSink(new PrintStream(baos)));
		
		f6.filter("");
		assert "".equals(baos.toString());
									
	}

	static {
		boolean assertsEnabled = false;
		assert assertsEnabled = true; // Intentional side effect!!!
		if (!assertsEnabled) {
			throw new RuntimeException("Asserts must be enabled!!! java -ea");
		}
    }
}
