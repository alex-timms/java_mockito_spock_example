package example

import spock.lang.Specification

// Docs: https://spockframework.org/spock/docs/1.1/all_in_one.html#PartialMockshttps://spockframework.org/spock/docs/1.1/all_in_one.html#_mocking
// https://spockframework.org/spock/docs/1.0/interaction_based_testing.html
// https://www.baeldung.com/spock-stub-mock-spy
// https://semaphoreci.com/community/tutorials/stubbing-and-mocking-in-java-with-the-spock-testing-framework#h-basic-mocking-with-spock
// https://javadoc.io/static/org.mockito/mockito-core/5.1.1/org/mockito/Mockito.html

class DemoClass1Spec extends Specification{

    DemoClass1 demoClass1 = new DemoClass1()
    
    def "testing democlass1Spec"() {
        given:"creating class object"
        DemoClass1 demoClass1 = new DemoClass1()
        when:"calling method"
        String initialString = demoClass1.getInitialStringClass1()
        then: "return expected"
        initialString == "String created during class 1 start up"
    }

    def "Example spy 1"() {
//        Spies provide the ability to wrap an existing object.
//        This means we can listen in on the conversation between the caller and the real object but retain the original object behavior.
//        Basically, Spy delegates method calls to the original object.
        given: "creating the spy"
        DemoClass1 spyDemoClass1 = Spy(DemoClass1)

        when: "calling the class get initial string"
        def initialString = spyDemoClass1.getInitialStringClass1()

        then: "change output and collect"
        1 * spyDemoClass1.getInitialStringClass1() >> "Spied output string"
        initialString == "Spied output string"

        when: "calling sum"
        def sum = spyDemoClass1.sumValues(2,3,4)

        then: "change operation and return"
        1 * spyDemoClass1.sumValues(2,3,4) >> demoClass1.subtractValues(4,3)
        sum != 9
        sum == 1
    }

    def "Example Mock 1"() {
        given: "creating and injecting the mock"
        DemoClass2 mockedDemoClass2 = Mock()
        demoClass1.demoClass2 = mockedDemoClass2
        when: "calling method in class2"
        def subtract = demoClass1.subtractValues(9,3)
        then: "change operation and return"
        1 * mockedDemoClass2.subtractValues(9, 3) >> 1
        subtract != 6
        subtract == 1
    }

    def "Example Spy 2"(){
//        We would like to know if some method of the dependent object was called with specified arguments.
//        We want to focus on the behavior of the objects and explore how they interact by looking on the method calls.
//        Mocking is a description of mandatory interaction between the objects in the test class.
        given: "Create Spy"
        DemoClass2 spiedDemoClass2 = Spy()
        when: "calling function twice"
        def firstResponse = spiedDemoClass2.divideValues(5, 0)
        def secondResponse = spiedDemoClass2.divideValues(5, 0)
        then: "change operation and return"
        2 * spiedDemoClass2.divideValues(5, 0) >>> [0, 5]  // output must be of same type
        firstResponse == 0
        secondResponse == 5
    }

    def "Example Mock 2"(){
        given: "Create Spy"
        DemoClass2 mockedDemoClass2 = Mock() // always create the mock / spy in the given, even if not being used straight away
        when:"calling divide that will error"
        def failingResponse = demoClass1.divideValuesinClass1(5, 0)
        then:"catch error"
        ArithmeticException ex = thrown(ArithmeticException)
        ex.getMessage() == "/ by zero"
        when: "calling function twice"
        demoClass1.demoClass2 = mockedDemoClass2
        def firstResponse = demoClass1.divideValuesinClass1(5, 0)
        def secondResponse = demoClass1.divideValuesinClass1(5, 0)
        then: "change operation and return"
        2 * mockedDemoClass2.divideValues(5, 0) >>> [0, 5]  // changing the number of invocations results in fail, additional check
        firstResponse == 0
        secondResponse == 5
    }

    def "Example Stub"(){
//        A stub is a controllable replacement of an existing class dependency in our tested code.
//        This is useful for making a method call that responds in a certain way.
//        When we use stub, we don't care how many times a method will be invoked.
//        Instead, we just want to say: return this value when called with this data.
        given: "Create stub"
        DemoClass2 stubedDemoClass2 = Stub(DemoClass2)
        when:
        String output_1 = demoClass1.stubExampleinClass1("hello")
        then:
        output_1 == "hello returned"
        when:
        demoClass1.demoClass2 = stubedDemoClass2
        stubedDemoClass2.stubExample("hello") >> "goodbye returned"
        String output_2 = demoClass1.stubExampleinClass1("hello")
        String output_3 = demoClass1.stubExampleinClass1("hello")
        then:
        output_2 == "goodbye returned"
        output_3 == "goodbye returned"
    }

    def "Example Stub 2"(){
        given: "Create stub"
        DemoClass2 stubedDemoClass2 = Stub(DemoClass2)
        when:
        String output_1 = demoClass1.stubExampleinClass1("hello")
        then:
        output_1 == "hello returned"
        when:
        demoClass1.demoClass2 = stubedDemoClass2
        stubedDemoClass2.stubExample(_) >> "goodbye returned"  // any agruement input
        String output_2 = demoClass1.stubExampleinClass1("hello")
        then:
        output_2 == "goodbye returned"
    }
}
