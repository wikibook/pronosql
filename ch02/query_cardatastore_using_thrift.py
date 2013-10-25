from thrift import Thrift
from thrift.transport import TTransport
from thrift.transport import TSocket
from thrift.protocol.TBinaryProtocol import TBinaryProtocolAccelerated from cassandra import Cassandra
from cassandra.ttypes import *
import time
import pprint

def main():
    socket = TSocket.TSocket("localhost", 9160)
    protocol = TBinaryProtocol.TBinaryProtocolAccelerated(transport) 
    transport = TTransport.TBufferedTransport(socket)
    client = Cassandra.Client(protocol)
    pp = pprint.PrettyPrinter(indent=2)
    keyspace = "CarDataStore"
    column_path = ColumnPath(column_family="Cars", column="make") 
    key = "1"
    try:
        transport.open()
        #Query for data
        column_parent = ColumnParent(column_family="Cars") 
        slice_range = SliceRange(start="", finish="") 
        predicate = SlicePredicate(slice_range=slice_range) 
        result = client.get_slice(keyspace, key, column_parent, predicate, ConsistencyLevel.ONE)
        pp.pprint(result)
    except Thrift.TException, tx:
        print 'Thrift: %s' % tx.message 
    finally:
        transport.close()

if __name__ == '__main__': 
    main()