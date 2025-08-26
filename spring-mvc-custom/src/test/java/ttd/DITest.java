package ttd;


import com.ttd.core.applicationcontext.MyApplicationContext;
import com.ttd.core.beancontainer.BeanContainer;
import com.ttd.core.dependencyinjector.DependencyInjector;
import com.ttd.core.scanner.ClassScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


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
    void testGetBeans_shouldNotNull() {
//        var data = beanContainer.getAllBeans();
//        Assertions.assertNotNull(data);
//        Collection<Object> beans = beanContainer.getAllBeans();
//        assertEquals(3, beans.size(), "Should return exactly 2 beans");
//        assertTrue(beans.stream().anyMatch(UserRepository.class::isInstance),
//                "Should contain UserRepositoryImpl");
//        assertTrue(beans.stream().anyMatch(UserService.class::isInstance),
//                "Should contain UserServiceImpl");
//        assertNotNull(beanContainer.getBeanByName("unknownBean"));
    }

    @Test
    void testClassScanner_shouldReturnAllClassesItScanned() throws Exception {
        Set<Class<?>> classes = ClassScanner.findClassesWithComponent("com.ttd");
        assertNotNull(classes, "ClassScanner returned null");
        assertEquals(classes.size(), 3);
    }

//
//    @Test
//    void testInjectDependencies_shouldInjectSuitableDependencies() {
//        UserService userService = (UserService) context.getBeanByName("userServiceImpl"); // Giả định bean name
//
//        UserRepository userRepository = userService.getUserRepository();
//        assertNotNull(userRepository, "UserRepository should be injected");
//
//        assertEquals(UserRepositoryImpl2.class, userRepository.getClass(),
//                "UserRepository should be of type UserRepositoryImpl");
//    }
}
