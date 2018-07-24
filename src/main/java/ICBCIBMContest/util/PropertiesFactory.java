package ICBCIBMContest.util;

import java.util.Properties;

public interface PropertiesFactory {

    Properties getProperty();

    String getPropertyValue(String name);
}
