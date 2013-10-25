from pymongo import Connection
connection = Connection(‘localhost’, 27017) 
db = connection.prefs
collection = db.location
for doc in collection.find():
    doc