
public class MainClass {
	static Parser weatherParther;
	static Parser hazeParther;
	
	public static void main(String[] args) {
		weatherParther = (Parser) new WeatherParser();
		hazeParther = (Parser) new HazeParser();
		
		process (weatherParther);
		process (hazeParther);
	}
	
	static void process (Parser parserConcrete){
		parserConcrete.getData();
		parserConcrete.pushData();
	}

}
