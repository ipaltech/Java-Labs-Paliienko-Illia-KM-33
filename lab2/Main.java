//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/*Завантажити фургон певного обсягу вантажем на певну суму з різних сортів
кави, що можуть перебувати у різних фізичних станах (зерно, мелена, розчинна
в банках і пакетиках). Ураховувати обсяг кава разом з упаковкою. Провести
сортування товарів на основі співвідношення ціни й ваги. Знайти товар у
фургоні, що відповідає заданому діапазону якості кави.*/



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Van{
    private ArrayList<Product> cargo;
    double vanFreeVolume;
    double cargoVolume;
    double priceOfCargo;

    Van(double vanFreeVolume){
        this.vanFreeVolume = vanFreeVolume;
        this.cargo = new ArrayList<>();
        this.cargoVolume = 0;
        this.priceOfCargo = 0;
    }

    private double calculateVolume(Product[] cargo){
        double cargoVolume = 0;
        for(Product e : cargo){
            cargoVolume += e.volumeOfUnit * e.quantityOfUnits;
        }
        return cargoVolume;
    }
    private double calculateVolume(ArrayList<Product> cargo){
        double cargoVolume = 0;
        for(Product e : cargo){
            cargoVolume += e.volumeOfUnit * e.quantityOfUnits;
        }
        return cargoVolume;
    }
    private double calculatePrice(Product[] cargo){
        double priceOfCargo = 0;
        for(Product e : cargo){
            priceOfCargo += e.pricePerUnit * e.quantityOfUnits;
        }
        return priceOfCargo;
    }
    private double calculatePrice(ArrayList<Product> cargo){
        double priceOfCargo = 0;
        for(Product e : cargo){
            priceOfCargo += e.pricePerUnit * e.quantityOfUnits;
        }
        return priceOfCargo;
    }


    public void loadVan(ArrayList<Product> cargo){
        double loadingVolume = calculateVolume(cargo);
        double leftVolume = this.vanFreeVolume - loadingVolume;

        if( leftVolume >= 0 ){
            for(Product e : cargo){
                this.cargo.add(e);
            }

            this.vanFreeVolume = leftVolume;
            this.cargoVolume += loadingVolume;
            this.priceOfCargo += calculatePrice(cargo);
        }
        else{
            System.out.println("Неможливо завантажити фургон через нестачу свободного обсягу !");
        }
    }
    public void loadVan(Product...cargo){
        double loadingVolume = calculateVolume(cargo);
        double leftVolume = this.vanFreeVolume - loadingVolume;

        if( leftVolume >= 0 ){
            for(Product e : cargo){
                this.cargo.add(e);
            }

            this.vanFreeVolume = leftVolume;
            this.cargoVolume += loadingVolume;
            this.priceOfCargo += calculatePrice(cargo);
        }
        else{
            System.out.println("Неможливо завантажити фургон через нестачу свободного обсягу !");
        }
    }

    public void showCargo(){
        for(int i = 0; i < cargo.size(); i++){

            Product product = cargo.get(i);
            String type = product.getClass().getSimpleName();
            String name = product.showName();


            double price = product.pricePerUnit;
            int quantity = product.quantityOfUnits;

            System.out.println(String.format("%d. Тип: %s, Назва: %s, Ціна: %f, Кількість: %d",(i+1),type,name,price,quantity));
        }
    }
    public void sortCargoByPrice(){
        cargo.sort((c1, c2) ->
                Double.compare(c1.pricePerUnit / c1.volumeOfUnit,
                        c2.pricePerUnit / c2.volumeOfUnit));


    }

    public ArrayList<Product> findProductByQuality(double from, double to){
        ArrayList<Product> foundProducts = new ArrayList<>();
        for(Product e : cargo){
            if(from <= e.qualityOfProduct && e.qualityOfProduct <= to){
                foundProducts.add(e);
            }

        }
        return foundProducts;
    }

}

class Packing{
    double volumeOfPacking;
    TypeOfPacking packingType;
    enum TypeOfPacking{
         CAN,
        PACKAGE
    }
    Packing(TypeOfPacking packingType, double volumeOfPacking){
        this.volumeOfPacking = volumeOfPacking;
        this.packingType = packingType;
    }

}

abstract class Product{
    double pricePerUnit;
    double volumeOfUnit;
    double qualityOfProduct;
    int quantityOfUnits;

    Product(Packing packing, double pricePerUnit, int quantityOfUnits, double qualityOfProduct){
        this.pricePerUnit = pricePerUnit;
        this.volumeOfUnit = packing.volumeOfPacking;
        this.quantityOfUnits = quantityOfUnits;
        this.qualityOfProduct = qualityOfProduct;
    }
    Product(double qualityOfProduct, double pricePerUnit, double volumeOfUnit, int quantityOfUnits){
        this.pricePerUnit = pricePerUnit;
        this.volumeOfUnit = volumeOfUnit;
        this.quantityOfUnits = quantityOfUnits;
        this.qualityOfProduct = qualityOfProduct;
    }

    public abstract String showName();
}

class Coffee extends Product{
    String varietyName;

    Coffee(String varietyName, Packing packing, double pricePerUnit, int quantityOfUnits,double qualityOfProduct){
        super(packing, pricePerUnit, quantityOfUnits,qualityOfProduct);
        this.varietyName = varietyName;
    }
    public String showName(){
        return this.varietyName;
    }
}

class CoffeeBeans extends Coffee{
    CoffeeBeans(String varietyName, Packing packing, double pricePerUnit, int quantityOfUnits,double qualityOfProduct){
        super(varietyName, packing, pricePerUnit, quantityOfUnits, qualityOfProduct);
    }
}
class CoffeeGround extends Coffee{
    CoffeeGround(String varietyName, Packing packing, double pricePerUnit, int quantityOfUnits,double qualityOfProduct){
        super(varietyName, packing, pricePerUnit, quantityOfUnits, qualityOfProduct);
    }
}
class CoffeeInstant extends Coffee{
    CoffeeInstant(String varietyName, Packing packing, double pricePerUnit, int quantityOfUnits,double qualityOfProduct){
        super(varietyName, packing, pricePerUnit, quantityOfUnits, qualityOfProduct);
    }
}


class Main{
    public static ArrayList<Product>demoLoader(int quantity){
        ArrayList<Product> coffee = new ArrayList<>();
        for(int i = 0; i < quantity; i++){
            coffee.add(new CoffeeBeans(String.format("Coffee%d",i+1),new Packing(Packing.TypeOfPacking.CAN, 5+i),
                    100+i, 1 + i, 10.5 - i ));
            coffee.add(new CoffeeGround(String.format("Coffee%d%d",i+1,i+1),new Packing(Packing.TypeOfPacking.PACKAGE, 5+i),
                    100+i, 1 + i, 10.5 + 2*Math.pow(-1,i)));
            coffee.add(new CoffeeInstant(String.format("Coffee%d%d%d",i+1,i+1,i+1),new Packing(Packing.TypeOfPacking.CAN, 5+i),
                    100+i, 2 + i, 10.5 + i ));
        }
        return coffee;
    }


    public static void main(String[] args){
        Van van1 = new Van(1000);
        ArrayList<Product> coffee = demoLoader(2);
        van1.loadVan(coffee);
        van1.sortCargoByPrice();
        van1.showCargo();
        System.out.println(van1.priceOfCargo);
        van1.findProductByQuality(10.5, 20);




    }
}


