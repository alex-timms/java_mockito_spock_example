package example;

public class DemoClass2 {

    String initialStringClass2 = "String created during class 1 start up";

    public String getInitialStringClass2() {
        return initialStringClass2;
    }

    public void setInitialStringClass2(String initialStringClass2) {
        this.initialStringClass2 = initialStringClass2;
    }

    public int subtractValues(int firstValue, int secondValue){
        return firstValue - secondValue;
    }

    public int divideValues(int firstValue, int secondValue){
        return firstValue / secondValue;
    }

    public String stubExample(String input){
        return input + " returned";
    }
}
