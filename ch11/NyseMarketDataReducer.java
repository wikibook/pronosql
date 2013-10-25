public class NyseMarketDataReducer extends TableReducer<Text, MapWritable, ImmutableBytesWritable> {
         public void reduce(Text arg0, Iterable arg1, Context context) {
        //Since the complex key made up of stock symbol and date is unique
                  //one value comes for a key.
        Map marketData = null;
        for (MapWritable value : arg1) {
            marketData = value;
            break;
        }

        ImmutableBytesWritable key = new ImmutableBytesWritable(Bytes
                .toBytes(arg0.toString()));
        Put put = new Put(Bytes.toBytes(arg0.toString()));
        put.add(Bytes.toBytes("mdata"), Bytes.toBytes("daily"), Bytes
                .toBytes((ByteBuffer) marketData));
        try {
            context.write(key, put);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
        }
    }	

}
