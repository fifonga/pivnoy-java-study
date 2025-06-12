package ttv.poltoraha.pivka.exception;


// Чтобы выводить красиво status message в постман, пиздец постман удобнее чем curl использовать
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }
}
