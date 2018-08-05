package ICBCIBMContest.model;

/**
 * API 传输对象一般实现类
 *
 * 封装了 状态码    status
 *       数据域    data
 *       消息域    msg
 *
 * @param <T> 数据填充域泛型接口
 * @author Mr.Hu
 */
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

}
