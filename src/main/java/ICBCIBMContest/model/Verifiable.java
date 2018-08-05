package ICBCIBMContest.model;

/**
 * 验证器接口
 * 实现了该接口的类可以简单进行属性验证
 */
public interface Verifiable {

    /**
     * 进行简单属性验证时需要调用的类
     *
     * @return 验证结果 当参数均正常时为 true 否则为 false
     */
    boolean verify();

}
