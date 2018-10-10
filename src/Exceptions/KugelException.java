package Exceptions;

public class KugelException extends RuntimeException {

	public KugelException() {
		super("Es ist ein Fehler mit den Kugeln aufgetreten.");
	}

	public KugelException(String message) {
		super(message);
	}

}
