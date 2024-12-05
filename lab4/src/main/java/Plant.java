public class Plant extends Organism {
    private boolean hasFlowers;

    public Plant(String name, EcologicalRole ecologicalRole, double age, double energyLevel,
                 double reproductionRate, double minVitalTemperature, double maxVitalTemperature, boolean hasFlowers) {

        super(name, ecologicalRole, age, energyLevel, reproductionRate, minVitalTemperature, maxVitalTemperature);
        this.hasFlowers = hasFlowers;
    }

    public Plant(Plant original) {
        super(original);
        this.hasFlowers = original.hasFlowers;
    }


    @Override
    public Organism copy() {
        return new Plant(this);
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Має квітки: " + (hasFlowers ? "Так" : "Ні"));

    }

    public void acquireEnergy(SunlightLevel sunlightLevel, SoilType soilType) {
        switch (sunlightLevel) {
            case НИЗЬКИЙ:
                if ((soilType != SoilType.ПІЩАНИЙ) && (soilType != SoilType.КРЕЙДЯНИЙ) ) {

                    energyLevel += 1;
                    System.out.println(String.format("Рослина %s відновила енергію на 1 одиницю",name));
                }
                else{
                    energyLevel -= 3;
                    System.out.println(String.format("Рослина %s втратила енергію на 3 одиниці",name));
                }
            case СЕРЕДНІЙ:
                if ((soilType != SoilType.ПІЩАНИЙ) && (soilType != SoilType.КРЕЙДЯНИЙ) ) {

                    energyLevel += 5;
                    System.out.println(String.format("Рослина %s відновила енергію на 5 одиниць",name));
                }
                else{
                    energyLevel -= 3;
                    System.out.println(String.format("Рослина %s втратила енергію на 3 одиниці",name));
                }
            case ВИСОКИЙ:
                if ((soilType != SoilType.ПІЩАНИЙ) && (soilType != SoilType.КРЕЙДЯНИЙ) ) {

                    energyLevel += 10;
                    System.out.println(String.format("Рослина %s відновила енергію на 10 одиниць",name));
                }
                else{
                    energyLevel -= 6;
                    System.out.println(String.format("Рослина %s втратила енергію на 6 одиниць",name));
                }

        }


    }
}
