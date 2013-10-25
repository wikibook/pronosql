import com.mongodb.DB;
import com.mongodb.DBCollection; 
import com.mongodb.DBCursor; 
import com.mongodb.Mongo;

public class JavaMongoDBClient {
  Mongo m;
  DB db; 
  DBCollection coll;

  public void init() throws Exception {
    m = new Mongo( "localhost" , 27017 ); 
    db = m.getDB( "mydb" );
    coll = db.getCollection("logdata");
  }

  public void getLogData() { 
    DBCursor cur = coll.find();
    while(cur.hasNext()) { 
      System.out.println(cur.next());
    } 
  }
  
  public static void main(String[] args) {
    try{
      JavaMongoDBClient javaMongoDBClient = new JavaMongoDBClient(); 
      javaMongoDBClient.init();
      javaMongoDBClient.getLogData();
    } catch(Exception e) { 
        e.printStackTrace();
    } 
  }
  
}