package ICBCIBMContest.services.exception;

/**
 * 服务器内部异常 由系统管理员进行检查
 *
 * @author Mr.Hu
 */
public class ServerException extends RuntimeException{

    private String message = "服务器内部异常，请联系系统管理员检查";

    public ServerException() {
    }

    public ServerException(String message) {
        super(message);
        this.message = message;
    }


}
