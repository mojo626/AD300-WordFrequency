import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

class Main {

	//sorts the HashMap by key by passing it into a TreeMap and then back since TreeMaps are automatically sorted by key
	public TreeMap<String, Integer> sortWordsByKey(HashMap<String, Integer> input) {
		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(input);

		return treeMap;
	}


	public HashMap<String, Integer> sortWordsByValue(HashMap<String, Integer> input) {
		HashMap<String, Integer> output = input.entrySet()
			.stream()
			.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return output;
	}

	public void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String input = "";

		System.out.println("Please start typing below (\"exit\" to stop):");

		while (true) {
			String line = scanner.nextLine();
			
			if (line.equals("exit")) {
				input = input.substring(0, input.length() - 1); //remove the last space
				break;
			}

			input += line + " ";
		}


		input = input.replaceAll("[^a-zA-Z\\s+0-9]", ""); //removes all characters in the string that are not spaces, letters or numbers

		String[] words = input.split("\\s+");
		
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].toLowerCase();
		}

		HashMap<String, Integer> frequency = new HashMap<>();

		for (String word : words) {
			if (!frequency.containsKey(word)) {
				frequency.put(word, 1);
			} else {
				frequency.put(word, frequency.get(word) + 1);
			}
		}

		TreeMap<String, Integer> sortedKeys = sortWordsByKey(frequency);
		HashMap<String, Integer> sortedValues = sortWordsByValue(frequency);

		System.out.print("How many alphebetically sorted words to show (0 for all): ");
		int shownWords = scanner.nextInt();

		int counter = 0;
		for (Map.Entry<String, Integer> entry : sortedKeys.entrySet()) {
			if (counter == shownWords && shownWords != 0) {
				break;
			}

			System.out.println(entry.getKey() + ": " + entry.getValue());

			counter++;
		}


		System.out.print("How many frequency sorted words to show (0 for all): ");
		shownWords = scanner.nextInt();

		counter = 0;
		for (Map.Entry<String, Integer> entry : sortedValues.entrySet()) {
			if (counter == shownWords && shownWords != 0) {
				break;
			}

			System.out.println(entry.getKey() + ": " + entry.getValue());

			counter++;
		}

		scanner.close();
    }


}
