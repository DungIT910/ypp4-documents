package com.ttd;

import com.ttd.applicationcontext.MyApplicationContext;
import com.ttd.beancontainer.BeanContainer;
import com.ttd.demo.UserRepository;
import com.ttd.demo.UserService;
import com.ttd.demo.impl.UserRepositoryImpl2;
import com.ttd.dependencyinjector.DependencyInjector;
import com.ttd.scanner.ClassScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DITest {
    private BeanContainer beanContainer;
    private MyApplicationContext context;

    @BeforeEach
    void setUp() throws Exception {
        beanContainer = new BeanContainer();
        DependencyInjector dependencyInjector = new DependencyInjector(beanContainer);
        context = new MyApplicationContext(beanContainer, dependencyInjector);
        context.initialize("com.ttd");
    }

    @Test
    void testGetBeans_shouldNotNull() {
        Collection<Object> beans = beanContainer.getAllBeans();
        Assertions.assertNotNull(beans);
        assertEquals(3, beans.size(), "Should return exactly 2 beans");
        assertTrue(beans.stream().anyMatch(UserRepository.class::isInstance),
                "Should contain UserRepositoryImpl");
        assertTrue(beans.stream().anyMatch(UserService.class::isInstance),
                "Should contain UserServiceImpl");
        assertNotNull(beanContainer.getBeanByName("unknownBean"));
    }

    @Test
    void testClassScanner_shouldReturnAllClassesItScanned() throws Exception {
        Set<Class<?>> classes = ClassScanner.findClassesWithComponent("com.ttd");
        assertNotNull(classes, "ClassScanner returned null");
        assertEquals(3, classes.size());
    }


    @Test
    void testInjectDependencies_shouldInjectSuitableDependencies() {
        UserService userService = (UserService) context.getBeanByName("userServiceImpl"); // Giả định bean name

        UserRepository userRepository = userService.getUserRepository();
        assertNotNull(userRepository, "UserRepository should be injected");

        assertEquals(UserRepositoryImpl2.class, userRepository.getClass(),
                "UserRepository should be of type UserRepositoryImpl");
    }
}
