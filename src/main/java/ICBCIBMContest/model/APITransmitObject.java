package ICBCIBMContest.model;

public class APITransmitObject<T> {

    protected String status;
    protected T data;
    protected String msg;

    public APITransmitObject(String status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "APITransmitObject{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
