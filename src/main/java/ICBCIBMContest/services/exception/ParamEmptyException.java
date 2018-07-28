package ICBCIBMContest.services.exception;

public class ParamEmptyException extends RuntimeException {

    private String message = "参数不完整，请检查必选参数是否完整";

    public ParamEmptyException() {
    }

    public ParamEmptyException(String message) {
        super(message);
        this.message = message;
    }

}
