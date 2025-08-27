package ttd;


import com.ttd.framework.context.MyApplicationContext;
import com.ttd.framework.container.BeanContainer;
import com.ttd.framework.di.DependencyInjector;
import com.ttd.framework.scanner.ClassScanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class DITest {
    private BeanContainer beanContainer;
    private MyApplicationContext context;
    private DependencyInjector dependencyInjector;

    @BeforeEach
    void setUp() throws Exception {
        beanContainer = new BeanContainer();
        dependencyInjector = new DependencyInjector(beanContainer);
        context = new MyApplicationContext(beanContainer, dependencyInjector);
        context.initialize("com.ttd");
    }

    @Test
    void testClassScanner_shouldReturnAllClassesItScanned() throws Exception {
        Set<Class<?>> classes = ClassScanner.findClassesWithComponent("com.ttd");
        assertNotNull(classes, "ClassScanner returned null");
        assertEquals(classes.size(), 3);
    }
}
