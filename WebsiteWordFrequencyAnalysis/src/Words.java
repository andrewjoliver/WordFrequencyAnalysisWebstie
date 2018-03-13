import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Iterator;

public class Words {
	static HashMap<String, Integer> finalcontent = new HashMap<>();
			
	public static HashMap<String, Integer> getWords(File dir) {
		File[] dirContents = dir.listFiles();
		String content = "";
		StringBuilder contentBuilder = new StringBuilder();
		
		for(File f : dirContents){
			if (f.isDirectory()) {
				getWords(f);
			}
			
			else {
				if ( (! f.getName().contains("."))){
				//if (f.getName().contains("-") || f.getName().contains("html") ) {
				//if (f.getName().contains("html")) {
					try {
						BufferedReader in = new BufferedReader(new FileReader(f));
						String str;
						while ((str = in.readLine()) != null) {
							contentBuilder.append(str);
							content = contentBuilder.toString();
						}
						
						String newcontent = cleanText(content);
						
						String[] arr = newcontent.split(" ");
						for (String w : arr) {
							Integer n = finalcontent.get(w);
							n = (n == null) ? 1 : ++n;
							finalcontent.put(w, n);
						}
						//finalcontent += content;
						in.close();
					} 
					catch (IOException e) {}
				}
			}
		}
		return finalcontent;
	}

	public static String cleanText(String content){
		content = content.toLowerCase();
		content = content.replaceAll("[^a-z && [^'] ]", " ");
		
		//System.out.println("replace all" + content);

		String [] stopWords = {"about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the"};
		String [] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		String [] htmlStopWords = {"li", " ", "jquery", "url", "div", "index", "alt", "row", "sm", "type", "line", "c", "bf", "'", "ffffff", "images", "circle", "min", "input", "og", "svg", "endif", "svgid", "fs", "cy", "cx", "image", "button", "md", "path", "function", "container", "src", "polygon", "miterlimit", "stroke", "rect", "col", "footer", "com", "http", "https", "www", "icon", "script", "title", "description", "css", "stylesheet", "id", "nav", "navbar", "property", "content", "height", "width", "js", "lt", "ul", "class", "href", "http", "html", "doctype", "body", "br", "div", "em", "font", "frame", "h1", "h2", "h3", "h4", "h5", "h6", "header", "img", "label", "li", "ol", "href", "main", "menu", "meta", "span"};
		String [] characters = {",", "<", ".", ">", "/", "?", "'", ":", ";", "]", "}", "[", "{", "=", "+", "-", "_", ")", "(", "*", "&", "^", "%", "$", "#", "@", "!", "|"};

		for (String word: characters){
			content = content.replace(word, " ");
		}
		
		for (String word: stopWords){
			String word1 = " " + word + " ";
			content = content.replace(word1, " ");
		}


		for (String word: htmlStopWords){
			String word1 = " " + word + " ";
			content = content.replace(word1, " ");
		}



		for (String word: alphabet){
			String word1 = " " + word + " ";
			content = content.replace(word1, " ");
		}
		
		for (String word: characters){
			String word1 = " " + word + " ";
			content = content.replace(word1, " ");
		}

		return content;
	}

	/*
	public static HashMap<String, Integer> analysis(String content){
		HashMap<String, Integer> map = new HashMap<>();
		String[] arr = content.split(" ");
		for (String w : arr) {
			Integer n = map.get(w);
			n = (n == null) ? 1 : ++n;
			map.put(w, n);
		}
		return map;
	}
	*/

	public static LinkedHashMap<String, Integer> sortHashMapByValues(HashMap<String, Integer> passedMap) {
	    List<String> mapKeys = new ArrayList<>(passedMap.keySet());
	    List<Integer> mapValues = new ArrayList<>(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    LinkedHashMap<String, Integer> sortedMap =
	        new LinkedHashMap<>();

	    Iterator<Integer> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Integer val = valueIt.next();
	        Iterator<String> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            String key = keyIt.next();
	            Integer comp1 = passedMap.get(key);
	            Integer comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return sortedMap;
	}
	
	/*
	public static void PrettyPrint(LinkedHashMap<String, Integer> map){
		for (String key : map.keySet()) {
		    System.out.println(key + " = " + map.get(key));
		}
		
	}
	*/
	
	public static void main(String[] args){
		File dir = FileSelector.selectDirectory();
	    LinkedHashMap<String, Integer> map = sortHashMapByValues(getWords(dir));
	
		for (String key : map.keySet()) {
		    System.out.println(key + " = " + map.get(key));
		}
	}

}


