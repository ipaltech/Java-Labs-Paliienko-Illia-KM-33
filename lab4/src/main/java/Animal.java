public class Animal extends Organism{
    DietType dietType;
    public Animal(String name, EcologicalRole ecologicalRole, double age, double energyLevel,
                 double reproductionRate, double minVitalTemperature, double maxVitalTemperature, DietType dietType) {

        super(name, ecologicalRole, age, energyLevel, reproductionRate, minVitalTemperature, maxVitalTemperature);
        this.dietType = dietType;
    }

    public Animal(Animal original) {
        super(original);
        this.dietType = dietType;

    }

    @Override
    public Organism copy() {
        return new Animal(this);
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println(dietType.toString());

    }
    public void feed(Organism organism){
        switch(organism.ecologicalRole){
            case РЕДУЦЕНТ:
                if((dietType == DietType.ТРАВОЇДНИЙ) || (dietType == DietType.ВСЕЇДНИЙ)){
                    energyLevel += 10;
                    System.out.println(String.format("Тварина %s відновила енергію на 10 одиниць",name));
                }
                else{
                    System.out.println("Такий тип організму тварина не споживає");
                }
            case ПРОДУЦЕНТ:
                if(dietType == DietType.ТРАВОЇДНИЙ){
                    energyLevel += 10;
                    System.out.println(String.format("Тварина %s відновила енергію на 10 одиниць",name));
                }
                else{
                    System.out.println("Такий тип організму тварина не споживає");
                }
            case КОНСУМЕНТ:
                if((dietType == DietType.ХИЖАК) || (dietType == DietType.ВСЕЇДНИЙ)){
                    energyLevel += 10;
                    System.out.println(String.format("Тварина %s відновила енергію на 10 одиниць",name));
                }
                else{
                    System.out.println("Такий тип організму тварина не споживає");
                }
        }
    }


}
