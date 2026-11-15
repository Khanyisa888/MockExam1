package productsalesapplication;
import javax.swing.SwingUtilities;
public class ProductSalesApplication implements IProductSales {

    private final int[][] productSalesData = {
        {300, 150, 700}, 
        {250, 200, 600}  
    };

    private final int SALES_LIMIT = 500;

    @Override
    public int[][] GetProductSales() {
        return productSalesData;
    }

    @Override
    public int GetTotalSales() {
        int total = 0;
        for (int[] year : productSalesData) {
            for (int sale : year) {
                total += sale;
            }
        }
        return total;
    }

    @Override
    public int GetSalesOverLimit() {
        int count = 0;
        for (int[] year : productSalesData) {
            for (int sale : year) {
                if (sale > SALES_LIMIT) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int GetSalesUnderLimit() {
        int count = 0;
        for (int[] year : productSalesData) {
            for (int sale : year) {
                if (sale < SALES_LIMIT) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int GetProductsProcessed() {
        return productSalesData.length;
    }

    @Override
    public double GetAverageSales() {
        int total = GetTotalSales();
        int count = 0;
        for (int[] year : productSalesData) {
            count += year.length;
        }
        return count > 0 ? (double) total / count : 0;
    }
public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductSalesFrame().setVisible(true);
        });
    }
}