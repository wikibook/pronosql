#!/bin/bash
FILES=./infochimps_dataset_4778_download_16677/NYSE/NYSE_daily_prices_*.csv
for f in $FILES
do
  echo "Processing $f file..."
  # set MONGODB_HOME environment variable to point to the MongoDB installation folder.
  ls -l $f
  $MONGODB_HOME/bin/mongoimport --type csv --db mydb --collection nyse --headerline $f
Done
