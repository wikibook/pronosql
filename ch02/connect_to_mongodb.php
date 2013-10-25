$connection = new Mongo( “localhost:27017” ); 
$collection = $connection->prefs->location; 
$cursor = $collection->find();
foreach ($cursor as $id => $value) {
    echo “$id: “;
    var_dump( $value ); 
}