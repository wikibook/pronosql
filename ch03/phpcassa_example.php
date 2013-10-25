<?php // Copy all the files in this repository to your include directory. 
$GLOBALS[‘THRIFT_ROOT’] = dirname(__FILE__) . ‘/include/thrift/’; 
require_once $GLOBALS[‘THRIFT_ROOT’].’/packages/cassandra/Cassandra.php’; 
require_once $GLOBALS[‘THRIFT_ROOT’].’/transport/TSocket.php’; 
require_once $GLOBALS[‘THRIFT_ROOT’].’/protocol/TBinaryProtocol.php’; 
require_once $GLOBALS[‘THRIFT_ROOT’].’/transport/TFramedTransport.php’; 
require_once $GLOBALS[‘THRIFT_ROOT’].’/transport/TBufferedTransport.php’; 
include_once(dirname(__FILE__) . ‘/include/phpcassa.php’); 
include_once(dirname(__FILE__) . ‘/include/uuid.php’);
$posts = new CassandraCF(‘BlogPosts’, ‘post’); $posts ->get();
?>