package ICBCIBMContest.services.exception;

/**
 * 参数异常 由调用者检查
 *
 * @author Mr.Hu
 */
public class ParamEmptyException extends RuntimeException {

    private String message = "参数不完整，请检查必选参数是否完整";

    public ParamEmptyException() {
    }

    public ParamEmptyException(String message) {
        super(message);
        this.message = message;
    }

}
