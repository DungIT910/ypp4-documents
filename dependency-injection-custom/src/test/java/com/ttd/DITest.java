package com.ttd;

import com.ttd.beancontainer.BeanContainer;
import com.ttd.demo.UserRepository;
import com.ttd.demo.UserService;
import com.ttd.dependencyinjector.DependencyInjector;
import com.ttd.scanner.ClassScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DITest {
    private BeanContainer beanContainer;
    private DependencyInjector dependencyInjector;

    @BeforeEach
    void setUp() throws Exception {
        beanContainer = new BeanContainer();
        beanContainer.registerBean(UserService.class);
        beanContainer.registerBean(UserRepository.class);
    }

    @Test
    void testGetBeans_shouldNotNull() {
        var data = beanContainer.getAllBeans();
        Assertions.assertNotNull(data);
        Collection<Object> beans = beanContainer.getAllBeans();
        assertEquals(2, beans.size(), "Should return exactly 2 beans");
        assertTrue(beans.stream().anyMatch(bean -> bean instanceof UserRepository),
                "Should contain UserRepository");
        assertTrue(beans.stream().anyMatch(bean -> bean instanceof UserService),
                "Should contain UserService");
    }


    @Test
    void testClassScanner_shouldReturnAllClassesItScanned() throws Exception {
        Set<Class<?>> classes = ClassScanner.findClassesWithComponent("com.ttd");
        assertNotNull(classes, "ClassScanner returned null");
        assertEquals(classes.size(), 2);
    }

}
