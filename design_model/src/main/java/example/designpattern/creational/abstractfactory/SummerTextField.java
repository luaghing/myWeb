package example.designpattern.creational.abstractfactory;

class SummerTextField implements TextField {
    @Override
    public void display() {
        System.out.println("显示蓝色边框文本框。");  
    }     
} 