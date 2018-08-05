package ICBCIBMContest.services.exception;

/**
 * 参数非法异常 由调用者检查
 *
 * @author Mr.Hu
 */
public class ParamIllegalException extends RuntimeException {

    private String message = "参数形式非法，请检查参数是否输入有误";

    public ParamIllegalException() {
    }

    public ParamIllegalException(String message) {
        super(message);
        this.message = message;
    }
}
