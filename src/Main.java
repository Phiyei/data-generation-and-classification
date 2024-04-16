import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
    	try {
    		Path directory = Paths.get(".");
	    	List<Path> salesFiles = Files.list(directory).filter(path -> path.getFileName().toString().startsWith("sales_")).collect(Collectors.toList());
	    	
	    	Path productsPath = Paths.get("products.csv");
	    	List<String> productsLines = Files.lines(productsPath, StandardCharsets.UTF_8).collect(Collectors.toList());
	    	productsLines = productsLines.subList(2, productsLines.size());
	    	
	    	Path sellersPath = Paths.get("sellers.csv");
	    	List<String> sellersLines = Files.lines(sellersPath, StandardCharsets.UTF_8).collect(Collectors.toList());
	    	sellersLines = sellersLines.subList(2, sellersLines.size());
	    	
	    	Path sellerReportPath = Paths.get("seller_report.csv");
    		List<String> sellerReportLines = new ArrayList<>();
    		List<String> sellerReport = new ArrayList<>();
    		sellerReport.add("\uFEFF");
	    
	    	
	    	for (Path salesFile : salesFiles) {
	    		String name = "";
	    		int total = 0;
	    		List<String> salesLines = Files.readAllLines(salesFile, StandardCharsets.UTF_8);
	    		String[] salesSeller = salesLines.get(1).split(";");
	    		for (String sellersLine : sellersLines) {
	    			String[] seller = sellersLine.split(";");
	    			if (Long.parseLong(seller[1]) == Long.parseLong(salesSeller[1])) {
	    				name = seller[2] + " " + seller[3];
	    			}
	    		}
	    				
	    		salesLines = salesLines.subList(2, salesLines.size());
	    		for (String salesLine : salesLines) {
	    			String[] sales = salesLine.split(";");
	    			int id = Integer.parseInt(sales[0]);
	    			int quantity = Integer.parseInt(sales[1]);
	    			
	    			for (String productLine : productsLines) {
	    	    		String[] product = productLine.split(";");
	    	    		int productId = Integer.parseInt(product[0]);
	    	    		int price = Integer.parseInt(product[2]);
	    	    		if (productId == id) {
	    	    			total += price * quantity;
	    	    			break;
	    	    		}
	    	    	}
	    		}
	    		
	    		sellerReportLines.add(name + ";" + total);
	    	}
	    	
	    	Collections.sort(sellerReportLines, new Comparator<String>() {
	    		public int compare(String firstLine, String secondLine) {
	    			String[] first = firstLine.split(";");
	    			String[] second = secondLine.split(";");
	    			int firstTotal = Integer.parseInt(first[1]);
	    			int secondTotal = Integer.parseInt(second[1]);
	    			return Integer.compare(secondTotal, firstTotal);
	    		}
	    	});
	    	
	    	sellerReport.addAll(sellerReportLines);
	    	
	    	Files.write(sellerReportPath, sellerReport, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	    	System.out.println("Reportes generados exitosamente.");
    	} catch (IOException exception) {
    		System.out.println("Error al generar los reportes.");
        	exception.printStackTrace();
    	}
    }
}
