import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateInfoFiles {
    public static void main(String[] args) {
        // Crear una instancia de GenerateInfoFiles para llamar a métodos no estáticos
        GenerateInfoFiles generator = new GenerateInfoFiles();
        // Verificar si todos los archivos se crearon exitosamente
        if (generator.createSalesMenFiles(5, 3) &&
            generator.createProductsFile(20) &&
            generator.createSalesManInfoFile(10)) {
            System.out.println("Archivos generados exitosamente.");
        } else {
            System.out.println("Error al generar los archivos.");
        }
    }

    // Método para crear archivos con información de ventas para cada vendedor
    public boolean createSalesMenFiles(int numSalesmen, int numSalesFilesPerSalesman) {
        try {
            Random random = new Random();
            for (int i = 1; i <= numSalesmen; i++) {
                // Generar nombre y apellido para cada vendedor
                String firstName = "FirstName" + i;
                String lastName = "LastName" + i;
                // Crear un nombre de archivo basado en el nombre del vendedor
                String filename = "ventas_" + firstName + "_" + lastName + ".txt";
                PrintWriter writer = new PrintWriter(new FileWriter(filename));
                
                // Escribir información de tipo y número de documento del vendedor
                String type = "Type" + (random.nextInt(3) + 1); // Tipo de documento aleatorio
                long documentNumber = 1000000 + random.nextInt(9000000); // Número de documento aleatorio
                writer.println("TipoDocumentoVendedor;" + documentNumber);
                
                // Generar y escribir ventas aleatorias
                for (int j = 1; j <= numSalesFilesPerSalesman; j++) {
                    for (int k = 1; k <= 3; k++) {
                        String productID = "Product" + k;
                        int quantitySold = random.nextInt(10) + 1; // Cantidad vendida aleatoria
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

    // Método para crear un archivo con información de productos
    public boolean createProductsFile(int productsCount) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("productos_info.txt"));
            Random random = new Random();
            for (int i = 1; i <= productsCount; i++) {
                // Generar ID, nombre y precio de cada producto
                String productID = "Product" + i;
                String productName = "Product Name " + i;
                double price = random.nextDouble() * 100; // Precio aleatorio
                writer.println(productID + ";" + productName + ";" + price);
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para crear un archivo con información de vendedores
    public boolean createSalesManInfoFile(int salesmanCount) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("vendedor_info.txt"));
            Random random = new Random();
            for (int i = 1; i <= salesmanCount; i++) {
                // Generar tipo y número de documento, nombre y apellido aleatorios para cada vendedor
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

