import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EcosystemTest {
    // Перевірка на додання прототипу до екосистеми
    @Test
    public void testRegisterOrganism() {
        Ecosystem ecosystem = new Ecosystem(
                "Forest Ecosystem",
                ClimateType.ПОМІРНИЙ,
                TerrainType.ЛІСОВИЙ,
                SoilType.ЧОРНОЗЕМ,
                SunlightLevel.СЕРЕДНІЙ,
                WaterBodyType.ОЗЕРО,
                TemperatureRange.ТЕПЛО,
                HumidityLevel.ВИСОКИЙ
        );

        Animal wolf = new Animal(
                "Wolf", // Имя животного
                EcologicalRole.КОНСУМЕНТ,
                5,
                80.0,
                0.3,
                -10.0,
                35.0,
                DietType.ХИЖАК
        );
        ecosystem.registerOrganism("wolf1",wolf,EcologicalRole.КОНСУМЕНТ);

        // Перевіряєм, що організм успішно додано
        assertNotNull(ecosystem.getConsumersRegistry().get("wolf1"));
    }

    // Перевірка на коректне клонування прототипу
    @Test
    public void testCreateOrganism() {
        Ecosystem desertEcosystem = new Ecosystem(
                "Desert Ecosystem",
                ClimateType.КОНТИНЕНТАЛЬНИЙ,
                TerrainType.ПУСТЕЛЬНИЙ,
                SoilType.ПІЩАНИЙ,
                SunlightLevel.ВИСОКИЙ,
                WaterBodyType.ВІДСУТНІЙ,
                TemperatureRange.ЖАРКО,
                HumidityLevel.НИЗЬКИЙ
        );

        Plant cactus = new Plant("cactus",EcologicalRole.ПРОДУЦЕНТ,2.0, 50.0,
                0.1, 10.0,45.0,true);
        desertEcosystem.registerOrganism("cactus1", cactus, EcologicalRole.ПРОДУЦЕНТ);


        Organism clonedCactus = desertEcosystem.createOrganism("cactus1", EcologicalRole.ПРОДУЦЕНТ);

        // Перевіряєм, що клон орігіналу не дорівнює йому
        assertNotSame(cactus, clonedCactus);
    }

    // Перевірка на коректне видалення прототипу з екосистеми
    @Test
    public void testUnregisterOrganism() {
        Ecosystem forestEcosystem = new Ecosystem(
                "Forest Ecosystem",
                ClimateType.ПОМІРНИЙ,
                TerrainType.ЛІСОВИЙ,
                SoilType.ЧОРНОЗЕМ,
                SunlightLevel.СЕРЕДНІЙ,
                WaterBodyType.ОЗЕРО,
                TemperatureRange.ТЕПЛО,
                HumidityLevel.ВИСОКИЙ
        );

        Animal deer = new Animal(
                "Deer",
                EcologicalRole.КОНСУМЕНТ,
                4.0,
                70.0,
                0.2,
                -5.0,
                30.0,
                DietType.ТРАВОЇДНИЙ
        );

        forestEcosystem.registerOrganism("deer1", deer, EcologicalRole.КОНСУМЕНТ);
        forestEcosystem.unregisterOrganism("deer1", EcologicalRole.КОНСУМЕНТ);

        // Перевіряєм, що організм видалено
        assertNull(forestEcosystem.getConsumersRegistry().get("deer1"));
    }

    // Перевірка унікального методу класу Animal
    @Test
    public void testAnimalEnergyConsumption() {
        Animal lion = new Animal(
                "Lion",
                EcologicalRole.КОНСУМЕНТ,
                3.0,
                90.0,
                0.4,
                -5.0,
                40.0,
                DietType.ХИЖАК
        );

        // Перевірка, що рівень енергії, до використання методу feed(), справді 90.0 одиниць
        assertEquals(90.0, lion.energyLevel);

        // Ствоюємо коректний екзепляр їжі
        Animal meatForLion = new Animal(
                "Deer",
                EcologicalRole.КОНСУМЕНТ,
                4.0,
                70.0,
                0.2,
                -5.0,
                30.0,
                DietType.ТРАВОЇДНИЙ
        );

        lion.feed(meatForLion);

        // Перевірка на зміну рівня енергії
        assertEquals(100.0, lion.energyLevel);
    }

    // Перевірка унікального методу класу Plant
    @Test
    public void testPlantEnergyConsumption() {
        Plant tree = new Plant(
                "Oak Tree",
                EcologicalRole.ПРОДУЦЕНТ,
                10.0,
                100.0,
                0.05,
                0.0,
                35.0,
                false
        );

        // Збільшення\зменшення енергії рослини, в залежності від факторів навколишнього середовища
        tree.acquireEnergy(SunlightLevel.ВИСОКИЙ, SoilType.ЧОРНОЗЕМ);

        // Перевірка на збільшення
        assertEquals(110.0, tree.energyLevel);

        tree.acquireEnergy(SunlightLevel.ВИСОКИЙ, SoilType.ПІЩАНИЙ);

        // Перевірка на зменшення
        assertEquals(104.0, tree.energyLevel);
    }

    // Перевірка незалежності оригіналу від копії
    @Test
    public void testPrototypeIndependence() {
        Animal prototype = new Animal(
                "Original",
                EcologicalRole.КОНСУМЕНТ,
                2.0,
                50.0,
                0.2,
                5.0,
                30.0,
                DietType.ХИЖАК
        );

        Animal clone = (Animal) prototype.copy();

        // Перевірка, що об'єкти різні
        assertNotSame(prototype, clone);

        // Перевірка на співпадання атрибутів
        assertEquals(prototype.name, clone.name);
        assertEquals(prototype.age, clone.age);
        assertEquals(prototype.energyLevel, clone.energyLevel);
        assertEquals(prototype.reproductionRate, clone.reproductionRate);
        assertEquals(prototype.ecologicalRole, clone.ecologicalRole);
        assertEquals(prototype.maxVitalTemperature, clone.maxVitalTemperature);
        assertEquals(prototype.minVitalTemperature, clone.minVitalTemperature);

        // Змінюємо значення атрибутів у клону й перевіряєм, що у оригінала вони не змінились
        clone.age = 3.0;
        assertNotEquals(prototype.age, clone.age);
        clone.name = "Clone";
        assertNotEquals(prototype.name, clone.name);
        clone.energyLevel = prototype.energyLevel + 10;
        assertNotEquals(prototype.energyLevel, clone.energyLevel);

    }

    // Перевірка на коректне додання прототипів до хеш-мап, в залежності від EcologicalRole прототипів
    @Test
    public void testAddDifferentRolesPrototypes(){
        Ecosystem desertEcosystem = new Ecosystem(
                "Desert Ecosystem",
                ClimateType.КОНТИНЕНТАЛЬНИЙ,
                TerrainType.ПУСТЕЛЬНИЙ,
                SoilType.ПІЩАНИЙ,
                SunlightLevel.ВИСОКИЙ,
                WaterBodyType.ВІДСУТНІЙ,
                TemperatureRange.ЖАРКО,
                HumidityLevel.НИЗЬКИЙ
        );
        Animal hyena = new Animal(
                "Hyena",
                EcologicalRole.КОНСУМЕНТ,
                3.0,
                90.0,
                0.4,
                -5.0,
                40.0,
                DietType.ХИЖАК
        );
        Plant cactus = new Plant("cactus",
                EcologicalRole.ПРОДУЦЕНТ,
                2.0,
                50.0,
                0.1,
                10.0,
                45.0,
                true
        );
        desertEcosystem.registerOrganism("Hyena",hyena,hyena.ecologicalRole);
        desertEcosystem.registerOrganism("Cactus",cactus,cactus.ecologicalRole);

        assertNotNull(desertEcosystem.getConsumersRegistry().get("Hyena"));
        assertNull(desertEcosystem.getConsumersRegistry().get("Cactus"));
        assertNotNull(desertEcosystem.getProducersRegistry().get("Cactus"));
        assertNull(desertEcosystem.getProducersRegistry().get("Hyena"));

    }


}
