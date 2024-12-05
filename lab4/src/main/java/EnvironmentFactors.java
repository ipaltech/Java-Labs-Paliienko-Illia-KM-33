public interface EnvironmentFactors{}; // Маркерний інтерфейс


enum ClimateType implements EnvironmentFactors {
    ТРОПІЧНИЙ,
    АРИДНИЙ,
    ПОМІРНИЙ,
    КОНТИНЕНТАЛЬНИЙ,
    ПОЛЯРНИЙ,
    ГІРСЬКИЙ
}

enum SoilType implements EnvironmentFactors{
    ПІЩАНИЙ,
    ГЛИНИСТИЙ,
    СУГЛИНКОВИЙ,
    ТОРФОВИЙ,
    КРЕЙДЯНИЙ,
    ЧОРНОЗЕМ
}

enum TerrainType implements EnvironmentFactors {
    ГІРСЬКИЙ,
    РІВНИННИЙ,
    ПАГОРБИСТИЙ,
    ПУСТЕЛЬНИЙ,
    ЛІСОВИЙ,
    ПРИБЕРЕЖНИЙ
}

enum SunlightLevel implements EnvironmentFactors {
    НИЗЬКИЙ,
    СЕРЕДНІЙ,
    ВИСОКИЙ
}

enum WaterBodyType implements EnvironmentFactors  {
    РІЧКА,
    ОЗЕРО,
    ОКЕАН,
    БОЛОТО,
    ВІДСУТНІЙ
}

enum HumidityLevel implements EnvironmentFactors {
    НИЗЬКИЙ,
    СЕРЕДНІЙ,
    ВИСОКИЙ
}

enum TemperatureRange implements EnvironmentFactors {
    ДУЖЕ_ХОЛОДНО,
    ХОЛОДНО,
    ПОМІРНО,
    ТЕПЛО,
    ЖАРКО
}





