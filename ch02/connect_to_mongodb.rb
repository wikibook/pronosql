db = Mongo::Connection.new(“localhost”, 27017).db(“prefs”) 
locationCollection = db.collection(“location”)
locationCollection.find().each { |row| puts row.inspect }