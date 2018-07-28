package ICBCIBMContest.services.exception;

public class ServerException extends RuntimeException{

    private String message = "服务器内部异常，请联系系统管理员检查";

    public ServerException() {
    }

    public ServerException(String message) {
        super(message);
        this.message = message;
    }


}
