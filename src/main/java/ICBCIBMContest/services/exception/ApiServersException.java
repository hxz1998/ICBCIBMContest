package ICBCIBMContest.services.exception;

/**
 * API服务提供方异常 由服务提供方检查
 *
 * @author Mr.Hu
 */
public class ApiServersException extends RuntimeException{

    private String message = "API提供方服务器错误，请联系系统管理员检查";

    public ApiServersException() {
    }

    public ApiServersException(String message) {
        super(message);
        this.message = message;
    }
}
