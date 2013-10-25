import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.io.RowResult;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class HBaseConnector {

public static Map retrievePriceData(String rowKey) throws IOException {
HTable table = new HTable(new HBaseConfiguration(), "historical_daily_stock_price");
Map stockData = new HashMap();

RowResult result = table.getRow(rowKey);

for (byte[] column : result.keySet()) {
    stockData.put(new String(column), new String(result.get(column).getValue()));
}

return stockData;
}

public static void main(String[] args) throws IOException {
    Map stock_data = HBaseConnector.retrievePriceData("NYSEAA20080227");
    System.out.println(stock_data.get("price:open"));
    System.out.println(stock_data.get("price:high"));
}
}
