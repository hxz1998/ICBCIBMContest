package ICBCIBMContest.services.exception;

public class ApiServersException extends RuntimeException{

    private String message = "API提供方服务器错误，请联系系统管理员检查";

    public ApiServersException() {
    }

    public ApiServersException(String message) {
        super(message);
        this.message = message;
    }
}
