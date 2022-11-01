package chainTest.demo.error;
public class SensibleException extends Exception{
	
	private static final long serialVersionUID = 1L;

	 public SensibleException(){
	   super(ExceptionTemplate.ERROR);
	 }
	 
	 public SensibleException(String messege){
	        super(messege);
	 }
	 
	 public SensibleException(Throwable cause){
	        super( cause );
	 }
	 
	 public SensibleException(String messege,Throwable cause){
	        super(messege, cause );
	 }
} 
