# Clasificación y generación de datos <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg" width="64" />

Proyecto que toma como entrada una serie de archivos planos, el cual contiene información de datos de los vendedores e información de las ventas de cada uno y genera reporte de ventas.
## Métodos
### `createSalesMenFiles`

Método para crear archivos con información de ventas para cada vendedor.

```java
public boolean createSalesMenFiles(int numSalesmen, int numSalesFilesPerSalesman) {
        try {
            Random random = new Random();
            for (int i = 1; i <= numSalesmen; i++) {
                
                String firstName = "FirstName" + i;
                String lastName = "LastName" + i;
                
                String filename = "ventas_" + firstName + "_" + lastName + ".txt";
                PrintWriter writer = new PrintWriter(new FileWriter(filename));
                
                
                String type = "Type" + (random.nextInt(3) + 1); // Tipo de documento aleatorio
                long documentNumber = 1000000 + random.nextInt(9000000); // Número de documento aleatorio
                writer.println("TipoDocumentoVendedor;" + documentNumber);
                
                
                for (int j = 1; j <= numSalesFilesPerSalesman; j++) {
                    for (int k = 1; k <= 3; k++) {
                        String productID = "Product" + k;
                        int quantitySold = random.nextInt(10) + 1; 
                        writer.println(productID + ";" + quantitySold);
                    }
                }
                writer.close();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
```
### `createProductsFile`

Método para crear un archivo con información de productos

```java
public boolean createProductsFile(int productsCount) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("productos_info.txt"));
            Random random = new Random();
            for (int i = 1; i <= productsCount; i++) {
                
                String productID = "Product" + i;
                String productName = "Product Name " + i;
                double price = random.nextDouble() * 100; 
                writer.println(productID + ";" + productName + ";" + price);
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

```
### `createSalesManInfoFile`

Método para crear un archivo con información de vendedores

```java
public boolean createSalesManInfoFile(int salesmanCount) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("vendedor_info.txt"));
            Random random = new Random();
            for (int i = 1; i <= salesmanCount; i++) {
                
                String type = "Type" + (random.nextInt(3) + 1);
                long documentNumber = 1000000 + random.nextInt(9000000);
                String firstName = "FirstName" + (random.nextInt(100) + 1);
                String lastName = "LastName" + (random.nextInt(100) + 1);
                writer.println(type + ";" + documentNumber + ";" + firstName + ";" + lastName);
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
