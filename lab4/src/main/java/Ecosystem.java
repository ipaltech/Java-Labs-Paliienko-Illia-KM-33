import java.util.HashMap;
import java.util.Map;

public class Ecosystem { // Диспетчер прототипів
    private Map<String, Organism> producersRegistry = new HashMap<>(); // Мапа продуцентів
    private Map<String, Organism> consumersRegistry = new HashMap<>(); // Мапа консументів
    private Map<String, Organism> decomposersRegistry = new HashMap<>(); // Мапа редуцентів

    public String name;
    public ClimateType climateType; // Тип клімату
    public TerrainType terrainType; // Тип рельєфу
    public SoilType soilType; // Тип ґрунту
    public SunlightLevel sunlightLevel; // Рівень сонячного світла
    public WaterBodyType waterBodyType; // Тип водойми
    public TemperatureRange temperatureRange; // Температурний діапазон
    public HumidityLevel humidityLevel; // Рівень вологості

    public Ecosystem(String name, ClimateType climateType, TerrainType terrainType, SoilType soilType,
                     SunlightLevel sunlightLevel, WaterBodyType waterBodyType, TemperatureRange temperatureRange,
                     HumidityLevel humidityLevel) {
        this.name = name;
        this.climateType = climateType;
        this.terrainType = terrainType;
        this.soilType = soilType;
        this.sunlightLevel = sunlightLevel;
        this.waterBodyType = waterBodyType;
        this.temperatureRange = temperatureRange;
        this.humidityLevel = humidityLevel;
    }


    public void registerOrganism(String key, Organism organism, EcologicalRole role) {
        switch (role) {
            case ПРОДУЦЕНТ:
                producersRegistry.put(key, organism);
                break;
            case КОНСУМЕНТ:
                consumersRegistry.put(key, organism);
                break;
            case РЕДУЦЕНТ:
                decomposersRegistry.put(key, organism);
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
    public void unregisterOrganism(String key, EcologicalRole role) {
        switch (role) {
            case ПРОДУЦЕНТ:
                if (!producersRegistry.containsKey(key)) {
                    throw new IllegalArgumentException("Немає такого прототипу: " + key);
                }
                producersRegistry.remove(key);
                break;
            case КОНСУМЕНТ:
                if (!consumersRegistry.containsKey(key)) {
                    throw new IllegalArgumentException("Немає такого прототипу: " + key);
                }
                consumersRegistry.remove(key);
                break;
            case РЕДУЦЕНТ:
                if (!decomposersRegistry.containsKey(key)) {
                    throw new IllegalArgumentException("Немає такого прототипу:  " + key);
                }
                decomposersRegistry.remove(key);
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    public Organism createOrganism(String key, EcologicalRole role) {
        switch (role) {
            case ПРОДУЦЕНТ:
                if (!producersRegistry.containsKey(key)) {
                    throw new IllegalArgumentException("Немає такого прототипу: " + key);
                }
                return producersRegistry.get(key).copy();
            case КОНСУМЕНТ:
                if (!consumersRegistry.containsKey(key)) {
                    throw new IllegalArgumentException("Немає такого прототипу: " + key);
                }
                return consumersRegistry.get(key).copy();
            case РЕДУЦЕНТ:
                if (!decomposersRegistry.containsKey(key)) {
                    throw new IllegalArgumentException("Немає такого прототипу:  " + key);
                }
                return decomposersRegistry.get(key).copy();
            default:
                throw new IllegalArgumentException("Невизначена роль: " + role);
        }
    }

    public Map<String, Organism> getProducersRegistry() {
        return producersRegistry;
    }
    public Map<String, Organism> getConsumersRegistry() {
        return consumersRegistry;
    }
    public Map<String, Organism> getDecomposersRegistry() {
        return decomposersRegistry;
    }
    public void showOrganisms() {
        System.out.println("Продуценти:");
        producersRegistry.values().forEach(System.out::println);

        System.out.println("Консументи:");
        consumersRegistry.values().forEach(System.out::println);

        System.out.println("Редуценти:");
        decomposersRegistry.values().forEach(System.out::println);
    }

}

