package ICBCIBMContest.util;

import java.util.Properties;

/**
 * 属性工厂接口
 * 获取属性组 Properties
 * 获取指定名称的属性值
 *
 * @author Mr.Hu
 */
public interface PropertiesFactory {

    /**
     * 获取从配置文件中直接读取到的 Properties 对象
     * @return Properties 对象
     */
    Properties getProperty();

    /**
     * 根据所需属性名称进行直接获取属性值
     * @param name 所需属性名称
     * @return 属性值
     */
    String getPropertyValue(String name);
}
