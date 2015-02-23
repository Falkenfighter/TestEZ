# TestEZ
A simple testing framework

The thought behind TestEZ is to provide a minimalist testing framework that is able to handle both unit testing (atomic) and feature/integration testing (dependent).

A lot of the current and planned features are derived from both JUnit and TestNG. The goal is to build upon the success of both of these great frameworks and provide a simpler testing experience.

I have a lot more work to put into this but for a quick example of where this framework is headed take a look at the unit tests.

## Usage

### Lambda Test


// Single line test

    Unit testName = () -> testBody;

// Multi line test

    Unit testName = () -> {
        testBody;
    };

// Short hand initialization test

    Unit testName = Class::new;

### Function Test

    @Test
    public void testName() {
        testBody
    }

### Annotations

#### @Test
Note: Most of the annotation functionality is still under active development! (2/23/2015)
The test annotation has the following functions:
1) dependsOn(); DependsOn takes a String[] of method, class, or package names which must complete before this test will execute.

    @Test(dependsOn = {"methodName", "className", "packageName"})

2) saveReturn(); If saveReturn is 'true' the value will be stored for injection into later test.

#### @Before

#### @After