# Clasificación y generación de datos <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" width="64" />

Proyecto que toma como entrada una serie de archivos planos, el cual contiene información de datos de los vendedores e información de las ventas de cada uno y genera reporte de ventas.
## Métodos

### `createProductsFile`

Método para crear un archivo con información de productos

```java
public static void createProductsFile(int productsCount) {
    	try {
    		Random random = new Random();
    		String[] storage = {"Leche entera", "Pan blanco", "Huevos grandes", "Arroz blanco", "Azúcar blanca", "Harina de trigo", "Aceite vegetal", "Sal de mesa", "Café molido", "Té negro", "Jugo de naranja", "Agua embotellada", "Pasta de dientes", "Jabón de tocador", "Shampoo para cabello normal", "Papel higiénico", "Detergente para ropa", "Suavizante de telas", "Toallas de papel", "Servilletas de papel"};
    		
    		class Product {
    			int id;
    			String name;
    			int price;
    			
    			public Product(int id, String name, int price) {
    				this.id = id;
    				this.name = name;
    				this.price = price;
    			}
    		}    		
    		
    		Product[] products = new Product[productsCount];
    		
    		Path path = Paths.get("products.csv");
    		List<String> lines = new ArrayList<>();
    		lines.add("\uFEFF");
    		lines.add("id;name;price");
    		
    		int index = 0;
    		for (Product product : products) {
    			int id = random.nextInt(900) + 100;
    			String name = storage[index];
    			int price = random.nextInt(91) * 100 + 1000;
    			product = new Product(id, name, price);
    			index++;
    			String line = product.id + ";" + product.name + ";" + product.price;
    			lines.add(line);
    		}
    		
    		Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    	} catch (IOException exception) {
    		System.out.println("Error al generar productos.");
    		exception.printStackTrace();
    	}
```

### `createSalesManInfoFile`

Método para crear un archivo con información de vendedores

```java
public static void createSalesManInfoFile(int salesmanCount) {
    	try {
    		Random random = new Random();
    		String[] firstNames = {"María", "José", "Juan", "Camila", "Alejandro", "Valentina", "Santiago", "Isabel", "Andrés", "Mariana"};
    		String[] lastNames = {"Rodríguez", "González", "Martínez", "García", "López", "Hernández", "Sánchez", "Ramírez", "Pérez", "Gómez"};
    		String[] idTypes = {"Cédula de Ciudadanía", "Tarjeta de Identidad", "Registro Civil de Nacimiento", "Cédula de Extranjería", "Pasaporte", "Permiso Especial de Permanencia"};
    		
    		class Seller {
    			String idType;
    			long id;
    			String firstName;
    			String lastName;
    			
    			public Seller(String idType, long id, String firstName, String lastName) {
    				this.idType = idType;
    				this.id = id;
    				this.firstName = firstName;
    				this.lastName = lastName;
    			}
    		}    		
    		
    		Seller[] sellers = new Seller[salesmanCount];
    		
    		Path path = Paths.get("sellers.csv");
    		List<String> lines = new ArrayList<>();
    		lines.add("\uFEFF");
    		lines.add("idType;id;firstName;lastName");
    		
    		for (Seller seller : sellers) {
    			String idType = idTypes[random.nextInt(idTypes.length)];
    			long id = (long) (random.nextDouble() * 9000000000L) + 1000000000L;
    			String firstName = firstNames[random.nextInt(firstNames.length)];
    			String lastName = lastNames[random.nextInt(lastNames.length)];
    			seller = new Seller(idType, id, firstName, lastName);
    			
    			String line = seller.idType + ";" + seller.id + ";" + seller.firstName + ";" + seller.lastName;
    			lines.add(line);
    		}
    	
    		Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    	} catch (IOException exception) {
    		System.out.println("Error al generar vendedores.");
    		exception.printStackTrace();
    	}	
    }
```

### `createSalesMenFiles`

Método para crear archivos con información de ventas para cada vendedor.

```java
public static void createSalesMenFile(int randomSalesCount, String name, long id) {
    	try {
    		Random random = new Random();
    		Path path = Paths.get("sales_" + id + ".csv");
    		List<String> lines = new ArrayList<>();
    		lines.add("\uFEFF");
    		
    		Path sellersPath = Paths.get("sellers.csv");
	    	List<String> sellersLines = Files.lines(sellersPath, StandardCharsets.UTF_8).collect(Collectors.toList());
	    	sellersLines = sellersLines.subList(2, sellersLines.size());
	    	for (String line : sellersLines) {
	    		String[] seller = line.split(";");
	    		if (Long.parseLong(seller[1]) == id) {
	    			lines.add(seller[0] + ";" + id);
	    			Path productsPath = Paths.get("products.csv");
	    	    	List<String> productsLines = Files.lines(productsPath, StandardCharsets.UTF_8).collect(Collectors.toList());
	    	    	productsLines = productsLines.subList(2, productsLines.size());
	    	    	
	    	    	for (int sale = 0; sale < randomSalesCount; sale++) {
	    	    		String[] product = productsLines.get(sale).split(";");
	    	    		lines.add(product[0] + ";" + (random.nextInt(10) + 1));
	    	    	}
	    			break;
	    		}
	    	}
	    	
	    	Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    	} catch (IOException exception) {
    		System.out.println("Error al generar ventas.");
    		exception.printStackTrace();
    	}
    }
```
## Próximamente
- Incluir cantidad de dinero que recaudó cada vendedor en el archivo.
- Ordenar reporte de ventas por cantidad de dinero de mayor a menor.
- Crear archivo con la información de los productos vendidos por cantidad, ordenados en forma descendente. 

### GRUPO B01 - SUBGRUPO 13
Proyecto desarrollado por:

- MARIA JOSE BARAHONA SERPA
- JOSE ANDRES CARREÑO MURILLO
- ARANGO PIEDRAHITA ELIZABETH
- INGRID PAOLA GUTIÉRREZ RINCÓN
- PAULA VALENTINA VELANDIA CASTRO
