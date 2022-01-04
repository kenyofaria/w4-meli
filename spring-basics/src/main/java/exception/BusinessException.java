package exception;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = -88237426027806218L;

	public BusinessException(String mensagem) {
		super(mensagem);
	}
}
