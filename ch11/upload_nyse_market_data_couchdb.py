def upload_nyse_market_data():
    couch_server = Couch('localhost', '5984')
    print "\nCreate database 'nyse_db':"
    couch_server.createDb('nyse_db')
    
    for file in os.listdir(PATH):
        if fnmatch.fnmatch(file, 'NYSE_daily_prices_*.csv'):
            print "opening file: " + file 
            f = open(PATH+file, 'r' )
            reader = csv.DictReader( f )
            print "beginning to save json documents converted from csv data in " + file
            for row in reader:
                json_doc = json.dumps(row)
                couch_server.saveDoc('nyse_db', json_doc)
                print "available json documents converted from csv data in " + file + " saved"
                print "closing " + file
            f.close()
