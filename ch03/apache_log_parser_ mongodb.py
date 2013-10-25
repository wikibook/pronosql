def props(obj): 
  pr = {}
  for name in dir(obj):
    value = getattr(obj, name)
    if not name.startswith(‘__’) and not inspect.ismethod(value):
      pr[name] = value 
  return pr
  
connection = Connection()
db = connection.mydb
collection = db.logdata
alf = ApacheLogFile(<path to access_log>) 
for log_line in alf:
  collection.insert(props(log_line)) 
alf.close()