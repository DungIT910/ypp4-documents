package ttd;


import com.ttd.core.applicationcontext.MyApplicationContext;
import com.ttd.core.beancontainer.BeanContainer;
import com.ttd.core.dependencyinjector.DependencyInjector;
import com.ttd.core.scanner.ClassScanner;
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
