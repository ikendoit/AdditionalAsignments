

public class mathstuffs {
	public double result=0; 
	//find trapenzoidal intergral
	public static double trapen(int n,double  a , double  b  )
	{
		double result = 0 ; 
		double i = a;
		for ( int j = 0; j <= n ; j ++ )
		{
			
			if (j == 0 || j == n )
				 result+= 0.5 * (double) 39*Math.sin(i);
			else result+= 		(double) 39*Math.sin(i) ;
			
			i+=(b-a)/n;

		}
		
		return result*(b-a)/n; 
	}
	
	//find simpson intergral
	public static double simpson(int n,double a , double b  ) 
	{
		double result=0;
		for (int j =0 ; j <=n ; j ++ )
		{
			
			if ( j == 0 || j == n) result+=(double) 39*Math.sin(a+j*(b-a)/n) ;
			
			else {
				if ( j % 2 == 0 )  result+=(double) 39*Math.sin(a+j*(b-a)/n)*2;
				else 			   result+=(double) 39*Math.sin(a+j*(b-a)/n)*4;
			}
			
		}
		return result * (b-a)/(3*n);
	}
	//find middle point integral value
	public static double  mid(int n,double a , double b ) 
	{
		double result= 0 ; 
		double i = a+(b-a)/(2*n) ; 
		while ( i < b )
		{
			
			{
				result +=(double) 	39*Math.sin(i)  ;
			}
			i+=(b-a)/n;
		}
		return result*(b-a)/n ; 
	}
	public static void main(String[] args) {
		System.out.printf("simpson :  %.6f\n"   ,(double) simpson(10,0,Math.PI) );
		
		System.out.printf("trapenzoidal :  %.6f\n",(double ) trapen(10,0,Math.PI) ) ; 
		
		System.out.printf("midpoint :  %.6f\n"    ,(double) mid(10,0,Math.PI) );
	}

}
