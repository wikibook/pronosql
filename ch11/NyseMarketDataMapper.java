package com.treasuryofideas.hbasemr;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NyseMarketDataMapper extends
    Mapper<LongWritable, Text, Text, MapWritable> {
  
  public void map(LongWritable key, MapWritable value, Context context)
      throws IOException, InterruptedException {
    
    final Text EXCHANGE = new Text("exchange");
    final Text STOCK_SYMBOL = new Text("stockSymbol");
    final Text DATE = new Text("date");
    final Text STOCK_PRICE_OPEN = new Text("stockPriceOpen");
    final Text STOCK_PRICE_HIGH = new Text("stockPriceHigh");
    final Text STOCK_PRICE_LOW = new Text("stockPriceLow");
    final Text STOCK_PRICE_CLOSE = new Text("stockPriceClose");
    final Text STOCK_VOLUME = new Text("stockVolume");
    final Text STOCK_PRICE_ADJ_CLOSE = new Text("stockPriceAdjClose");
    
    try
    {
      //sample market data csv file
      String strFile = "data/NYSE_daily_prices_A.csv";
 
      //create BufferedReader to read csv file
      BufferedReader br = new BufferedReader( new FileReader(strFile));
      String strLine = "";
      int lineNumber = 0;
 
      //read comma separated file line by line
      while( (strLine = br.readLine()) != null)
      {
        lineNumber++;
                if(lineNumber > 1) {
                  String[] data_values = strLine.split(",");
                  MapWritable marketData = new MapWritable();
                  marketData.put(EXCHANGE, new Text(data_values[0]));
                  marketData.put(STOCK_SYMBOL, new Text(data_values[1]));
                  marketData.put(DATE, new Text(data_values[2]));
                  marketData.put(STOCK_PRICE_OPEN, new Text(data_values[3]));
                  marketData.put(STOCK_PRICE_HIGH, new Text(data_values[4]));
                  marketData.put(STOCK_PRICE_LOW, new Text(data_values[5]));
                  marketData.put(STOCK_PRICE_CLOSE, new Text(data_values[6]));
                  marketData.put(STOCK_VOLUME, new Text(data_values[7]));
                  marketData.put(STOCK_PRICE_ADJ_CLOSE, new Text(data_values[8]));
                  
                  context.write(new Text(String.format("%s-%s", data_values[1], data_values[2])), marketData);
                  
                }  
      }
 
 
    }
    catch(Exception e)
    {
      System.errout.println("Exception while reading csv file or process interrupted: " + e);			
    }
    
    }
}
