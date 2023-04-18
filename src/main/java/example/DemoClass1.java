package example;

public class DemoClass1 {

    DemoClass2 demoClass2 = new DemoClass2();

    String initialStringClass1 = "String created during class 1 start up";

    public String getInitialStringClass1() {
        return initialStringClass1;
    }

    public void setInitialStringClass1(String initialStringClass1) {
        this.initialStringClass1 = initialStringClass1;
    }

    public void replaceInitialStringClass1() {
        this.initialStringClass1 = demoClass2.getInitialStringClass2();
    }

    public int sumValues(int firstValue, int secondValue, int thirdValue){
        return firstValue + secondValue + thirdValue;
    }

    public int subtractValues(int firstValue, int secondValue){
        return demoClass2.subtractValues(firstValue, secondValue);
    }

    public int divideValuesinClass1(int firstValue, int secondValue){
        return demoClass2.divideValues(firstValue, secondValue);
    }

    public String stubExampleinClass1(String input){
        return demoClass2.stubExample(input);
    }
}
