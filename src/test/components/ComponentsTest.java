package test.components;

import com.sine.yys.skill.model.AttackInfoImpl;
import test.components.base.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ComponentsTest {
    static {
        if (System.getProperty("java.util.logging.config.file") == null)
            System.setProperty("java.util.logging.config.file", "tests/logging.properties");
    }

    public static void main(String[] args) {
        AttackInfoImpl._float = false;
        Logger log = Logger.getLogger(ComponentsTest.class.getName());
        log.setLevel(Level.INFO);
        int count = 0, fail = 0;
        for (TestFactory.Creator creator : TestFactory.get("")) {
            Test test = creator.create();
            try {
                test.test();
                log.fine(test.getClass().getSimpleName() + " pass");
            } catch (AssertionError e) {
                log.severe(test.getClass().getSimpleName() + " failed: " + e.getMessage());
                fail++;
            }
            count++;
        }
        log.info(fail + "/" + count + " tests failed");
    }
}
