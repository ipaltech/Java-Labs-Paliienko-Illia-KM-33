abstract class Organism {
    public String name;
    public EcologicalRole ecologicalRole;
    public double age;
    public double energyLevel;
    public double reproductionRate;
    public double minVitalTemperature;
    public double maxVitalTemperature;

    public Organism(String name, EcologicalRole ecologicalRole, double age, double energyLevel,
                    double reproductionRate, double minVitalTemperature, double maxVitalTemperature) {
        this.name = name;
        this.ecologicalRole = ecologicalRole;
        this.age = age;
        this.energyLevel = energyLevel;
        this.reproductionRate = reproductionRate;
        this.minVitalTemperature = minVitalTemperature;
        this.maxVitalTemperature = maxVitalTemperature;

    }

    public Organism(Organism original) {
        this.name = original.name;
        this.ecologicalRole = original.ecologicalRole;
        this.age = original.age;
        this.energyLevel = original.energyLevel;
        this.reproductionRate = original.reproductionRate;
        this.minVitalTemperature = original.minVitalTemperature;
        this.maxVitalTemperature = original.maxVitalTemperature;
    }

    public void showDetails() {
        System.out.println("Ім'я: " + name);
        System.out.println("Екологічна роль: " + ecologicalRole);
        System.out.println("Вік: " + age);
        System.out.println("Рівень енергії: " + energyLevel);
        System.out.println("Швидкість репродукції: " + reproductionRate);
        System.out.println("Температурний оптимум: " + minVitalTemperature + " до " + maxVitalTemperature);
    }

    public abstract Organism copy();
}
