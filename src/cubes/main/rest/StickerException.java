package cubes.main.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class StickerException extends RuntimeException {
	
	public StickerException() {
		
		super("Doslo je do problema sa ucitavanjem stikera");
	}
	
	public StickerException(String message) {
		
		super(message);
	}
	
	@ExceptionHandler
	public ResponseEntity<MessageResponse> handleException(Exception exception){
		
		MessageResponse response = new MessageResponse(HttpStatus.BAD_REQUEST.value(), 
				"Doslo je do greske", System.currentTimeMillis());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}

}
